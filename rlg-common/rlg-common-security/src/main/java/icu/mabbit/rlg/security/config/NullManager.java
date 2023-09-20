package icu.mabbit.rlg.security.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * <h2>空实现认证管理器</h2>
 * <p>用于占位</p>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/20 14:23
 */
public class NullManager implements AuthenticationManager
{
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException
    {
        throw new AuthenticationServiceException("The auth is not supported in this module");
    }
}