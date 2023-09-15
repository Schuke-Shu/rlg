package icu.mabbit.rlg.common.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <h2>项目信息</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/4 14:34
 */
@Component
@ConfigurationProperties(ProjectInfo.PREFIX)
@Getter
@Setter
@Accessors(chain = true)
public class ProjectInfo
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static final String PREFIX = "rlg.project-info";

    /**
     * 名称
     */
    private String name = "红叶园多功能生活服务平台";
    /**
     * 版本
     */
    private String version;
}