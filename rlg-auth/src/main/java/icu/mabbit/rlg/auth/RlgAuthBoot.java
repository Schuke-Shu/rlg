package icu.mabbit.rlg.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h2>红叶园多功能生活服务平台身份认证中心应用启动类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module auth
 * @Date 2023/9/2 14:44
 */
@SpringBootApplication
public class RlgAuthBoot
{
    public static void main(String[] args)
    {
        SpringApplication.run(RlgAuthBoot.class, args);
        System.out.println("\n(♥◠‿◠)ﾉﾞ  身份认证中心启动成功  ლ(´ڡ`ლ)ﾞ\n");
    }
}