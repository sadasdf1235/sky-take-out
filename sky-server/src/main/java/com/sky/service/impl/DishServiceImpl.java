package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    /**
     * 新增菜品
     * @param dto
     */
    @Override
    public void save(DishDTO dto) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dto,dish);
        dish.setCreateUser(BaseContext.getCurrentId());
        dish.setUpdateUser(BaseContext.getCurrentId());
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dish.setStatus(StatusConstant.DISABLE);
        // 插入菜品
        dishMapper.insert(dish);
        // 获取菜品id
        Long id = dish.getId();
        // 获取口味，存入口味表
        List<DishFlavor> flavors = dto.getFlavors();
        if(flavors != null && !flavors.isEmpty()){ // 口味存在
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(id);
            });
            // 批量插入口味
            dishFlavorMapper.insertBatch(flavors);
        }
    }
}
