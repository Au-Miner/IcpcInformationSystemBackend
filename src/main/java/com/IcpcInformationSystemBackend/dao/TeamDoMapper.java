package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.TeamDo;
import com.IcpcInformationSystemBackend.model.entity.TeamDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeamDoMapper {
    int countByExample(TeamDoExample example);

    int deleteByExample(TeamDoExample example);

    int deleteByPrimaryKey(String teamId);

    int insert(TeamDo record);

    int insertSelective(TeamDo record);

    List<TeamDo> selectByExample(TeamDoExample example);

    TeamDo selectByPrimaryKey(String teamId);

    int updateByExampleSelective(@Param("record") TeamDo record, @Param("example") TeamDoExample example);

    int updateByExample(@Param("record") TeamDo record, @Param("example") TeamDoExample example);

    int updateByPrimaryKeySelective(TeamDo record);

    int updateByPrimaryKey(TeamDo record);
}