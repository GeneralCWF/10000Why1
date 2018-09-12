package com.why10000.mapper.user;

import com.why10000.domain.UserRec;

public interface UserRecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRec record);

    int insertSelective(UserRec record);

    UserRec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRec record);

    int updateByPrimaryKey(UserRec record);


}