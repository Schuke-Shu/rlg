package icu.mabbit.rlg.common.swagger.annotation;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h2>控制器注解</h2>
 * <p>
 *     组合了{@link RestController}、{@link RequestMapping}、{@link Tag}
 * </p>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-swagger
 * @Date 2023/9/18 13:17
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@RestController
@RequestMapping
@Tag(name = "")
public @interface Api
{
    @AliasFor(annotation = RequestMapping.class)
    String value() default "";

    @AliasFor(
            value = "value",
            annotation = RestController.class
    )
    String beanName() default "";

    @AliasFor(
            value = "name",
            annotation = RequestMapping.class
    )
    String mappingName() default "";

    @AliasFor(annotation = RequestMapping.class)
    RequestMethod[] method() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] params() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] headers() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] consumes() default {};

    @AliasFor(annotation = RequestMapping.class)
    String[] produces() default {};

    @AliasFor(annotation = Tag.class)
    String name();

    @AliasFor(annotation = Tag.class)
    String description() default "";

    @AliasFor(annotation = Tag.class)
    ExternalDocumentation externalDocs() default @ExternalDocumentation();

    @AliasFor(annotation = Tag.class)
    Extension[] extensions() default {};
}