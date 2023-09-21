package icu.mabbit.rlg.common.data.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>基于MyBatis的自动插入“创建时间”、更新"最后修改时间"的拦截器</p>
 *
 * <p>需要SQL语法预编译之前进行拦截，则拦截类型为StatementHandler，拦截方法是prepare</p>
 *
 * <p>具体的拦截处理由内部的intercept()方法实现</p>
 *
 * <p>注意：由于仅适用于当前项目，并不具备范用性，所以：</p>
 *
 * <ul>
 * <li>拦截所有的update方法（根据SQL语句以update前缀进行判定），无法不拦截某些update方法</li>
 * <li>所有数据表中"最后修改时间"的字段名必须一致，由本拦截器的FIELD_MODIFIED属性进行设置</li>
 * </ul>
 */
@Slf4j
@Intercepts(
        {
                @Signature(
                        type = StatementHandler.class,
                        method = "prepare",
                        args = {Connection.class, Integer.class}
                )
        }
)
public class InsertUpdateTimeInterceptor
        implements Interceptor
{
    /**
     * {@link BoundSql}中的sql字段名
     */
    private static final String FIELD_SQL = "sql";

    /**
     * 自动添加的创建时间字段
     */
    private static final String FIELD_CREATE = "create_time";
    /**
     * 自动更新时间的字段
     */
    private static final String FIELD_MODIFIED = "modified_time";
    /**
     * SQL语句类型：其它（暂无实际用途）
     */
    private static final int SQL_TYPE_OTHER = 0;
    /**
     * SQL语句类型：INSERT
     */
    private static final int SQL_TYPE_INSERT = 1;
    /**
     * SQL语句类型：UPDATE
     */
    private static final int SQL_TYPE_UPDATE = 2;
    /**
     * 查找SQL类型的正则表达式：INSERT
     */
    private static final Pattern SQL_TYPE_PATTERN_INSERT = Pattern.compile("^insert\\s", Pattern.CASE_INSENSITIVE);
    /**
     * 查找SQL类型的正则表达式：UPDATE
     */
    private static final Pattern SQL_TYPE_PATTERN_UPDATE = Pattern.compile("^update\\s", Pattern.CASE_INSENSITIVE);
    /**
     * 查询SQL语句片段的正则表达式：modified_time片段
     */
    private static final Pattern SQL_STATEMENT_PATTERN_MODIFIED = Pattern.compile(",\\s*" + FIELD_MODIFIED + "\\s*=", Pattern.CASE_INSENSITIVE);
    /**
     * 查询SQL语句片段的正则表达式：create_time片段
     */
    private static final Pattern SQL_STATEMENT_PATTERN_CREATE = Pattern.compile(",\\s*" + FIELD_CREATE + "\\s*[,)]?", Pattern.CASE_INSENSITIVE);
    /**
     * 查询SQL语句片段的正则表达式：WHERE子句
     */
    private static final Pattern SQL_STATEMENT_PATTERN_WHERE = Pattern.compile("\\s+where\\s+", Pattern.CASE_INSENSITIVE);
    /**
     * 查询SQL语句片段的正则表达式：VALUES子句
     */
    private static final Pattern SQL_STATEMENT_PATTERN_VALUES = Pattern.compile("\\)\\s*values?\\s*\\(", Pattern.CASE_INSENSITIVE);

    @Override
    public Object intercept(Invocation invocation)
            throws Throwable
    {
        log.debug("Intercept sql...");

        // 获取BoundSql（封装了即将执行的SQL语句及相关数据的对象）
        BoundSql boundSql = getBoundSql(invocation);
        // 从boundSql中获取SQL语句
        String sql = getSql(boundSql);

        log.trace("Origin sql：{}", sql);

        String newSql = null;
        // 判断原SQL类型
        switch (getOriginalSqlType(sql))
        {
            case SQL_TYPE_INSERT:
                log.debug("The origin sql is [INSERT], creating 'create_time' & 'modified_time'...");
                newSql = appendCreateTimeField(sql, LocalDateTime.now());
                break;
            case SQL_TYPE_UPDATE:
                log.debug("The origin sql is [UPDATE], creating 'modified_time'...");
                newSql = appendModifiedTimeField(sql, LocalDateTime.now());
                break;
            case SQL_TYPE_OTHER:
                log.debug("The origin sql not [INSERT] | [UPDATE], pass...");
                break;
        }

        if (newSql != null)
        {
            log.trace("New sql：{}", newSql);
            Field field = BoundSql
                    .class
                    .getDeclaredField(FIELD_SQL);

            field.setAccessible(true);
            field.set(boundSql, newSql);
        }

        // 执行调用，即拦截器放行，执行后续部分
        return invocation.proceed();
    }

    private String appendCreateTimeField(String sqlStatement, LocalDateTime dateTime)
    {
        if (SQL_STATEMENT_PATTERN_CREATE.matcher(sqlStatement).find())
        {
            log.trace("Origin sql has included 'create_time', pass...");
            return null;
        }

        // INSERT into table (xx, xx, xx ) values (?,?,?)
        // 查找 ) values ( 的位置
        StringBuilder sql = new StringBuilder(sqlStatement);
        Matcher valuesClauseMatcher = SQL_STATEMENT_PATTERN_VALUES.matcher(sql);

        if (!valuesClauseMatcher.find())
        {
            log.trace("Not found ') values (', statement invalid");
            return null;
        }

        int start = valuesClauseMatcher.start();
        int end = valuesClauseMatcher.end();

        // 插入创建时间与更新时间字段名
        String fieldNames = ", " + FIELD_CREATE + ", " + FIELD_MODIFIED;
        sql.insert(start, fieldNames);

        // 查找参数值位置
        Matcher paramPositionMatcher =
                Pattern
                        .compile("\\)")
                        .matcher(sql);

        int position = end + fieldNames.length();

        String param = ", '" + dateTime + "', '" + dateTime + "'";
        while (paramPositionMatcher.find(position))
        {
            start = paramPositionMatcher.start();
            end = paramPositionMatcher.end();

            // 插入字段值
            sql.insert(start, param);
            position = end + param.length();
        }

        return sql.toString();
    }

    private String appendModifiedTimeField(String sqlStatement, LocalDateTime dateTime)
    {
        if (SQL_STATEMENT_PATTERN_MODIFIED.matcher(sqlStatement).find())
        {
            log.trace("Origin sql has included 'modified_time', pass...");
            return null;
        }

        StringBuilder sql = new StringBuilder(sqlStatement);
        Matcher whereClauseMatcher = SQL_STATEMENT_PATTERN_WHERE.matcher(sql);

        // 查找 where 子句的位置
        if (!whereClauseMatcher.find())
        {
            log.trace("Not found 'where' statement, pass...");
            return null;
        }

        sql.insert(
                whereClauseMatcher.start(),
                ", " + FIELD_MODIFIED + "='" + dateTime + "'"
        );
        return sql.toString();
    }

    @Override
    public Object plugin(Object target)
    {
        // 本方法的代码是相对固定的
        if (target instanceof StatementHandler)
            return Plugin.wrap(target, this);
        else
            return target;
    }

    /**
     * <p>获取BoundSql对象，此部分代码相对固定</p>
     *
     * <p>注意：根据拦截类型不同，获取BoundSql的步骤并不相同，此处并未穷举所有方式！</p>
     *
     * @param invocation 调用对象
     * @return 绑定SQL的对象
     */
    private BoundSql getBoundSql(Invocation invocation)
    {
        Object invocationTarget = invocation.getTarget();
        if (invocationTarget instanceof StatementHandler)
            return
                    ((StatementHandler) invocationTarget)
                            .getBoundSql();
        else
            throw new RuntimeException("Failed to get StatementHandler, please check the configuration of interceptor");
    }

    /**
     * 从BoundSql对象中获取SQL语句
     *
     * @param boundSql BoundSql对象
     * @return 将BoundSql对象中封装的SQL语句进行转换小写、去除多余空白后的SQL语句
     */
    private String getSql(BoundSql boundSql)
    {
        return
                boundSql
                        .getSql()
                        .toLowerCase()
                        .replaceAll("\\s+", " ")
                        .trim();
    }

    /**
     * 获取原SQL的语句类型
     *
     * @param sql 原SQL
     * @return SQL语句类型
     */
    private int getOriginalSqlType(String sql)
    {
        if (SQL_TYPE_PATTERN_INSERT.matcher(sql).find())
            return SQL_TYPE_INSERT;

        if (SQL_TYPE_PATTERN_UPDATE.matcher(sql).find())
            return SQL_TYPE_UPDATE;

        return SQL_TYPE_OTHER;
    }
}