package icu.mabbit.rlg.common.swagger.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <h2>swagger文档参数</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-swagger
 * @Date 2023/9/4 14:41
 */
@Component
@ConfigurationProperties(SwaggerProperties.PREFIX)
@Getter
@Setter
@Accessors(chain = true)
public class SwaggerProperties
{
    public static final String PREFIX = "rlg.swagger";

    /**
     * 版本号
     */
    private String version = "";
    /**
     * Controller包路径
     */
    private String basePackage = "";
    /**
     * 分组名称
     */
    private String groupName = "";
    /**
     * 主机名称
     */
    private String host = "";
    /**
     * 标题
     */
    private String title = "";
    /**
     * 简介
     */
    private String description = "";
    /**
     * 服务条款URL
     */
    private String termOfServiceUrl = "";
    /**
     * 联系人姓名
     */
    private String contactName = "";
    /**
     * 联系网址
     */
    private String contactUrl = "";
    /**
     * 联系邮箱
     */
    private String contactEmail = "";
}