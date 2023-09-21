package icu.mabbit.rlg.common.core.annotation;

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
 * <h2>Api注解</h2>
 * <p>
 *     组合了{@link RestController}和{@link RequestMapping}的注解
 * </p>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/18 13:17
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@RestController
@RequestMapping
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
}