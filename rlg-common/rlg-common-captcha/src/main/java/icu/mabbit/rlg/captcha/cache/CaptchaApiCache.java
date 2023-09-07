package icu.mabbit.rlg.captcha.cache;

import icu.mabbit.rlg.common.exception.ProjectException;
import icu.mabbit.rlg.captcha.annotation.CaptchaApi;
import icu.mabbit.rlg.captcha.generator.CaptchaGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>验证码接口存储器</h2>
 * 存储所有被{@link CaptchaApi}标记的接口的uri和验证码生成器
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/7 12:57
 */
@Component
public class CaptchaApiCache
        implements InitializingBean
{
    @Autowired
    private RequestMappingHandlerMapping mappings;

    private static final Map<String, CaptchaGenerator<?>> CACHE = new HashMap<>();

    @Override
    public void afterPropertiesSet()
    {
        List<CaptchaGenerator<?>> list = new ArrayList<>();

        mappings
                .getHandlerMethods()
                .forEach((key, value) ->
                         {
                             CaptchaApi captchaApi = value.getMethodAnnotation(CaptchaApi.class);
                             PatternsRequestCondition condition = key.getPatternsCondition();

                             if (captchaApi == null || condition == null)
                                 return;

                             condition
                                     .getPatterns()
                                     .forEach(
                                             uri ->
                                             {
                                                 Class<? extends CaptchaGenerator<?>> cls = captchaApi.generator();
                                                 CaptchaGenerator<?> generator = null;

                                                 for (CaptchaGenerator<?> c : list)
                                                     if (c.getClass() == cls)
                                                         generator = c;

                                                 try
                                                 {
                                                     if (generator == null)
                                                     {
                                                         generator = cls.getConstructor().newInstance();
                                                         list.add(generator);
                                                     }
                                                 }
                                                 catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                                                        IllegalAccessException e)
                                                 {
                                                     throw new ProjectException(e);
                                                 }

                                                 CACHE.put(uri, generator);
                                             }
                                     );
                         });
    }

    /**
     * @param uri uri
     * @return 是否存在指定uri的接口
     */
    public boolean has(String uri)
    {
        return CACHE.containsKey(uri);
    }

    /**
     * 根据指定uri获取验证码生成器
     *
     * @param uri uri
     * @return {@link CaptchaGenerator}
     */
    public CaptchaGenerator<?> generator(String uri)
    {
        return CACHE.get(uri);
    }
}