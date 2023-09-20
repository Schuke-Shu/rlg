package icu.mabbit.rlg.common.enums;

/**
 * <h2>布尔值枚举</h2>
 * <p>定义 true == 1，false == 0</p>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/20 18:27
 */
public enum Boolean
{
    FALSE,
    TRUE,
    ;

    public int num()
    {
        return ordinal();
    }

    public boolean bool()
    {
        return ordinal() == TRUE.ordinal();
    }

    /**
     * 将数值转换为布尔枚举
     *
     * @param i 数值
     * @return 参数为1时返回 {@link Boolean#TRUE}，其他任意值返回 {@link Boolean#FALSE}
     */
    public static Boolean of(int i)
    {
        return of(i == 1);
    }

    /**
     * 将布尔值转换为布尔枚举
     *
     * @param b 布尔值
     * @return 若为true返回 {@link Boolean#TRUE}，否则返回 {@link Boolean#FALSE}
     */
    public static Boolean of(boolean b)
    {
        return b ? TRUE : FALSE;
    }

    /**
     * 将数值转换为布尔值
     *
     * @param i 数值
     * @return 参数为1时返回 {@code true}，其他任意值返回 {@code false}
     */
    public boolean convert(int i)
    {
        return i == 1;
    }

    /**
     * 将布尔值转换为数值
     *
     * @param b 布尔值
     * @return 若为true返回 {@code 1}，否则返回 {@link 0}
     */
    public static int convert(boolean b)
    {
        return b ? 1 : 0;
    }
}