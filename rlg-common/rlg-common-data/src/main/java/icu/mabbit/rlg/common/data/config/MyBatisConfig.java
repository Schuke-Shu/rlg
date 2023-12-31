package icu.mabbit.rlg.common.data.config;

import icu.mabbit.rlg.common.data.interceptor.InsertUpdateTimeInterceptor;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <h2>MyBatis配置</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-data
 * @Date 2023/9/4 13:56
 */
@Configuration
@MapperScan("icu.mabbit.rlg.**.mapper")
public class MyBatisConfig
{
    @Setter(onMethod_ = @Autowired)
    private List<SqlSessionFactory> sqlSessionFactoryList;

    private static final InsertUpdateTimeInterceptor INTERCEPTOR = new InsertUpdateTimeInterceptor();

    // 配置Mybatis拦截器
    @PostConstruct
    public void addInterceptor()
    {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList)
            sqlSessionFactory
                    .getConfiguration()
                    .addInterceptor(INTERCEPTOR);
    }
}