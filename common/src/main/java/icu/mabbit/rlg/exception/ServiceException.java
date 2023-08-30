package icu.mabbit.rlg.exception;

/**
 * <h2>全局服务异常类</h2>
 *
 * @author 一只枫兔
 * @Date 2023/8/29 15:52
 */
public class ServiceException
        extends RuntimeException
{
    public ServiceException()
    {
    }

    public ServiceException(String message)
    {
        super(message);
    }

    public ServiceException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ServiceException(Throwable cause)
    {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}