package icu.mabbit.rlg.common.captcha.enums;

import icu.mabbit.rlg.common.captcha.entity.Captcha;
import icu.mabbit.rlg.common.captcha.generator.CaptchaGenerator;

/**
 * <h2>验证码获取器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/16 17:29
 */
public enum CaptchaGetter
{
    RANDOM_SIX_CHAR
            {
                @Override
                public Captcha<?> captcha()
                {
                    return CaptchaGenerator.randomStr(CHARS, DEFAULT_NUM);
                }
            },
    RANDOM_SIX_DIGITS
            {
                @Override
                public Captcha<?> captcha()
                {
                    return CaptchaGenerator.randomStr(DIGITS, DEFAULT_NUM);
                }
            },
    ;

    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String DIGITS = "0123456789";
    public static final int DEFAULT_NUM = 6;

    /**
     * 生成验证码
     *
     * @return {@link Captcha}
     */
    public Captcha<?> captcha()
    {
        throw new AbstractMethodError();
    }
}