package icu.mabbit.rlg.common.captcha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.mabbit.mdk4j.core.lang.Assert;
import icu.mabbit.mdk4j.core.util.StringUtil;
import icu.mabbit.rlg.common.captcha.exception.CaptchaException;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * <h2>验证码</h2>
 * 验证码可能有多种形式，例如文字、图片...
 *
 * @param <T> 验证码类型
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/16 16:46
 */
@Data
public class Captcha<T extends Serializable>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 用于展示的验证码
     */
    private final T body;
    /**
     * 验证码字符串
     */
    @JsonIgnore
    private final String code;

    public Captcha(T body, String code)
    {
        Assert.notNull(body, CaptchaException::new);
        Assert.notNull(code, CaptchaException::new);

        this.body = body;
        this.code = code;
    }

    public String uuid(String uri, String account)
    {
        Assert.paramNotNull(uri);
        Assert.paramNotNull(account);

        return
                UUID
                        .nameUUIDFromBytes(
                                StringUtil
                                        .joiner("/")
                                        .add(uri)
                                        .add(account)
                                        .toString()
                                        .getBytes()
                        )
                                .toString();
    }
}