package icu.mabbit.rlg.common.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <h2>模块信息</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/4 14:34
 */
@Component
@ConfigurationProperties(ModuleInfo.PREFIX)
@Getter
@Setter
@Accessors(chain = true)
public class ModuleInfo
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static final String PREFIX = "rlg.module-info";

    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
}