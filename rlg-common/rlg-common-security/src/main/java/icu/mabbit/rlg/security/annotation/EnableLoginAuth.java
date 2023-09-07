package icu.mabbit.rlg.security.annotation;

import icu.mabbit.rlg.security.config.EnableLoginAuthConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <h2>开启登录认证</h2>
 * spring初始化时添加登录流程相关类
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/7 12:11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableLoginAuthConfig.class)
public @interface EnableLoginAuth
{
}