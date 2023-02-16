package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.UserDo;
import com.IcpcInformationSystemBackend.model.entity.UserDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDoMapper {
    int countByExample(UserDoExample example);

    int deleteByExample(UserDoExample example);

    int deleteByPrimaryKey(String userEmail);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    List<UserDo> selectByExample(UserDoExample example);

    UserDo selectByPrimaryKey(String userEmail);

    int updateByExampleSelective(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByExample(@Param("record") UserDo record, @Param("example") UserDoExample example);

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);
}