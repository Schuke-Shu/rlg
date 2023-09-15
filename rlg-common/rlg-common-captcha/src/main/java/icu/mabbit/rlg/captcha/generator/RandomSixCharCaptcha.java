package icu.mabbit.rlg.captcha.generator;

import icu.mabbit.rlg.captcha.entity.Captcha;

/**
 * <h2>随机六位字符验证码</h2>
 * 包含数字与大小写字母
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/7 11:29
 */
public class RandomSixCharCaptcha
        implements CaptchaGenerator<String>
{
    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CHARS_LEN = 62;

    @Override
    public Captcha<String> captcha()
    {
        String s =
                new String(
                        new char[]{
                                random(), random(), random(),
                                random(), random(), random(),
                        }
                );

        return new Captcha<>(s, s);

    }

    private char random()
    {
        return
                CHARS.charAt(
                        (int)
                        (Math.random() * CHARS_LEN)
                );
    }
}