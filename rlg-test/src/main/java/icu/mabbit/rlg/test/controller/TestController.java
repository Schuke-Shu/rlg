package icu.mabbit.rlg.test.controller;

import icu.mabbit.rlg.captcha.annotation.CaptchaApi;
import icu.mabbit.rlg.captcha.cache.CaptchaApiCache;
import icu.mabbit.rlg.captcha.entity.Captcha;
import icu.mabbit.rlg.common.annotation.Api;
import icu.mabbit.rlg.common.properties.ProjectInfo;
import icu.mabbit.rlg.common.restful.R;
import icu.mabbit.rlg.common.restful.SuccessResult;
import icu.mabbit.rlg.common.util.ServletUtil;
import icu.mabbit.rlg.security.properties.SecurityProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h2>测试控制器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module test
 * @Date 2023/9/8 10:06
 */
@Api
@Tag(name = "测试")
@Setter(onMethod_ = @Autowired)
public class TestController
{
    private CaptchaApiCache captchaApiCache;
    private SecurityProperties securityProperties;
    private ProjectInfo projectInfo;

    @CaptchaApi
    @GetMapping("/hello")
    @Operation(summary = "测试接口")
    public SuccessResult<?> test()
    {
        Captcha<?> captcha = captchaApiCache.get("/hello").captcha();
        String uri = ServletUtil.getRequestURI();
        System.out.println(uri);
        captcha.setUuid(uri, "123456");
        return R.ok(captcha);
    }

    @CaptchaApi
    @GetMapping("/nacos")
    @Operation(summary = "nacos测试接口")
    public SuccessResult<?> nacos()
    {
        Map<String, String> map = new HashMap<>();
        map.put("security-white-list", Arrays.toString(securityProperties.getUriWhiteList()));
        map.put("project-version", projectInfo.getVersion());
        return R.ok(map);
    }
}