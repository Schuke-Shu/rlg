package icu.mabbit.rlg.common.orm;

import com.fasterxml.jackson.annotation.JsonFormat;
import icu.mabbit.rlg.common.enums.Boolean;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseEntity<K extends Serializable>
        implements Serializable
{
    /**
     * id
     */
    protected K id;
    /**
     * 是否已被删除
     */
    protected Boolean ifDeleted;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;
    /**
     * 最后一次修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime modifiedTime;
}