package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.TeamRankDo;
import com.IcpcInformationSystemBackend.model.entity.TeamRankDoExample;
import com.IcpcInformationSystemBackend.model.entity.TeamRankDoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeamRankDoMapper {
    int countByExample(TeamRankDoExample example);

    int deleteByExample(TeamRankDoExample example);

    int deleteByPrimaryKey(TeamRankDoKey key);

    int insert(TeamRankDo record);

    int insertSelective(TeamRankDo record);

    List<TeamRankDo> selectByExample(TeamRankDoExample example);

    TeamRankDo selectByPrimaryKey(TeamRankDoKey key);

    int updateByExampleSelective(@Param("record") TeamRankDo record, @Param("example") TeamRankDoExample example);

    int updateByExample(@Param("record") TeamRankDo record, @Param("example") TeamRankDoExample example);

    int updateByPrimaryKeySelective(TeamRankDo record);

    int updateByPrimaryKey(TeamRankDo record);
}