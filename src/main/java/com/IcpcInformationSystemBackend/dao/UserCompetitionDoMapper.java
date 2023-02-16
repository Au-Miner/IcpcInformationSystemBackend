package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.UserCompetitionDo;
import com.IcpcInformationSystemBackend.model.entity.UserCompetitionDoExample;
import com.IcpcInformationSystemBackend.model.entity.UserCompetitionDoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCompetitionDoMapper {
    int countByExample(UserCompetitionDoExample example);

    int deleteByExample(UserCompetitionDoExample example);

    int deleteByPrimaryKey(UserCompetitionDoKey key);

    int insert(UserCompetitionDo record);

    int insertSelective(UserCompetitionDo record);

    List<UserCompetitionDo> selectByExample(UserCompetitionDoExample example);

    UserCompetitionDo selectByPrimaryKey(UserCompetitionDoKey key);

    int updateByExampleSelective(@Param("record") UserCompetitionDo record, @Param("example") UserCompetitionDoExample example);

    int updateByExample(@Param("record") UserCompetitionDo record, @Param("example") UserCompetitionDoExample example);

    int updateByPrimaryKeySelective(UserCompetitionDo record);

    int updateByPrimaryKey(UserCompetitionDo record);
}