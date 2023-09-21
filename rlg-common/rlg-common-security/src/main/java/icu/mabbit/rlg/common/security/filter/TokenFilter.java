package icu.mabbit.rlg.common.security.filter;

import com.alibaba.fastjson2.JSON;
import icu.mabbit.mdk4j.core.util.StringUtil;
import icu.mabbit.rlg.common.security.properties.TokenProperties;
import icu.mabbit.rlg.common.security.token.AuthToken;
import icu.mabbit.rlg.common.security.util.SecurityUtil;
import icu.mabbit.rlg.common.security.util.TokenUtil;
import icu.mabbit.rlg.common.core.restful.R;
import icu.mabbit.rlg.common.core.util.ServletUtil;
import icu.mabbit.rlg.common.security.consts.SecurityConsts;
import icu.mabbit.rlg.common.security.entity.Principal;
import io.jsonwebtoken.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h2>token过滤解析器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/15 13:38
 */
@Slf4j
@Component
@Setter(onMethod_ = @Autowired)
public class TokenFilter
        extends OncePerRequestFilter
        implements SecurityConsts
{
    private TokenUtil tokenUtil;
    private TokenProperties tokenProperties;

    @Override
    protected void doFilterInternal(
            HttpServletRequest req, HttpServletResponse res, FilterChain chain
    )
            throws ServletException, IOException
    {
        // 清空SecurityContext，强制所有请求都必须携带JWT
        SecurityContextHolder.clearContext();

        // 从请求头获取token
        String token = req.getHeader(tokenProperties.getHeader());

        if (!valid(token))
        {
            log.debug("Token parsed failed, to next...");
            // token无效，放行
            chain.doFilter(req, res);
            return;
        }

        Claims claims;
        try
        {
            // token有效，准备解析
            claims = tokenUtil.parse(token);
        }
        catch (TokenUtil.TokenParseException e)
        {
            ServletUtil.response(R.fail(e));
            return;
        }

        // 从Claims中获取数据
        Principal principal = new Principal(claims);

        // 创建Authentication对象
        Authentication auth = new AuthToken(
                // 封装Principal（当事人）对象
                principal,
                // 解析json形式的权限集合
                JSON.parseArray(
                        claims.get(CLAIMS_KEY_AUTHORITIES, String.class),
                        SimpleGrantedAuthority.class
                )
        );

        log.trace("Login authentication: {}", auth);

        // 存入到SecurityContext中
        SecurityUtil.setAuthentication(auth);

        // 放行
        log.debug("Token parsed success, to next...");
        chain.doFilter(req, res);
    }

    private boolean valid(String token)
    {
        boolean valid = !StringUtil.isBlank(token) && token.length() < tokenProperties.getMinLength();

        if (!valid) log.trace("Token is invalid, token: {}", token);
        return valid;
    }
}