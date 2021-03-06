package com.yl.common.mapper;

import com.yl.common.bean.UserBasicBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Alex
 * @since 2018/10/19 17:36
 */
public interface UserBasicBeanMapper {

    @Select("select * from T_USER_BASIC where id = #{id};")
    public UserBasicBean get(long id);

    @Select("select * from T_USER_BASIC where id = #{user.id}")
    public UserBasicBean select(@Param("user") UserBasicBean user);

    @Delete("delete from T_USER_BASIC where id = #{user.id}")
    public int del(@Param("user") UserBasicBean user);

    @Update("update T_USER_BASIC set name = #{name} where id = #{id}")
    public int updateName(@Param("id") long id,@Param("name") String name);
}
