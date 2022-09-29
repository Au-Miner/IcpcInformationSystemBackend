package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.SchoolDo;
import com.IcpcInformationSystemBackend.model.entity.SchoolDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolDoMapper {
    int countByExample(SchoolDoExample example);

    int deleteByExample(SchoolDoExample example);

    int deleteByPrimaryKey(String schoolId);

    int insert(SchoolDo record);

    int insertSelective(SchoolDo record);

    List<SchoolDo> selectByExample(SchoolDoExample example);

    SchoolDo selectByPrimaryKey(String schoolId);

    int updateByExampleSelective(@Param("record") SchoolDo record, @Param("example") SchoolDoExample example);

    int updateByExample(@Param("record") SchoolDo record, @Param("example") SchoolDoExample example);

    int updateByPrimaryKeySelective(SchoolDo record);

    int updateByPrimaryKey(SchoolDo record);
}