package icu.mabbit.rlg.common.security.exception;

import icu.mabbit.rlg.common.core.exception.ProjectException;

/**
 * <h2>认证异常</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-security
 * @Date 2023/9/19 16:00
 */
public class AuthException
        extends ProjectException
{
    public AuthException()
    {
    }

    public AuthException(String message)
    {
        super(message);
    }

    public AuthException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AuthException(Throwable cause)
    {
        super(cause);
    }

    public AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}