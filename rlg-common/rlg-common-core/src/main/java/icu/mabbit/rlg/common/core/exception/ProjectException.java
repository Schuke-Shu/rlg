package icu.mabbit.rlg.common.core.exception;

/**
 * <h2>全局项目异常</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/7 13:22
 */
public class ProjectException
        extends BaseException
{
    private static final long serialVersionUID = 1L;

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