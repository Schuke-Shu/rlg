package icu.mabbit.rlg.common.orm;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    /**
     * id
     */
    protected K id;
    protected Integer sort;
    /**
     * 是否启用
     */
    protected Status isEnabled;
    /**
     * 备注
     */
    protected String remark;
    /**
     * 创建人
     */
    protected String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;
    /**
     * 更新人
     */
    protected String modifiedBy;
    /**
     * 最后一次修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime modifiedTime;

    public K id()
    {
        return id;
    }

    public BaseEntity<K> id(K id)
    {
        this.id = id;
        return this;
    }

    public Integer sort()
    {
        return sort;
    }

    public BaseEntity<K> sort(Integer sort)
    {
        this.sort = sort;
        return this;
    }

    public Status isEnabled()
    {
        return isEnabled;
    }

    public BaseEntity<K> isEnabled(Status isEnabled)
    {
        this.isEnabled = isEnabled;
        return this;
    }

    public String remark()
    {
        return remark;
    }

    public BaseEntity<K> remark(String remark)
    {
        this.remark = remark;
        return this;
    }

    public String createBy()
    {
        return createBy;
    }

    public BaseEntity<K> createBy(String createBy)
    {
        this.createBy = createBy;
        return this;
    }

    public LocalDateTime createTime()
    {
        return createTime;
    }

    public BaseEntity<K> createTime(LocalDateTime createTime)
    {
        this.createTime = createTime;
        return this;
    }

    public String modifiedBy()
    {
        return modifiedBy;
    }

    public BaseEntity<K> modifiedBy(String modifiedBy)
    {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public LocalDateTime modifiedTime()
    {
        return modifiedTime;
    }

    public BaseEntity<K> modifiedTime(LocalDateTime modifiedTime)
    {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public enum Status
    {
        DISABLED,
        ENABLED
    }
}