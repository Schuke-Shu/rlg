package icu.mabbit.rlg.security.config;

import icu.mabbit.rlg.security.filter.TokenFilter;
import icu.mabbit.rlg.security.properties.SecurityProperties;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <h2>Spring-Security 配置类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/7 12:08
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Setter(onMethod_ = @Autowired)
public class SecurityConfig
{
    private SecurityProperties securityProperties;
    private TokenFilter tokenFilter;
    private AuthenticationManager authenticationManager;

    // 加密编码器
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception
    {
        return
                http
                        // 启用Security框架自带的CorsFilter过滤器，对OPTIONS请求放行
                        .cors()
                        .and()
                        // 配置请求是否需要认证
                        .authorizeRequests()
                        // 匹配白名单中的请求路径
                        .mvcMatchers(securityProperties.getUriWhiteList())
                        .permitAll()        // 允许直接访问
                        .anyRequest()       // 其它任何请求
                        .authenticated()    // 需要通过认证
                        .and()
                        // 禁用“防止伪造的跨域攻击”防御机制
                        .csrf()
                        .disable()
                        .formLogin()
                        .disable()
                        // 设置Session创建策略：从不创建
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                        .and()
                        // 将token过滤器置于Spring Security的“用户名密码认证信息过滤器”之前
                        .addFilterAt(
                                tokenFilter,
                                UsernamePasswordAuthenticationFilter.class
                        )
                        .build();
    }
}