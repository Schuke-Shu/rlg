package icu.mabbit.rlg.security.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h2>登录认证失败处理器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/7 10:44
 */
@Slf4j
@Component
public class LoginAuthenticationFailHandler
        implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest req,
            HttpServletResponse res,
            AuthenticationException e
    )
    {
        log.debug("Access LoginAuthenticationFailHandler onAuthenticationFailure()");
        log.debug("-- {}, msg: {}", e.getClass().getSimpleName(), e.getMessage());
        // TODO 返回错误信息
    }
}