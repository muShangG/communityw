package com.mus.community.mapper;

import com.mus.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into PUBLIC.QUESTION(TITLE, DESC, GMT_CREATE, GMT_MODIFIED, TAG, CREATOR)\n" +
            "        values (#{title}, #{desc}, #{gmtCreate}, #{gmtModified}, #{tag}, #{creator})")
    public void create(Question question);
}
