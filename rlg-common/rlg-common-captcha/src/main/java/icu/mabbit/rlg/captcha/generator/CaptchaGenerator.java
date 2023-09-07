package icu.mabbit.rlg.captcha.generator;

import icu.mabbit.rlg.captcha.entity.Captcha;

import java.io.Serializable;

/**
 * <h2>验证码生成器接口</h2>
 *
 * @param <T> 验证码类型
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/7 11:06
 */
public interface CaptchaGenerator<T extends Serializable>
{
    /**
     * 生成验证码
     *
     * @return {@link Captcha}
     */
    Captcha<T> captcha();
}