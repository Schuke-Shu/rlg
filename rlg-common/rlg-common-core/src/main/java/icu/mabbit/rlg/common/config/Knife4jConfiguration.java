package icu.mabbit.rlg.common.config;

import icu.mabbit.rlg.common.enums.ServiceCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h2>Knife4j在线文档配置</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/4 0:24
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration
{
    /**
     * 【重要】指定Controller包路径
     */
    private static final String BASE_PACKAGE = "icu.mabbit.rlg";
    /**
     * 分组名称
     */
    private static final String GROUP_NAME = "rlg-backend";
    /**
     * 主机名
     */
    private static final String HOST = "http://rlg.mabbit.cn";
    /**
     * 标题
     */
    private static final String TITLE = "红叶园API";
    /**
     * 简介
     */
    private static final String DESCRIPTION = "红叶园多功能生活服务平台后台在线API文档";
    /**
     * 服务条款URL
     */
    private static final String TERMS_OF_SERVICE_URL = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 联系人
     */
    private static final String CONTACT_NAME = "一只枫兔";
    /**
     * 联系网址
     */
    private static final String CONTACT_URL = "http://mabbit.icu";
    /**
     * 联系邮箱
     */
    private static final String CONTACT_EMAIL = "schuke-shu@outlook.com";
    /**
     * 版本号
     */
    private static final String VERSION = "1.0";

    @Bean
    public Docket docket()
    {
        ArrayList<ResponseMessage> responseMessages = Arrays.stream(ServiceCode.values())
                .collect(
                        ArrayList::new,
                        (l, v) -> l.add(
                                new ResponseMessageBuilder()
                                        .code(v.code())
                                        .message(v.msg())
                                        .responseModel(
                                                new ModelRef(v.msg())
                                        )
                                        .build()
                        ),
                        ArrayList::addAll
                );

        return new Docket(DocumentationType.SWAGGER_2)
                // 添加全局响应状态码
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .host(HOST)
                .apiInfo(
                        new ApiInfoBuilder()
                                .title(TITLE)
                                .description(DESCRIPTION)
                                .termsOfServiceUrl(TERMS_OF_SERVICE_URL)
                                .contact(
                                        new Contact(
                                                CONTACT_NAME,
                                                CONTACT_URL,
                                                CONTACT_EMAIL
                                        )
                                )
                                .version(VERSION)
                                .build()
                )
                .groupName(GROUP_NAME)
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage(BASE_PACKAGE)
                )
                .paths(
                        PathSelectors
                                .any()
                )
                .build();
    }
}