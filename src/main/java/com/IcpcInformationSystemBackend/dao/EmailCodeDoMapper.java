package com.IcpcInformationSystemBackend.dao;

import com.IcpcInformationSystemBackend.model.entity.EmailCodeDo;
import com.IcpcInformationSystemBackend.model.entity.EmailCodeDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailCodeDoMapper {
    int countByExample(EmailCodeDoExample example);

    int deleteByExample(EmailCodeDoExample example);

    int deleteByPrimaryKey(String userEmail);

    int insert(EmailCodeDo record);

    int insertSelective(EmailCodeDo record);

    List<EmailCodeDo> selectByExample(EmailCodeDoExample example);

    EmailCodeDo selectByPrimaryKey(String userEmail);

    int updateByExampleSelective(@Param("record") EmailCodeDo record, @Param("example") EmailCodeDoExample example);

    int updateByExample(@Param("record") EmailCodeDo record, @Param("example") EmailCodeDoExample example);

    int updateByPrimaryKeySelective(EmailCodeDo record);

    int updateByPrimaryKey(EmailCodeDo record);
}