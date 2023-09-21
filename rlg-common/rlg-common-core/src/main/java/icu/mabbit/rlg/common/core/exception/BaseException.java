package icu.mabbit.rlg.common.core.exception;

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
    /**
     * 细节信息，用于内部调试
     */
    private String detail;

    public BaseException()
    {
    }

    public BaseException(String message)
    {
        super(message);
    }

    public BaseException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BaseException(Throwable cause)
    {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
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