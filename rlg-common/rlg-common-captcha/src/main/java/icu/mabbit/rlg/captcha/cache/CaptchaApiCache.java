package icu.mabbit.rlg.captcha.cache;

import icu.mabbit.rlg.captcha.enums.CaptchaGetter;
import icu.mabbit.rlg.captcha.annotation.CaptchaApi;
import icu.mabbit.rlg.captcha.generator.CaptchaGenerator;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * <h2>验证码接口存储器</h2>
 * 存储所有被{@link CaptchaApi}标记的接口的uri和验证码生成器
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/7 12:57
 */
@Slf4j
@Component
@Setter(onMethod_ = @Autowired)
public class CaptchaApiCache
        implements InitializingBean
{
    private RequestMappingHandlerMapping mappings;

    private static final Map<String, CaptchaGetter> SERVICE_CACHE = new HashMap<>();

    @Override
    public void afterPropertiesSet()
    {
        mappings
                .getHandlerMethods()
                .forEach(
                        (k, v) ->
                        {
                            CaptchaApi captchaApi = v.getMethodAnnotation(CaptchaApi.class);
                            PatternsRequestCondition condition = k.getPatternsCondition();

                            if (condition == null)
                            {
                                log.error("Need to configure [spring.mvc.pathmatch.matching-strategy] as [ant_path_matcher]");
                                throw new NullPointerException();
                            }

                            if (captchaApi == null)
                                return;

                            condition
                                    .getPatterns()
                                    .forEach(uri -> put(uri, captchaApi.type()));
                        }
                );

        log.info("Captcha-api parse successfully, the number of captcha apis: [{}]", SERVICE_CACHE.size());
    }

    /**
     * @param uri uri
     * @return 是否存在指定uri的接口
     */
    public boolean has(String uri)
    {
        return SERVICE_CACHE.containsKey(uri);
    }

    /**
     * 根据指定uri获取验证码获取器
     *
     * @param uri uri
     * @return {@link CaptchaGenerator}
     */
    public CaptchaGetter get(String uri)
    {
        return SERVICE_CACHE.get(uri);
    }

    /**
     * 添加验证码服务uri
     *
     * @param uri  uri
     * @param getter {@link CaptchaGetter}
     */
    public void put(String uri, CaptchaGetter getter)
    {
        log.trace("Detected captcha uri: [{}], captcha getter: {}", uri, getter);
        SERVICE_CACHE.put(uri, getter);
    }
}