package icu.mabbit.rlg.common.exception;

import icu.mabbit.rlg.common.enums.ServiceCode;

/**
 * <h2>全局业务异常</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/2 16:51
 */
public class ServiceException extends BaseException
{
    public ServiceException()
    {
    }

    public ServiceException(int code)
    {
        super(code);
    }

    public ServiceException(int code, String msg)
    {
        super(code, msg);
    }

    public ServiceException(ServiceCode code)
    {
        super(code);
    }

    public ServiceException(ServiceCode code, String msg)
    {
        super(code, msg);
    }

    public ServiceException(Throwable cause)
    {
        super(cause);
    }
}