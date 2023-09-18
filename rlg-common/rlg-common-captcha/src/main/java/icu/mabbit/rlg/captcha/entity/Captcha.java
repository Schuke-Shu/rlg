package icu.mabbit.rlg.captcha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import icu.mabbit.mdk4j.core.util.StringUtil;
import icu.mabbit.rlg.common.exception.ProjectException;
import lombok.Getter;

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
public class Captcha<T extends Serializable>
        implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 用于展示的验证码
     */
    @Getter
    private final T body;
    /**
     * 验证码字符串
     */
    @Getter
    @JsonIgnore
    private final String code;

    private String uuid;

    public Captcha(T body, String code)
    {
        this.body = body;
        this.code = code;
    }

    public String getUuid()
    {
        if (uuid == null)
            throw new ProjectException("Captcha uuid is null, need to call [uuid(String, String)] first");

        return uuid;
    }

    public Captcha<?> setUuid(String uri, String account)
    {
        uuid = UUID.nameUUIDFromBytes(
                        StringUtil.joiner("/")
                                .add(uri)
                                .add(account)
                                .toString()
                                .getBytes()
                )
                .toString();
        return this;
    }
}