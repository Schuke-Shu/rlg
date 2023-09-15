package icu.mabbit.rlg.common.orm;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <h2>实体模板</h2>
 *
 * @param <K> 主键类型
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/4 13:18
 */
public abstract class BaseEntity<K extends Serializable>
        implements Serializable
{
    protected K id;
    /**
     * 创建时间
     */
    protected LocalDateTime createTime;
    /**
     * 最后一次修改时间
     */
    protected LocalDateTime modifiedTime;
}