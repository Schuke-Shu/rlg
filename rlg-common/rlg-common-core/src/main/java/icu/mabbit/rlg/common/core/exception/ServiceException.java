package icu.mabbit.rlg.common.core.exception;

import icu.mabbit.rlg.common.core.enums.ServiceCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <h2>全局业务异常</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/2 16:51
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
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
}