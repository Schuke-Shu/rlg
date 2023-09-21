package icu.mabbit.rlg.common.swagger.config;

import icu.mabbit.rlg.common.swagger.properties.SwaggerProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h2>Swagger在线文档配置</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-swagger
 * @Date 2023/9/4 0:24
 */
@Configuration
@Setter(onMethod_ = @Autowired)
public class SwaggerConfig
{
    private SwaggerProperties swaggerProperties;

    @Bean
    public OpenAPI customOpenAPI()
    {
        return
                new OpenAPI()
                        .info(
                                new Info()
                                        .title(swaggerProperties.getTitle())
                                        .contact(
                                                new Contact()
                                                        .name(swaggerProperties.getContactName())
                                                        .email(swaggerProperties.getContactEmail())
                                                        .url(swaggerProperties.getContactUrl())
                                        )
                                        .version(swaggerProperties.getVersion())
                                        .description(swaggerProperties.getDescription())
                                        .termsOfService(swaggerProperties.getTermOfServiceUrl())
                                        .license(
                                                new License()
                                                        .name(swaggerProperties.getContactName())
                                                        .url(swaggerProperties.getContactUrl())
                                        )
                        );
    }
}