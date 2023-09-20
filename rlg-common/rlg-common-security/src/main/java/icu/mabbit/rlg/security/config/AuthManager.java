package icu.mabbit.rlg.security.config;

import icu.mabbit.mdk4j.core.lang.Assert;
import icu.mabbit.rlg.security.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.LinkedList;
import java.util.List;

/**
 * <h2>认证管理器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/19 15:37
 */
@Slf4j
public class AuthManager
        implements AuthenticationManager
{
    /**
     * 认证方法池
     */
    private static final List<AuthenticationProvider> PROVIDER_POOL = new LinkedList<>();

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException
    {
        Assert.state(
                !PROVIDER_POOL.isEmpty(),
                () -> new AuthException("Provider pool is empty, not supports authentication (or create a configuration class to add providers)")
        );

        Class<? extends Authentication> test = authentication.getClass();
        int current = 0;
        for (AuthenticationProvider provider : PROVIDER_POOL)
        {
            if (!provider.supports(test))
                continue;

            log.trace("Authenticating request with {} ({}/{})", provider.getClass().getSimpleName(), ++current, PROVIDER_POOL.size());

            return provider.authenticate(authentication);
        }

        return null;
    }

    public AuthManager addProvider(AuthenticationProvider provider)
    {
        PROVIDER_POOL.add(provider);
        return this;
    }
}