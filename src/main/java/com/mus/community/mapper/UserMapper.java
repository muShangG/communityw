package com.mus.community.mapper;

import com.mus.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
   @Insert("insert into PUBLIC.USER(ACCOUNT_ID, NAME, TOKEN, GMT_CREAT, GMT_MODIFIED) values" +
           "(#{accountId}, #{name}, #{token}, #{gmtCreat}, #{gmtModified})")
   void  insert(User user);


   @Select("select * from user where token=#{token}")
   User findToken(@Param("token") String token);
}
