package icu.mabbit.rlg.common.test.mapper;

import icu.mabbit.rlg.common.data.orm.BaseDO;
import icu.mabbit.rlg.common.data.orm.BaseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * mapper测试接口
 *
 * @param <E> 实体数据类型
 * @param <K> 主键类型
 * @param <M> mapper类型
 * @author 一只枫兔
 * @Project rlg
 * @Module common-test
 * @Date 2023/9/5 13:32
 */
@SpringBootTest
public interface BaseMapperTest<K extends Serializable, E extends BaseDO<K>, M extends BaseMapper<K, E>>
{
    /**
     * 获取mapper
     *
     * @return mapper
     */
    M mapper();

    /**
     * 自动测试，需要在这个方法上添加{@link Test}注解，然后调用{@link BaseMapperTest#auto(String)}
     *
     * <pre>
     *     <code>@Test</code>
     *     <code>@Override</code>
     *     public void baseAutoTest()
     *             throws Throwable
     *     {
     *         auto("id");
     *     }
     * </pre>
     */
    void baseAutoTest()
            throws Throwable;

    /**
     * 自动测试
     * <p>
     *     自动调用{@link BaseMapperTest#dynamicRemoveParam()} ()}、{@link BaseMapperTest#updateParam()}、{@link BaseMapperTest#dynamicQueryParam()} ()}、{@link BaseMapperTest#params()}、{@link BaseMapperTest#baseAutoTest()}
     * </p>
     *
     * @param pkName 主键属性名
     */
    default void auto(String pkName)
            throws Throwable
    {
        baseAutoTest(
                pkName,
                dynamicRemoveParam(),
                updateParam(),
                dynamicQueryParam(),
                params()
        );
    }

    /**
     * 提供测试数据，不得少于三个
     *
     * @return 测试数据
     */
    E[] params();

    /**
     * 提供动态删除测试数据
     *
     * @return 动态删除测试数据
     */
    E dynamicRemoveParam();

    /**
     * 提供更新测试数据
     *
     * @return 更新测试数据
     */
    E updateParam();

    /**
     * 提供动态查询测试数据
     *
     * @return 动态查询测试数据
     */
    E dynamicQueryParam();

    /**
     * 自动测试
     *
     * @param pn 主键名
     * @param dr 动态删除数据
     * @param ud 更新数据
     * @param dq 动态查询数据
     * @param ps 参数，不得少于三个
     * @throws IllegalArgumentException 如果参数少于三个
     */
    @SuppressWarnings("ALL")
    default void baseAutoTest(String pn, E dr, E ud, E dq, E[] ps)
            throws Throwable
    {
        if (ps == null || ps.length < 3) throw new IllegalArgumentException("自动测试参数不得少于三个");

        M mapper = mapper();
        assert mapper.save(ps[0]) == 1;

        assert mapper.save(ps) == ps.length;


        // 参数类型
        Class<?> type = ud.getClass();
        // 主键类型
        Class<?> kt = type.getDeclaredField(pn).getType();
        // 获取id方法
        Method getId = type.getDeclaredMethod(
                "get" + Character.toUpperCase(pn.charAt(0)) + new String(pn.substring(1))
        );
        // 设置id方法
        Method setId = type.getDeclaredMethod(
                "set" + Character.toUpperCase(pn.charAt(0)) + new String(pn.substring(1)),
                kt
        );

        assert mapper.count() >= ps.length;

        assert mapper.update(ud) == 1;

        for (int i = 0; i < ps.length; i++)
        {
            K p = (K) getId.invoke(ps[i]);

            if (i == 0)
            {
                setId.invoke(ud, p);
                assert mapper.update(ud) == 1;
            }

            assert mapper.query(p).get(0) != null;
            assert mapper.remove(p) == 1;
        }
    }
}