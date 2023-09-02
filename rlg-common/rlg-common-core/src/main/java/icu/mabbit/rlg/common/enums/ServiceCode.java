package icu.mabbit.rlg.common.enums;

/**
 * <h2>业务状态码</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/2 15:58
 */
public enum ServiceCode
{
    SUCCESS("0")
    ,;

    private final String value;

    ServiceCode(String value)
    {
        this.value = value;
    }

    public String value()
    {
        return value;
    }
}