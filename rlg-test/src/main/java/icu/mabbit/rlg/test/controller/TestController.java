package icu.mabbit.rlg.test.controller;

import icu.mabbit.rlg.captcha.annotation.CaptchaApi;
import icu.mabbit.rlg.captcha.cache.CaptchaApiCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h2>测试控制器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module test
 * @Date 2023/9/8 10:06
 */
@Slf4j
@RestController
public class TestController
{
    @Autowired
    private CaptchaApiCache captchaApiCache;

    @CaptchaApi
    @GetMapping("/hello")
    public void test()
    {
        System.out.println(captchaApiCache.has("/hello"));
        System.out.println(captchaApiCache.get("/hello").captcha().code());
    }
}