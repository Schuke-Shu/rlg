package icu.mabbit.rlg.common.restful;

import icu.mabbit.rlg.common.enums.ServiceCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <h2>restful风格JSON数据主体类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/2 15:56
 */
@Data
@Accessors(chain = true)
public class JsonResult<D>
        implements Serializable
{
    /**
     * 业务状态码
     */
    private final int code;
    /**
     * 业务处理成功时返回的数据
     */
    private final D data;
    /**
     * 业务处理失败时返回的信息
     */
    private final String msg;

    public JsonResult(D data)
    {
        this.code = ServiceCode.OK.code();
        this.data = data;
        this.msg = null;
    }

    public JsonResult(int code, String msg)
    {
        this.code = code;
        this.data = null;
        this.msg = msg;
    }

    public int code()
    {
        return code;
    }

    public D data()
    {
        return data;
    }

    public String msg()
    {
        return msg;
    }
}