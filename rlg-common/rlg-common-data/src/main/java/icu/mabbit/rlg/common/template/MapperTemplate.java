package icu.mabbit.rlg.common.template;

import java.io.Serializable;
import java.util.List;

/**
 * <h2>实体映射模板</h2>
 *
 * @param <K> 主键类型
 * @param <E> 实体类型
 * @author 一只枫兔
 * @Project rlg
 * @Module common-core
 * @Date 2023/9/4 13:17
 */
public interface MapperTemplate<K extends Serializable, E extends EntityTemplate<K>>
{
    /**
     * 添加数据，可批量
     *
     * @param e 实体数据
     * @return 实际添加数据量
     */
    int save(E... e);

    /**
     * 根据主键删除数据，可批量
     *
     * @param k 主键
     * @return 实际删除数据量
     */
    int remove(K... k);

    /**
     * 根据属性删除
     *
     * @param e 实体
     * @return 实际删除数据量
     */
    int removeDynamic(E e);

    /**
     * 修改一条数据
     *
     * @param e 提交需要修改的数据
     * @return 实际修改数据量
     */
    int update(E e);

    /**
     * 根据主键查询数据，可批量
     *
     * @param k 主键
     * @return 实体数据列表
     */
    List<E> query(K... k);

    /**
     * 根据属性查询
     *
     * @param e 实体
     * @return 实体数据列表
     */
    List<E> queryDynamic(E e);

    /**
     * 列出所有数据
     *
     * @return 表中所有数据
     */
    List<E> queryAll();

    /**
     * 查询表中数据总量
     *
     * @return 数据总量
     */
    int count();

    /**
     * 根据属性查询数据数量
     *
     * @return 数据数量
     */
    int countDynamic(E e);
}