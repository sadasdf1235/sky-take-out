package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 根据openid查找用户
     * @param openid
     * @return
     */
    @Select("select  * from user where openid = #{openid}")
    User getUserByOpenId(String openid);

    /**
     * 新增用户
     * @param user
     */
    void insert(User user);
}
