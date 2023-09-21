package icu.mabbit.rlg.common.core.enums;

/**
 * <h2>业务状态码</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/2 15:58
 */
public enum ServiceCode
{
    OK(0, "请求成功"),
    ERR_UNKNOWN(1, "未知错误"),
    ERR_USER(0xA0000, "用户端错误", Level.ONE),
    ERR_TOKEN(0xA0100, "token错误", Level.TWO),
    ERR_TOKEN_EXPIRED(0xA0101, "token过期"),
    ERR_TOKEN_SIGNATURE(0xA0102, "签名错误，token被篡改"),
    ERR_token_MALFORMED(0x0103, "格式错误，token被篡改"),
    ERR_LOGIN(0xA0200, "用户登录异常", Level.TWO),
    ERR_USER_NOT_FOUND(0xA0201, "用户账户不存在"),
    ERR_USER_FROZEN(0xA0202, "用户账户被冻结"),
    ERR_USER_INVALIDATED(0xA0203, "用户账户已作废"),
    ERR_SYS(0xB0000, "系统执行出错", Level.ONE),
    ERR_SYS_RESOURCE_ACCESS(0xB0320, "系统资源访问异常"),
    ;

    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误提示信息
     */
    private final String msg;

    private final Level level;


    ServiceCode(int code, String msg)
    {
        this(code, msg, Level.NORMAL);
    }


    ServiceCode(int code, String msg, Level level)
    {
        this.code = code;
        this.msg = msg;
        this.level = level;
    }

    public int code()
    {
        return code;
    }

    public String msg()
    {
        return msg;
    }

    public Level level()
    {
        return level;
    }

    public static enum Level
    {
        ONE(1, "一级宏观错误码"),
        TWO(2, "二级宏观错误码"),
        NORMAL(3, "普通错误码"),
        ;

        private final int lv;
        private final String text;

        Level(int lv, String text)
        {
            this.lv = lv;
            this.text = text;
        }

        public int lv()
        {
            return lv;
        }

        public String text()
        {
            return text;
        }
    }
}