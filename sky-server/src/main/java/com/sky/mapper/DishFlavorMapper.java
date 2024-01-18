package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味
     * @param flavors
     */

    void insertBatch(List<DishFlavor> flavors);

    void delete(List<Long> ids);
}
