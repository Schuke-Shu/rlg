package icu.mabbit.rlg.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * <h2>Spring工具类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/20 11:13
 */
@Component
@SuppressWarnings("unchecked")
public class SpringUtil
        implements BeanFactoryPostProcessor
{
    /**
     * Spring应用上下文环境
     */
    private static ConfigurableListableBeanFactory FACTORY;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException
    {
        SpringUtil.FACTORY = beanFactory;
    }

    /**
     * 通过bean名称获取bean
     *
     * @param name 指定bean名称
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public static Object getBean(String name)
            throws BeansException
    {
        return FACTORY.getBean(name);
    }

    /**
     * 通过bean名称与类型获取bean
     *
     * @param name 指定bean名称
     * @param type 指定bean类型
     * @param <T>  bean类型
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public static <T> T getBean(String name, Class<T> type)
            throws BeansException
    {
        return FACTORY.getBean(name, type);
    }

    /**
     * 通过bean名称与指定有参构造参数获取bean
     *
     * @param name 指定bean名称
     * @param args 指定有参构造参数
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public static Object getBean(String name, Object... args)
            throws BeansException
    {
        return FACTORY.getBean(name, args);
    }

    /**
     * 通过bean类型获取bean
     *
     * @param type 指定bean类型
     * @param <T>  bean类型
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public static <T> T getBean(Class<T> type)
            throws BeansException
    {
        return FACTORY.getBean(type);
    }

    /**
     * 通过bean类型与指定有参构造参数获取bean
     *
     * @param type 指定bean类型
     * @param args 指定有参构造参数
     * @return bean
     * @throws BeansException 无法获取bean
     */
    public static <T> T getBean(Class<T> type, Object... args)
            throws BeansException
    {
        return FACTORY.getBean(type, args);
    }

    public static <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType)
    {
        return FACTORY.getBeanProvider(requiredType);
    }

    public static <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType)
    {
        return FACTORY.getBeanProvider(requiredType);
    }

    /**
     * 根据bean名称判断是否存在bean
     *
     * @param name bean名称
     * @return 指定bean是否存在
     */
    public static boolean containsBean(String name)
    {
        return FACTORY.containsBean(name);
    }

    /**
     * 根据bean名称判断该bean是否为单例
     * @param name bean名称
     * @return 指定bean是否为单例
     * @throws NoSuchBeanDefinitionException 找不到该bean
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException
    {
        return FACTORY.isSingleton(name);
    }

    /**
     * 根据bean名称判断该bean是否不为单例
     * @param name bean名称
     * @return 指定bean是否不为单例
     * @throws NoSuchBeanDefinitionException 找不到该bean
     */
    public static boolean isPrototype(String name) throws NoSuchBeanDefinitionException
    {
        return FACTORY.isPrototype(name);
    }

    public static boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException
    {
        return FACTORY.isTypeMatch(name, typeToMatch);
    }

    public static boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException
    {
        return FACTORY.isTypeMatch(name, typeToMatch);
    }

    @Nullable
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException
    {
        return FACTORY.getType(name);
    }

    @Nullable
    public static Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException
    {
        return FACTORY.getType(name, allowFactoryBeanInit);
    }

    public static String[] getAliases(String name)
    {
        return FACTORY.getAliases(name);
    }
}