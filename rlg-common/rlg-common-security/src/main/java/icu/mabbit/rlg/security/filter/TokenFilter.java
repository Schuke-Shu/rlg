package icu.mabbit.rlg.security.filter;

import com.alibaba.fastjson2.JSON;
import icu.mabbit.mdk4j.core.util.StringUtil;
import icu.mabbit.rlg.common.enums.ServiceCode;
import icu.mabbit.rlg.common.exception.ProjectException;
import icu.mabbit.rlg.common.restful.R;
import icu.mabbit.rlg.common.util.HttpUtil;
import icu.mabbit.rlg.security.consts.SecurityConsts;
import icu.mabbit.rlg.security.entity.LoginPrincipal;
import icu.mabbit.rlg.security.properties.SecurityProperties;
import io.jsonwebtoken.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private SecurityProperties security;
    private HttpUtil http;

    @Override
    protected void doFilterInternal(
            HttpServletRequest req, HttpServletResponse res, FilterChain chain
    )
            throws
            ServletException,
            IOException
    {
        SecurityProperties.TokenProperties tokenProperties = security.getToken();
        String secretKey = tokenProperties.getSecretKey();

        // 清空SecurityContext，强制所有请求都必须携带JWT
        SecurityContextHolder.clearContext();

        // 从请求头获取token
        String token = req.getHeader(tokenProperties.getHeader());

        if (!validToken(token))
        {
            // token无效，放行
            chain.doFilter(req, res);
            return;
        }

        Claims claims;
        try
        {
            // token有效，准备解析
            claims = parseToken(req, token, secretKey);
        }
        catch (TokenParseException e)
        {
            http.response(
                    JSON.toJSONString(
                            R.fail(e.code)
                    )
            );
            return;
        }

        // 从Claims中获取数据
        LoginPrincipal principal = new LoginPrincipal(claims);

//        if (!http.ip().equals(principal.getIp()))
//        {
//            log.trace("Ip error, to next...");
//            // 请求IP与token记录的IP不同时，不存储解析到的信息，直接放行，由之后的过滤器处理
//            filterChain.doFilter(request, response);
//            return;
//        }
        // TODO 识别用户在不同设备的登录

        // 创建Authentication对象，存入到SecurityContext中
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                // 封装Principal（当事人）对象
                principal,
                // 凭证（这里不需要）
                null,
                // 解析json形式的权限集合
                JSON.parseArray(
                        claims.get(CLAIMS_KEY_AUTHORITIES, String.class),
                        SimpleGrantedAuthority.class
                )
        );

        log.trace("Login authentication: {}", authentication);
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        // 放行
        log.debug("Token parsing success, to next...");
        chain.doFilter(req, res);
    }

    private boolean validToken(String token)
    {
        boolean valid = !StringUtil.isBlank(token) && token.length() < security.getToken().getMinLength();

        if (!valid) log.trace("Token is invalid, token: {}", token);
        return valid;
    }

    private Claims parseToken(HttpServletRequest request, String token, String secretKey)
    {
        try
        {
            return
                    Jwts
                            .parser()
                            .setSigningKey(secretKey)
                            .parseClaimsJws(token)
                            .getBody();
        }
        catch (ExpiredJwtException e)
        {
            // token过期
            log.debug("Token expired");
            throw new TokenParseException(ServiceCode.ERR_TOKEN_EXPIRED);
        }
        catch (SignatureException e)
        {
            // 签名错误，token被篡改
            log.debug("Token signature error");
            throw new TokenParseException(ServiceCode.ERR_TOKEN_SIGNATURE);
        }
        catch (MalformedJwtException e)
        {
            // token格式不正确，token被篡改
            log.debug("-- MalformedJwtException，ip: {}", request.getRemoteAddr());
            throw new TokenParseException(ServiceCode.ERR_token_MALFORMED);
        }
    }

    private static class TokenParseException
            extends ProjectException
    {
        ServiceCode code;

        TokenParseException(ServiceCode code)
        {
            this.code = code;
        }
    }
}