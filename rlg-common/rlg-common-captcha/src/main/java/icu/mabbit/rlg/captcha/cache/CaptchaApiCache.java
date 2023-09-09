package icu.mabbit.rlg.captcha.cache;

import icu.mabbit.rlg.common.exception.ProjectException;
import icu.mabbit.rlg.captcha.annotation.CaptchaApi;
import icu.mabbit.rlg.captcha.generator.CaptchaGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.InvocationTargetException;
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
public class CaptchaApiCache
        implements InitializingBean
{
    @Autowired
    private RequestMappingHandlerMapping mappings;

    private static final Map<String, CaptchaGenerator<?>> SERVICE_CACHE = new HashMap<>();

    private static final Set<CaptchaGenerator<?>> GENERATORS = new HashSet<>();

    @Override
    public void afterPropertiesSet()
    {
        mappings
                .getHandlerMethods()
                .forEach((key, value) ->
                         {
                             CaptchaApi captchaApi = value.getMethodAnnotation(CaptchaApi.class);
                             PatternsRequestCondition condition = key.getPatternsCondition();

                             if (condition == null)
                             {
                                 class NullPatternsConditionException
                                         extends ProjectException
                                 {
                                     public NullPatternsConditionException()
                                     {
                                         super("检查配置[spring.mvc.pathmatch.matching-strategy]的值是否为[ant_path_matcher]");
                                     }
                                 }

                                 throw new NullPatternsConditionException();
                             }

                             if (captchaApi == null)
                                 return;

                             condition
                                     .getPatterns()
                                     .forEach(uri -> put(uri, captchaApi.generator()));
                         });
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
     * 根据指定uri获取验证码生成器
     *
     * @param uri uri
     * @return {@link CaptchaGenerator}
     */
    public CaptchaGenerator<?> get(String uri)
    {
        return SERVICE_CACHE.get(uri);
    }

    /**
     * 添加验证码服务uri
     *
     * @param uri       uri
     * @param generator {@link CaptchaGenerator}
     */
    public void put(String uri, Class<? extends CaptchaGenerator<?>> generator)
    {
        CaptchaGenerator<?> g = getGenerator(generator);
        log.debug("Detected captcha api: uri[{}], captcha generator: [{}]", uri, g);
        SERVICE_CACHE.put(uri, g);
    }

    private static CaptchaGenerator<?> getGenerator(Class<? extends CaptchaGenerator<?>> generator)
    {
        CaptchaGenerator<?> g = null;

        for (CaptchaGenerator<?> v : GENERATORS)
            if (v.getClass() == generator)
                g = v;

        try
        {
            if (g == null)
            {
                g = generator.getConstructor().newInstance();
                GENERATORS.add(g);
            }
        }
        catch (NoSuchMethodException e)
        {
            throw new ProjectException("验证码生成器必须包含一个空参构造方法", e);
        }
        catch (InvocationTargetException | InstantiationException | IllegalAccessException e)
        {
            throw new ProjectException(e);
        }

        return g;
    }
}