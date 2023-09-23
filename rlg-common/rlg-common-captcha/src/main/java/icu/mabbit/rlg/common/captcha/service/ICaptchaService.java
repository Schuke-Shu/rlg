package icu.mabbit.rlg.common.captcha.service;

import icu.mabbit.rlg.common.captcha.entity.Captcha;
import icu.mabbit.rlg.common.captcha.exception.CaptchaException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <h2>验证码服务接口</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/16 18:01
 */
@Service
public interface ICaptchaService
{
    void sendEmail(Supplier<Captcha<?>> captchaProvider, Function<String, SimpleMailMessage> msgGetter)
            throws CaptchaException;
}