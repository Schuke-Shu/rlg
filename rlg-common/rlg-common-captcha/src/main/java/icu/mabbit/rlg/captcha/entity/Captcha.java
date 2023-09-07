package icu.mabbit.rlg.captcha.entity;

import java.io.Serializable;

/**
 * <h2>验证码</h2>
 * 验证码可能有多种形式，例如文字、图片...
 *
 * @param <T> 验证码类型
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/7 11:14
 */
public class Captcha<T extends Serializable>
{
    /**
     * 用于展示的验证码
     */
    private final T body;
    /**
     * 验证码字符串
     */
    private final String code;

    public Captcha(T body, String code)
    {
        this.body = body;
        this.code = code;
    }

    public T body()
    {
        return body;
    }

    public String code()
    {
        return code;
    }
}