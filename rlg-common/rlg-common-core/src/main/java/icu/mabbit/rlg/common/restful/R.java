package icu.mabbit.rlg.common.restful;

import icu.mabbit.rlg.common.enums.ServiceCode;
import icu.mabbit.rlg.common.exception.ServiceException;

/**
 * <h2>包装业务返回信息的工具类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/2 16:43
 */
public abstract class R
{
    /**
     * 业务处理成功，无返回数据
     *
     * @return {@link SuccessResult}
     */
    public static SuccessResult<?> ok()
    {
        return new SuccessResult<>();
    }

    /**
     * 业务处理成功，有返回数据
     *
     * @param data 业务处理完成后获得的数据
     * @return 业务处理成功
     * @param <D> {@link SuccessResult}
     */
    public static <D> SuccessResult<D> OK(D data)
    {
        return new SuccessResult<>(data);
    }

    /**
     * 业务处理失败
     *
     * @param code 业务状态码
     * @param msg 反馈信息
     * @return {@link FailedResult}
     */
    public static FailedResult fail(ServiceCode code, String msg)
    {
        return new FailedResult(code, msg);
    }

    /**
     * 业务处理失败
     *
     * @param e 业务异常
     * @return {@link FailedResult}
     */
    public static <E extends ServiceException> FailedResult fail(E e)
    {
        return new FailedResult(e.code(), e.getMessage());
    }
}