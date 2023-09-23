package icu.mabbit.rlg.common.swagger.annotation;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * <h2>接口注解</h2>
 * <p>
 *     组合了{@link RequestMapping}、{@link Operation}，且请求方法限制为POST
 * </p>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-swagger
 * @Date 2023/9/21 17:34
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
@RequestMapping(method = POST)
@Operation
public @interface PostUri
{
    @AliasFor(annotation = RequestMapping.class)
    String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] path() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] params() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] headers() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] consumes() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] produces() default {};

    @AliasFor(annotation = Operation.class)
    String[] tags() default {};

    @AliasFor(annotation = Operation.class)
    String summary() default "";

    @AliasFor(annotation = Operation.class)
    String description() default "";

    @AliasFor(annotation = Operation.class)
    RequestBody requestBody() default @RequestBody();

    @AliasFor(annotation = Operation.class)
    ExternalDocumentation externalDocs() default @ExternalDocumentation();

    @AliasFor(annotation = Operation.class)
    String operationId() default "";

    @AliasFor(annotation = Operation.class)
    Parameter[] parameters() default {};

    @AliasFor(annotation = Operation.class)
    ApiResponse[] responses() default {};

    @AliasFor(annotation = Operation.class)
    boolean deprecated() default false;

    @AliasFor(annotation = Operation.class)
    SecurityRequirement[] security() default {};

    @AliasFor(annotation = Operation.class)
    Server[] servers() default {};

    @AliasFor(annotation = Operation.class)
    Extension[] extensions() default {};

    @AliasFor(annotation = Operation.class)
    boolean hidden() default false;

    @AliasFor(annotation = Operation.class)
    boolean ignoreJsonView() default false;
}