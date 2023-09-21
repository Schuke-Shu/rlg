package icu.mabbit.rlg.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h2>红叶园多功能生活服务平台用户服务子模块应用启动类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module service-user
 * @Date 2023/9/2 14:44
 */
@SpringBootApplication
public class RlgUserServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RlgUserServiceApplication.class, args);
        System.out.println("\n(♥◠‿◠)ﾉﾞ  用户服务启动成功  ლ(´ڡ`ლ)ﾞ\n");
    }
}