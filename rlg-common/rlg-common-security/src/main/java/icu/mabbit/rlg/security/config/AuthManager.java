package icu.mabbit.rlg.security.config;

import icu.mabbit.mdk4j.core.lang.Assert;
import icu.mabbit.rlg.common.restful.R;
import icu.mabbit.rlg.common.util.ServletUtil;
import icu.mabbit.rlg.security.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

import static icu.mabbit.rlg.common.enums.ServiceCode.ERR_LOGIN;

/**
 * <h2>认证管理器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/19 15:37
 */
@Slf4j
@Component
public class AuthManager
        implements AuthenticationManager
{
    /**
     * 认证方法池
     */
    private final List<AuthenticationProvider> providerPool = new LinkedList<>();

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException
    {
        Assert.state(
                !providerPool.isEmpty(),
                () -> new AuthException("Provider pool is empty, not supports authentication (or create a configuration class to add providers)")
        );

        Class<? extends Authentication> test = authentication.getClass();
        int current = 0;
        int size = providerPool.size();
        Authentication result = null;
        for (AuthenticationProvider provider : providerPool)
        {
            if (!provider.supports(test))
                continue;

            log.trace("Authenticating request with {} ({}/{})", provider.getClass().getSimpleName(), ++current, size);

            try
            {
                result = provider.authenticate(authentication);
            }
            catch (AuthException e)
            {
                ServletUtil.response(R.fail(ERR_LOGIN));
            }
            String token = (String) authentication.getPrincipal();

            log.trace("Authentication success, info: {}", token);

            // 获取token并返回到客户端
            ServletUtil.response(R.ok(token));

            return result;
        }

        return null;
    }

    public AuthManager addProvider(AuthenticationProvider provider)
    {
        providerPool.add(provider);
        return this;
    }
}