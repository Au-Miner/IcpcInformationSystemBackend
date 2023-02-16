package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.PositionDo;
import com.IcpcInformationSystemBackend.model.entity.PositionDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PositionDoMapper {
    int countByExample(PositionDoExample example);

    int deleteByExample(PositionDoExample example);

    int deleteByPrimaryKey(String positionId);

    int insert(PositionDo record);

    int insertSelective(PositionDo record);

    List<PositionDo> selectByExample(PositionDoExample example);

    PositionDo selectByPrimaryKey(String positionId);

    int updateByExampleSelective(@Param("record") PositionDo record, @Param("example") PositionDoExample example);

    int updateByExample(@Param("record") PositionDo record, @Param("example") PositionDoExample example);

    int updateByPrimaryKeySelective(PositionDo record);

    int updateByPrimaryKey(PositionDo record);
}