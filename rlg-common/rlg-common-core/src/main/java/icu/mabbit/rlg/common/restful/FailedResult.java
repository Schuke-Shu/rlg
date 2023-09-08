package icu.mabbit.rlg.common.restful;

/**
 * <h2>业务处理失败返回的数据</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/2 16:36
 */
public class FailedResult extends JsonResult<Object>
{
    public FailedResult(int code, String msg)
    {
        super(code, msg);
    }
}