package icu.mabbit.rlg.common.captcha.service.impl;

import icu.mabbit.rlg.common.captcha.entity.Captcha;
import icu.mabbit.rlg.common.captcha.exception.CaptchaException;
import icu.mabbit.rlg.common.captcha.properties.CaptchaProperties;
import icu.mabbit.rlg.common.captcha.service.ICaptchaService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 一只枫兔
 * @Project rlg
 * @Module
 * @Date 2023/9/16 23:37
 */
@Slf4j
@Service
@Setter(onMethod_ = @Autowired)
public class CaptchaServiceImpl implements ICaptchaService
{
    private JavaMailSender mailSender;
    private CaptchaProperties captchaProperties;

    @Override
    public void sendEmail(Supplier<Captcha<?>> captchaProvider, Function<String, SimpleMailMessage> msgGetter)
            throws CaptchaException
    {
        mailSender.send(
                msgGetter.apply(
                        captchaProvider.get().getCode()
                )
        );
    }
}