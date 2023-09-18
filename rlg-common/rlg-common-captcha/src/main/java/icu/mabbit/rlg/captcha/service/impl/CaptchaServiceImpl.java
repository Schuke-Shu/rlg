package icu.mabbit.rlg.captcha.service.impl;

import icu.mabbit.rlg.captcha.cache.CaptchaApiCache;
import icu.mabbit.rlg.captcha.service.ICaptchaService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CaptchaApiCache captchaApiCache;


}