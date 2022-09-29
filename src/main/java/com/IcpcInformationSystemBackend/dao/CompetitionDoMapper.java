package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.CompetitionDo;
import com.IcpcInformationSystemBackend.model.entity.CompetitionDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompetitionDoMapper {
    int countByExample(CompetitionDoExample example);

    int deleteByExample(CompetitionDoExample example);

    int deleteByPrimaryKey(String competitionId);

    int insert(CompetitionDo record);

    int insertSelective(CompetitionDo record);

    List<CompetitionDo> selectByExample(CompetitionDoExample example);

    CompetitionDo selectByPrimaryKey(String competitionId);

    int updateByExampleSelective(@Param("record") CompetitionDo record, @Param("example") CompetitionDoExample example);

    int updateByExample(@Param("record") CompetitionDo record, @Param("example") CompetitionDoExample example);

    int updateByPrimaryKeySelective(CompetitionDo record);

    int updateByPrimaryKey(CompetitionDo record);
}