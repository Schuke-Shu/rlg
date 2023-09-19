package icu.mabbit.rlg.security.util;

import icu.mabbit.mdk4j.core.lang.Assert;
import icu.mabbit.rlg.common.enums.ServiceCode;
import icu.mabbit.rlg.common.exception.PropertiesException;
import icu.mabbit.rlg.common.exception.ServiceException;
import icu.mabbit.rlg.security.properties.TokenProperties;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * <h2>Token工具类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/16 15:07
 */
@Slf4j
@Component
@Getter
@Setter(onMethod_ = @Autowired)
public class TokenUtil
{
    private TokenProperties tokenProperties;

    /**
     * 生成token
     *
     * @param handleClaims 设置主体数据
     * @param expire       过期时间
     * @param algorithm    签名算法
     * @param type         token类型
     * @param secretKey    token密钥
     * @return token
     */
    public String generate(
            Consumer<Map<String, Object>> handleClaims,
            Date expire,
            String algorithm,
            String type,
            String secretKey
    )
    {
        Assert.notBlank(algorithm, () -> new PropertiesException(TokenProperties.PREFIX, "algorithm"));
        Assert.notBlank(type, () -> new PropertiesException(TokenProperties.PREFIX, "type"));
        Assert.notBlank(secretKey, () -> new PropertiesException(TokenProperties.PREFIX, "secretKey"));
        Assert.state(expire.before(new Date()), "Timeout setting is invalid");

        Map<String, Object> claims = new HashMap<>();
        handleClaims.accept(claims);

        return
                Jwts
                        // 获取JwtBuilder，用于构建token
                        .builder()

                        // 配置Header
                        .setHeaderParam("alg", algorithm)
                        .setHeaderParam("typ", type)

                        // 配置payload（存入数据）
                        .setClaims(claims)

                        // 配置Signature
                        .setExpiration(expire)              // 配置token过期时间
                        .signWith(
                                SignatureAlgorithm.forName(
                                        algorithm
                                ),
                                secretKey
                        )                                   // 配置算法和密钥

                        // 获取JWT
                        .compact();
    }

    /**
     * 生成token，除设置主体函数与过期时间外使用配置数据
     *
     * @param handleClaims 设置主体数据
     * @param expire       过期时间
     * @return token
     */
    public String generate(Consumer<Map<String, Object>> handleClaims, Date expire)
    {
        return generate(
                handleClaims,
                expire,
                tokenProperties.getAlgorithm(),
                tokenProperties.getType(),
                tokenProperties.getSecretKey()
        );
    }

    /**
     * 生成token，除设置主体函数外全部使用配置数据
     *
     * @param handleClaims 设置主体数据
     * @return token
     */
    public String generate(Consumer<Map<String, Object>> handleClaims)
    {
        return generate(
                handleClaims,
                new Date(
                        System.currentTimeMillis() +
                        MILLISECONDS.convert(
                                tokenProperties.getUsableMinutes(),
                                TimeUnit.MINUTES
                        )
                ),
                tokenProperties.getAlgorithm(),
                tokenProperties.getType(),
                tokenProperties.getSecretKey()
        );
    }

    /**
     * 解析token
     *
     * @param token tokenUtil
     * @return {@link Claims}
     */
    public Claims parse(String token)
    {
        try
        {
            return
                    Jwts
                            .parser()
                            .setSigningKey(tokenProperties.getSecretKey())
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
            log.debug("Token malformed");
            throw new TokenParseException(ServiceCode.ERR_token_MALFORMED);
        }
        catch (Throwable e)
        {
            // 未知错误
            log.debug("Unknown error in [TokenUtil#parse()], -- {}: {}", e.getClass().getSimpleName(), e.getMessage());
            throw new TokenParseException(ServiceCode.ERR_UNKNOWN);
        }
    }

    /**
     * token解析错误
     */
    public static class TokenParseException
            extends ServiceException
    {
        TokenParseException(ServiceCode code)
        {
            super(code);
        }
    }
}