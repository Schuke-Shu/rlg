package icu.mabbit.rlg.test.controller;

import icu.mabbit.rlg.common.swagger.annotation.Api;
import icu.mabbit.rlg.common.core.properties.ProjectInfo;
import icu.mabbit.rlg.common.core.restful.R;
import icu.mabbit.rlg.common.core.restful.SuccessResult;
import icu.mabbit.rlg.common.security.properties.SecurityProperties;
import icu.mabbit.rlg.common.swagger.annotation.GetUri;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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
@Api(name = "测试")
@Setter(onMethod_ = @Autowired)
public class TestController
{
    private SecurityProperties securityProperties;
    private ProjectInfo projectInfo;

    @GetUri(value = "/hello", summary = "测试")
    public SuccessResult<?> test()
    {
        return R.ok();
    }

    @GetUri(value = "/nacos", summary = "nacos测试")
    public SuccessResult<?> nacos()
    {
        Map<String, String> map = new HashMap<>();
        map.put("security-white-list", Arrays.toString(securityProperties.getUriWhiteList()));
        map.put("project-version", projectInfo.getVersion());
        return R.ok(map);
    }
}