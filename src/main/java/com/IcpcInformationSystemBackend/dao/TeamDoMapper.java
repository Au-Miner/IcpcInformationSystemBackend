package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.TeamDo;
import com.IcpcInformationSystemBackend.model.entity.TeamDoExample;
import com.IcpcInformationSystemBackend.model.entity.TeamDoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeamDoMapper {
    int countByExample(TeamDoExample example);

    int deleteByExample(TeamDoExample example);

    int deleteByPrimaryKey(TeamDoKey key);

    int insert(TeamDo record);

    int insertSelective(TeamDo record);

    List<TeamDo> selectByExample(TeamDoExample example);

    TeamDo selectByPrimaryKey(TeamDoKey key);

    int updateByExampleSelective(@Param("record") TeamDo record, @Param("example") TeamDoExample example);

    int updateByExample(@Param("record") TeamDo record, @Param("example") TeamDoExample example);

    int updateByPrimaryKeySelective(TeamDo record);

    int updateByPrimaryKey(TeamDo record);
}