package icu.mabbit.rlg.common.security.token;

import icu.mabbit.mdk4j.core.lang.Assert;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * <h2>认证令牌</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/19 13:56
 */
public class AuthToken
        extends AbstractAuthenticationToken
{
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 登录时存放token，其他请求用于存放解析token后获取的当事人
     */
    private final Object principal;

    public AuthToken(
            Object principal,
            Collection<? extends GrantedAuthority> authorities
    )
    {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // setAuthenticated()已经被重写为传入true值会报错，所以这里使用父类的方法
    }

    @Override
    public Object getPrincipal()
    {
        return this.principal;
    }

    @Override
    public Object getCredentials()
    {
        throw new RuntimeException();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated)
            throws IllegalArgumentException
    {
        Assert.isFalse(
                isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority ls instead"
        );
        super.setAuthenticated(false);
    }

    /**
     * 若已经验证过，返回父类的toString()，否则只返回token
     */
    @Override
    public String toString()
    {
        return isAuthenticated() ? super.toString() : "Token: " + principal;
    }
}