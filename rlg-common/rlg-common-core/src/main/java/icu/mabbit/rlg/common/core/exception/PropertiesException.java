package icu.mabbit.rlg.common.core.exception;

/**
 * <h2>配置文件错误</h2>
 * 配置文件出现错误，例如缺少配置或配置不符合要求
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/16 15:43
 */
public class PropertiesException
        extends ProjectException
{
    /**
     * 错误配置项前缀
     * <p>
     *     例如：rlg.project-info
     * </p>
     */
    private final String prefix;

    /**
     * 错误配置项名称
     * <p>
     *     例如：name
     * </p>
     */
    private final String errorOpt;

    public PropertiesException(String prefix, String errorOpt)
    {
        this.prefix = prefix;
        this.errorOpt = errorOpt;
    }

    public PropertiesException(String prefix, String errorOpt, String msg)
    {
        super(msg);
        this.prefix = prefix;
        this.errorOpt = errorOpt;
    }

    public String errorOpt()
    {
        return errorOpt;
    }
}