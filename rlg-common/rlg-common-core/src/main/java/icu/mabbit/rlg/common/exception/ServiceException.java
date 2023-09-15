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
    private static final long serialVersionUID = 1L;

    private final int code;

    public ServiceException()
    {
        this(ServiceCode.ERR_UNKNOWN);
    }

    public ServiceException(ServiceCode code)
    {
        super(code.msg());
        this.code = code.code();
    }

    public int code()
    {
        return code;
    }
}