package icu.mabbit.rlg.security.util;

import icu.mabbit.rlg.security.entity.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <h2>Security工具类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/19 18:17
 */
public abstract class SecurityUtil
{
    /**
     * @return {@link SecurityContext}
     */
    public static SecurityContext getContext()
    {
        return SecurityContextHolder.getContext();
    }

    /**
     * @return {@link Authentication}
     */
    public static Authentication getAuthentication()
    {
        return getContext().getAuthentication();
    }

    /**
     * @param authentication {@link Authentication}
     */
    public static void setAuthentication(Authentication authentication)
    {
        getContext().setAuthentication(authentication);
    }

    /**
     * @return {@link Principal}
     */
    public static Principal getPrincipal()
    {
        return (Principal) getAuthentication().getPrincipal();
    }
}