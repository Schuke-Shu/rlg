package icu.mabbit.rlg.common.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <h2>配置文件错误</h2>
 * 配置文件出现错误，例如缺少配置或配置不符合要求
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/16 15:43
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PropertiesException
        extends ProjectException
{
    /**
     * 错误配置项名称
     * <p>
     *     例如：rlg.project-info.name
     * </p>
     */
    private final String optional;

    public PropertiesException(String optional)
    {
        this.optional = optional;
    }

    public PropertiesException(String optional, String msg)
    {
        super(msg);
        this.optional = optional;
    }
}