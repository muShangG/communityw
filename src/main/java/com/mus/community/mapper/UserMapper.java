package com.mus.community.mapper;

import com.mus.community.model.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {
//   @Insert("insert into PUBLIC.USER(ACCOUNT_ID, NAME, TOKEN, GMT_CREAT, GMT_MODIFIED,AVATAR_URL) values" +
//           "(#{accountId}, #{name}, #{token}, #{gmtCreat}, #{gmtModified},#{avatar_url})")
   void  insert(User user);


//   @Select("select * from user where token=#{token}")
   User findToken(@Param("token") String token);
}
