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
    private final int code;

    public ServiceException()
    {
        code = ServiceCode.ERR_UNKNOWN.code();
    }

    public ServiceException(int code)
    {
        this.code = code;
    }

    public ServiceException(int code, String msg)
    {
        super(msg);
        this.code = code;
    }

    public ServiceException(ServiceCode code)
    {
        this(code.code(), code.msg());
    }

    public int code()
    {
        return code;
    }
}