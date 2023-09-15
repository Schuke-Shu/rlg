package icu.mabbit.rlg.common.util;

import icu.mabbit.mdk4j.core.util.StringUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Consumer;

/**
 * <h2>http工具类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/15 13:02
 */
@Slf4j
@Component
@Setter(onMethod_ = @Autowired)
public class HttpUtil
{
    private HttpServletRequest request;

    private HttpServletResponse response;

    /**
     * 向浏览器端返回数据，默认设置{@code contentType}为{@code "application/json;charset=utf-8"}
     *
     * @param data 要返回的数据
     */
    public void response(String data)
    {
        response.setContentType("application/json;charset=utf-8");

        try (PrintWriter writer = response.getWriter())
        {
            writer.write(data);
            writer.flush();
        }
        catch (IOException e)
        {
            log.error("Failed to send response data, msg: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 向浏览器端返回数据，返回之前调用{@link Consumer#accept(Object)}，传入当前的{@link HttpServletResponse}对象，可以对该返回对象进行设置
     *
     * @param data 要返回的数据
     * @param responseSetting 回调函数，传入当前的{@link HttpServletResponse}对象
     */
    public void response(String data, Consumer<HttpServletResponse> responseSetting)
    {
        responseSetting.accept(response);

        try (PrintWriter writer = response.getWriter())
        {
            writer.write(data);
            writer.flush();
        }
        catch (IOException e)
        {
            log.error("Failed to send response data, msg: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 可能包含ip的请求头
     */
    private static final String[] HEADERS = {
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR",
            "X-Real-IP"
    };

    /**
     * 获取ip
     */
    public String ip()
    {
        String ip = request.getHeader("x-forwarded-for");

        if (!isEmpty(ip))
            if (ip.contains(","))
                ip = ip.split(",")[0];

        for (String header : HEADERS)
            if (isEmpty(ip))
                ip = request.getHeader(header);

        if (isEmpty(ip)) ip = request.getRemoteAddr();

        return ip;
    }

    private static boolean isEmpty(String ip)
    {
        return StringUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip);
    }
}