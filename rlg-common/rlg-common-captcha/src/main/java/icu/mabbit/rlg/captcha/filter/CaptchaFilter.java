package icu.mabbit.rlg.captcha.filter;

import icu.mabbit.rlg.captcha.cache.CaptchaApiCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h2>验证码过滤器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/15 9:47
 */
@Slf4j
public class CaptchaFilter
        extends OncePerRequestFilter
{
    private CaptchaApiCache cache;

    private static final HttpMethod HTTP_METHOD = HttpMethod.GET;

    private static final String REQUEST_PATTERN = "/code";

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
    {
        // TODO 验证码功能
    }

    private boolean requestMatches(HttpServletRequest req)
    {
        String method = req.getMethod();
        boolean match = HTTP_METHOD == HttpMethod.resolve(method) && req.getRequestURI().startsWith(REQUEST_PATTERN);

        log.trace("Request method: {}, result of captcha matches: {}", method, match);

        return match;
    }
}