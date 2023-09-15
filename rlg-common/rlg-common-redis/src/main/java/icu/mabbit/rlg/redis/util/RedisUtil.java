package icu.mabbit.rlg.redis.util;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
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
@Setter(onMethod_ = @Autowired)
@SuppressWarnings({"rawtypes", "unchecked", "DataFlowIssue"})
public class RedisUtil
{
    public RedisTemplate redisTemplate;

    /**
     * 存储Object
     *
     * @param key   键
     * @param value 值
     */
    public <T> void setObj(String key, T value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储Object并设置超时时间
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setObj(String key, T value, Long timeout, TimeUnit timeUnit)
    {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return 是否设置成功
     */
    public boolean expire(String key, long timeout)
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
    public boolean expire(String key, long timeout, TimeUnit unit)
    {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 获取有效时间
     *
     * @param key Redis键
     * @return 有效时间
     */
    public long expire(String key)
    {
        return redisTemplate.getExpire(key);
    }

    /**
     * @param key 键
     * @return 是否存在指定key
     */
    public boolean exist(String key)
    {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * @param key 键
     * @return 值
     */
    public <T> T getObj(String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key 键
     * @return 是否删除成功
     */
    public boolean delObj(String key)
    {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return 是否删除成功
     */
    public boolean delObj(Collection collection)
    {
        return redisTemplate.delete(collection) > 0;
    }

    /**
     * 存储list
     *
     * @param key  键
     * @param list list数据
     * @return 存储成功的数量
     */
    public <T> long setList(String key, List<T> list)
    {
        Long count = redisTemplate.opsForList().rightPushAll(key, list);
        return count == null ? 0 : count;
    }

    /**
     * 获取list
     *
     * @param key 键
     * @return 值
     */
    public <T> List<T> getList(String key)
    {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 存储set
     *
     * @param key 键
     * @param set set数据
     * @return 存储数据的对象
     */
    public <T> BoundSetOperations<String, T> setSet(String key, Set<T> set)
    {
        BoundSetOperations<String, T> ops = redisTemplate.boundSetOps(key);

        for (T t : set)
            ops.add(t);

        return ops;
    }

    /**
     * 获取set
     *
     * @param key 键
     * @return 值
     */
    public <T> Set<T> getSet(String key)
    {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 存储map
     *
     * @param key 键
     * @param map 值
     */
    public <T> void setMap(String key, Map<String, T> map)
    {
        if (map != null)
            redisTemplate
                    .opsForHash()
                    .putAll(key, map);
    }

    /**
     * 获取map
     *
     * @param key 键
     * @return 值
     */
    public <T> Map<String, T> getMap(String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setMapValue(String key, String hKey, T value)
    {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getMapValue(String key, String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiMapValue(String key, Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除Hash中的某条数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return 是否删除成功
     */
    public boolean delMapValue(String key, String hKey)
    {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }

    /**
     * 获得存储的对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(String pattern)
    {
        return redisTemplate.keys(pattern);
    }
}