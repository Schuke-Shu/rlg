package icu.mabbit.rlg.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h2>红叶园多功能生活服务平台网关应用启动类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module gateway
 * @Date 2023/9/2 14:44
 */
@SpringBootApplication
public class RlgGatewayBoot
{
    public static void main(String[] args)
    {
        SpringApplication.run(RlgGatewayBoot.class, args);
        System.out.println("\n(♥◠‿◠)ﾉﾞ  网关启动成功  ლ(´ڡ`ლ)ﾞ\n");
    }
}