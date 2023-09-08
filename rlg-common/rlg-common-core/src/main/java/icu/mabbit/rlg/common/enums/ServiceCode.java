package icu.mabbit.rlg.common.enums;

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
    ERR_USER(0xA0001, "用户端错误"),
    ERR_SYS(0xB0001, "系统执行出错"),
    ERR_RESOURCE_ACCESS(0xB0320, "系统资源访问异常"),
    ;

    private final int code;

    private final String msg;

    ServiceCode(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public int code()
    {
        return code;
    }

    public String msg()
    {
        return msg;
    }
}