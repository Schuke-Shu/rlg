package icu.mabbit.rlg.common.captcha.exception;

import icu.mabbit.rlg.common.core.enums.ServiceCode;
import icu.mabbit.rlg.common.core.exception.ServiceException;

/**
 * @author 一只枫兔
 * @Project rlg
 * @Module
 * @Date 2023/9/22 17:57
 */
public class CaptchaException extends ServiceException
{
    public CaptchaException()
    {
    }

    public CaptchaException(ServiceCode code)
    {
        super(code);
    }
}