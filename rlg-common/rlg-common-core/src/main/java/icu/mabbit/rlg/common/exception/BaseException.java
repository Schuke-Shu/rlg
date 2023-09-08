package icu.mabbit.rlg.common.exception;

import icu.mabbit.rlg.common.enums.ServiceCode;

/**
 * <h2>异常基类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/7 15:15
 */
public class BaseException
        extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;
    /**
     * 细节信息，用于内部调试
     */
    private String detail;

    public BaseException()
    {
    }

    public BaseException(int code)
    {
        this.code = code;
    }

    public BaseException(int code, String msg)
    {
        super(msg);
        this.code = code;
    }

    public BaseException(ServiceCode code)
    {
        this(code.code(), code.msg());
    }

    public BaseException(ServiceCode code, String msg)
    {
        this(code.code(), msg);
    }

    public int code()
    {
        return code;
    }

    public BaseException code(int code)
    {
        this.code = code;
        return this;
    }

    public BaseException(Throwable cause)
    {
        super(cause);
    }

    public String detail()
    {
        return detail;
    }

    public BaseException detail(String detail)
    {
        this.detail = detail;
        return this;
    }
}