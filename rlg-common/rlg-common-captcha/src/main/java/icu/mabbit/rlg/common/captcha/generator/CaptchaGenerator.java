package icu.mabbit.rlg.common.captcha.generator;

import icu.mabbit.rlg.common.captcha.entity.Captcha;

/**
 * <h2>验证码处理器接口</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/7 11:06
 */
public abstract class CaptchaGenerator
{
    /**
     * 随机字符验证码，每次从指定的字符序列中随机取出某个位置的字符，取{@code num}个，然后拼成新的字符串
     *
     * @param temp 字符序列模板
     * @param num 验证码位数
     * @return {@link Captcha}
     */
    public static Captcha<String> randomStr(String temp, int num)
    {
        int len = temp.length();

        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < num; i++)
            sb.append(
                    temp.charAt(
                            (int) (Math.random() * len)
                    )
            );
        String s = sb.toString();
        return new Captcha<>(s, s);
    }
}