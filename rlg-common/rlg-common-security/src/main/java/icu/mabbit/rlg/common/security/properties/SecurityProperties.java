package icu.mabbit.rlg.common.security.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <h2>安全认证配置</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/5 11:50
 */
@Component
@ConfigurationProperties(SecurityProperties.PREFIX)
@Getter
@Setter
@Accessors(chain = true)
public class SecurityProperties
{
    public static final String PREFIX = "rlg.security";

    /**
     * uri白名单
     */
    private String[] uriWhiteList = {};
}