package icu.mabbit.rlg.captcha.annotation;

import icu.mabbit.rlg.captcha.generator.CaptchaGenerator;
import icu.mabbit.rlg.captcha.generator.RandomSixCharCaptcha;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <h2>验证码api标记</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/7 10:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaptchaApi
{
    @AliasFor("generator")
    Class<? extends CaptchaGenerator<?>> value() default RandomSixCharCaptcha.class;

    @AliasFor("value")
    Class<? extends CaptchaGenerator<?>> generator() default RandomSixCharCaptcha.class;
}