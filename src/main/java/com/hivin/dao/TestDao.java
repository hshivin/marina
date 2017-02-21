package com.hivin.dao;

import com.hivin.vo.TestDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by LP-566 on 2016/12/19.
 */
public interface TestDao {

    public TestDO getById(@Param("id") int id);

    @Select("select * from test where `ID`=${id}")
    public TestDO getOneById(@Param("id") int id);

}
