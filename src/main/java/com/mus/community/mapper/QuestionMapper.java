package com.mus.community.mapper;

import com.mus.community.model.Question;
import com.mus.community.model.Question_User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapper {
    @Insert("insert into PUBLIC.QUESTION(TITLE, DESC, GMT_CREATE, GMT_MODIFIED, TAG, CREATOR)\n" +
            "        values (#{title}, #{desc}, #{gmtCreate}, #{gmtModified}, #{tag}, #{creator})")
    public void create(Question question);



    @Select("select * from PUBLIC.QUESTION a join PUBLIC.USER b on a.CREATOR=b.ID")
    public List<Map<String,Object>> ListMap();
}
