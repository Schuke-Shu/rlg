package icu.mabbit.rlg.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * <h2>redis工具类</h2>
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-redis
 * @Date 2023/9/5 12:35
 */
@Component
public class RedisUtil
{
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return 是否设置成功
     */
    public boolean expire(final String key, final long timeout)
    {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 是否设置成功
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit)
    {
        return
                Boolean.TRUE
                        .equals(
                                redisTemplate
                                        .expire(key, timeout, unit)
                        );
    }

    /**
     * 根据key获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    @SuppressWarnings("DataFlowIssue")
    public long queryExpireTime(String key)
    {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return key是否存在
     */
    public boolean exist(String key)
    {
        return
                Boolean.TRUE
                        .equals(
                                redisTemplate.hasKey(key)
                        );
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void remove(String... key)
    {
        if (!ObjectUtils.isEmpty(key))
            redisTemplate.delete(Arrays.asList(key));
    }
}