package icu.mabbit.rlg.security.properties;

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
    /**
     * token配置
     */
    private TokenProperties token;

    /**
     * <h2>token配置</h2>
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class TokenProperties
    {
        /**
         * token签名算法
         */
        private String algorithm = "HS256";
        /**
         * token类型
         */
        private String type = "JWT";
        /**
         * 解析和生成token使用的key
         */
        private String secretKey = "RedLeafGarden-JsonWebToken-SecretKey";
        /**
         * token有效时长（单位：分钟）
         */
        private Integer usableMinutes = 10080;
        /**
         * 长度下限
         */
        private Integer minLength = 105;
        /**
         * 存放token的请求头的名称
         */
        private String header = "Authorization";
        /**
         * token可刷新临期时间（单位：分钟），临期时间低于该值才可刷新
         */
        private Integer refreshAllowTime = 1500;
    }
}