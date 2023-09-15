package icu.mabbit.rlg.common.restful;

/**
 * <h2>业务处理成功返回的数据</h2>
 *
 * @param <D> 返回给客户端的数据类型
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/2 16:36
 */
public class SuccessResult<D> extends JsonResult<D>
{
    private static final long serialVersionUID = 1L;

    public SuccessResult()
    {
        this(null);
    }

    public SuccessResult(D data)
    {
        super(data);
    }
}