package com.snow.automatic.common.mapper;



import com.snow.automatic.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from s_user")
    List<User> findAll();



}
