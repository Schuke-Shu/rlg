package icu.mabbit.rlg.captcha.controller;

import icu.mabbit.rlg.common.annotation.Api;
import icu.mabbit.rlg.common.restful.SuccessResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <h2>验证码控制器</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-captcha
 * @Date 2023/9/16 17:58
 */
@Api(CaptchaController.PREFIX)
@Tag(name = "获取验证码")
public class CaptchaController
{
    public static final String PREFIX = "/code";

    @GetMapping("/**")
    @Operation(summary = "获取验证码接口")
    public SuccessResult<?> captcha()
    {
        // TODO 验证码
        return null;
    }
}