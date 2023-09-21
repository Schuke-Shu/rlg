package icu.mabbit.rlg.common.redis.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.data.redis.serializer.SerializationException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <h2>redis序列化类</h2>
 * 使用FastJson序列化
 *
 * @author 一只枫兔
 * @Project rlg
 * @Module common-redis
 * @Date 2023/9/8 12:31
 */
public class RedisSerializer<T>
        implements org.springframework.data.redis.serializer.RedisSerializer<T>
{
    private final Class<T> cls;

    public RedisSerializer(Class<T> cls)
    {
        super();
        this.cls = cls;
    }

    @Override
    public byte[] serialize(T t)
            throws SerializationException
    {
        if (t == null) return new byte[0];

        return
                JSON
                        .toJSONString(
                                t,
                                JSONWriter.Feature.WriteClassName
                        )
                        .getBytes(UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes)
            throws SerializationException
    {
        if (bytes == null || bytes.length == 0)
            return null;

        return JSON.parseObject(
                new String(bytes, UTF_8),
                cls,
                JSONReader.Feature.SupportAutoType
        );
    }
}
