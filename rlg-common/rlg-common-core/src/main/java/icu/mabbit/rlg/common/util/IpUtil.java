package icu.mabbit.rlg.common.util;

import icu.mabbit.mdk4j.core.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <h2>Ip工具类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/18 11:03
 */
public abstract class IpUtil
{
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
    public static String getIp()
    {
        HttpServletRequest request = ServletUtil.getRequest();

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