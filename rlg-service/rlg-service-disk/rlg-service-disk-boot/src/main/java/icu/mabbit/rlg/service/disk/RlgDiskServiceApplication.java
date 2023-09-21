package icu.mabbit.rlg.service.disk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h2>红叶园多功能生活服务平台网盘服务子模块应用启动类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module service-disk
 * @Date 2023/9/2 14:44
 */
@SpringBootApplication
public class RlgDiskServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RlgDiskServiceApplication.class, args);
        System.out.println("\n(♥◠‿◠)ﾉﾞ  网盘服务启动成功  ლ(´ڡ`ლ)ﾞ\n");
    }
}