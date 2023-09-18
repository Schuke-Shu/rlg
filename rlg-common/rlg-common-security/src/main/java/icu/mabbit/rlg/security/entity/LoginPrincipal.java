package icu.mabbit.rlg.security.entity;

import icu.mabbit.rlg.security.consts.SecurityConsts;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <h2>当事人</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/7 10:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LoginPrincipal
        implements Serializable, SecurityConsts
{
    private String uuid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * ip
     */
    private String ip;

    public LoginPrincipal(Claims claims)
    {
        uuid = claims.get(CLAIMS_KEY_UUID, String.class);
        username = claims.get(CLAIMS_KEY_USERNAME, String.class);
        phone = claims.get(CLAIMS_KEY_PHONE, String.class);
        email = claims.get(CLAIMS_KEY_EMAIL, String.class);
    }
}
