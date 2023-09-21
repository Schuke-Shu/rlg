package icu.mabbit.rlg.common.core.util;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.function.Consumer;

/**
 * <h2>Servlet工具类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/15 13:02
 */
@Slf4j
public abstract class ServletUtil
{
    /**
     * 将数据转换为json并返回到浏览器端，默认设置{@code contentType}为{@code "application/json;charset=utf-8"}
     *
     * @param data 要返回的数据
     */
    public static void response(Object data)
    {
        response(data, res -> res.setContentType("application/json;charset=utf-8"));
    }

    /**
     * 将数据转换为json并返回到浏览器端，返回之前调用{@link Consumer#accept(Object)}，传入当前的{@link HttpServletResponse}对象，可以对该返回对象进行设置
     *
     * @param data 要返回的数据
     * @param responseSetting 回调函数，传入当前的{@link HttpServletResponse}对象
     */
    public static void response(Object data, Consumer<HttpServletResponse> responseSetting)
    {
        HttpServletResponse response = getResponse();

        responseSetting.accept(response);
        try (PrintWriter writer = response.getWriter())
        {
            writer.write(
                    JSON.toJSONString(data)
            );
            writer.flush();
        }
        catch (IOException e)
        {
            log.error("Failed to send response data, msg: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * @return 获取当前请求的request对象
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * @return 获取当前请求的response对象
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * @return {@link HttpServletRequest#getMethod()}
     */
    public static String getMethod()
    {
        return getRequest().getMethod();
    }

    /**
     * @return {@link HttpServletRequest#getRequestURI()}
     */
    public static String getRequestURI()
    {
        return getRequest().getRequestURI();
    }

    /**
     * @param s 请求头key值
     * @return {@link HttpServletRequest#getHeader(String)}
     */
    public static String getHeader(String s)
    {
        return getRequest().getHeader(s);
    }

    /**
     * @param s 请求头key值
     * @return {@link HttpServletRequest#getHeaders(String)}
     */
    public static Enumeration<String> getHeaders(String s)
    {
        return getRequest().getHeaders(s);
    }

    /**
     * @param key 参数键值对的key值
     * @return 参数
     */
    public static String getParameter(String key)
    {
        return getRequest().getParameter(key);
    }
}