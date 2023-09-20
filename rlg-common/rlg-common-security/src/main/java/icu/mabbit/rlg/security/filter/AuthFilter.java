package icu.mabbit.rlg.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import icu.mabbit.mdk4j.core.lang.Assert;
import icu.mabbit.rlg.security.config.AuthManager;
import icu.mabbit.rlg.security.token.AuthToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * <h2>认证过滤器</h2>
 * <p>客户端登录时需要提供一个"LoginWay"的请求头，值为"pwd"或"code"，表明是通过密码还是验证码登录</p>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/19 14:33
 */
public class AuthFilter
        extends AbstractAuthenticationProcessingFilter
{
    private static final String LOGIN_URI = "/login";
    private static final String POST = "POST";
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(LOGIN_URI, POST);

    public AuthFilter(AuthenticationManager authenticationManager)
    {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException
    {
        // 请求方法
        String method = request.getMethod();

        // 登录方法只允许post
        if (!method.equals(POST))
            throw new AuthenticationServiceException("Authentication method not supported: " + method);

        // AuthenticationManager必须是自定义的Manager
        AuthenticationManager manager = getAuthenticationManager();
        Assert.isInstanceOf(AuthManager.class, manager, () -> new AuthenticationServiceException("The [AuthenticationManager] must be [icu.mabbit.rlg.security.config.AuthManager]"));

        return manager.authenticate(new AuthToken());
    }
}