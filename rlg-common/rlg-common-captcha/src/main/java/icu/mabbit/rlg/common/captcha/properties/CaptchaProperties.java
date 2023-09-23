package icu.mabbit.rlg.common.captcha.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <h2>验证码相关配置</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/22 17:41
 */
@Component
@Getter
@Setter
@Accessors(chain = true)
@ConfigurationProperties(prefix = CaptchaProperties.PREFIX)
public class CaptchaProperties
{
    public static final String PREFIX = "rlg.captcha";

    /**
     * 有效时长，单位：分钟
     */
    private Integer validTime = 10;
}