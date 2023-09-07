package icu.mabbit.rlg.security.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * <h2>用户信息</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/7 10:35
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserDetail
        extends User
        implements Serializable
{
    private final Long id;
    private final String uuid;
    /**
     * 头像地址
     */
    private final String avatarUrl;
    /**
     * 手机号
     */
    private final String phone;
    /**
     * 邮箱
     */
    private final String email;
    /**
     * 用户当前登录的ip地址
     */
    private final String ip;

    public UserDetail(
            Long id,
            String uuid,
            String username,
            String password,
            String phone,
            String email,
            String ip,
            String avatarUrl,
            boolean enabled,
            Collection<? extends GrantedAuthority> authorities
    )
    {
        super(username, password, enabled, true, true, true, authorities);
        this.id = id;
        this.uuid = uuid;
        this.phone = phone;
        this.email = email;
        this.ip = ip;
        this.avatarUrl = avatarUrl;
    }
}
