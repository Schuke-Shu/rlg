package icu.mabbit.rlg.common.exception;

import icu.mabbit.rlg.common.enums.ServiceCode;

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
    public ProjectException()
    {
    }

    public ProjectException(int code)
    {
        super(code);
    }

    public ProjectException(int code, String msg)
    {
        super(code, msg);
    }

    public ProjectException(ServiceCode code)
    {
        super(code);
    }

    public ProjectException(ServiceCode code, String msg)
    {
        super(code, msg);
    }

    public ProjectException(Throwable cause)
    {
        super(cause);
    }
}