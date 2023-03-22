package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.TeamScoreDo;
import com.IcpcInformationSystemBackend.model.entity.TeamScoreDoExample;
import com.IcpcInformationSystemBackend.model.entity.TeamScoreDoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeamScoreDoMapper {
    int countByExample(TeamScoreDoExample example);

    int deleteByExample(TeamScoreDoExample example);

    int deleteByPrimaryKey(TeamScoreDoKey key);

    int insert(TeamScoreDo record);

    int insertSelective(TeamScoreDo record);

    List<TeamScoreDo> selectByExample(TeamScoreDoExample example);

    TeamScoreDo selectByPrimaryKey(TeamScoreDoKey key);

    int updateByExampleSelective(@Param("record") TeamScoreDo record, @Param("example") TeamScoreDoExample example);

    int updateByExample(@Param("record") TeamScoreDo record, @Param("example") TeamScoreDoExample example);

    int updateByPrimaryKeySelective(TeamScoreDo record);

    int updateByPrimaryKey(TeamScoreDo record);
}