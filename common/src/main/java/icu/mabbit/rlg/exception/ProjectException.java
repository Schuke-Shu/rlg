package icu.mabbit.rlg.exception;

/**
 * <h2>全局程序异常类</h2>
 *
 * @author 一只枫兔
 * @Date 2023/8/29 15:54
 */
public class ProjectException
        extends RuntimeException
{
    public ProjectException()
    {
    }

    public ProjectException(String message)
    {
        super(message);
    }

    public ProjectException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ProjectException(Throwable cause)
    {
        super(cause);
    }

    public ProjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}