package icu.mabbit.rlg.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <h2>项目信息配置</h2>
 *
 * @author 一只枫兔
 * @Date 2023/8/30 14:43
 */
@Component
@ConfigurationProperties(ProjectInfo.PREFIX)
public class ProjectInfo
{
    public static final String PREFIX = "rlg.project-info";

    /**
     * 项目名称
     */
    public String name;
    /**
     * 项目版本
     */
    public String version;
    /**
     * 版权年份
     */
    public Integer copyrightYear;
}