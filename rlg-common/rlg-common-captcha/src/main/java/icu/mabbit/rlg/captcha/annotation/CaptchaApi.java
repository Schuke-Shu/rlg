package icu.mabbit.rlg.captcha.annotation;

import icu.mabbit.rlg.captcha.enums.CaptchaGetter;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h2>验证码api标记</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/7 10:31
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface CaptchaApi
{
    @AliasFor("type")
    CaptchaGetter value() default CaptchaGetter.RANDOM_SIX_CHAR;

    @AliasFor("value")
    CaptchaGetter type() default CaptchaGetter.RANDOM_SIX_CHAR;
}