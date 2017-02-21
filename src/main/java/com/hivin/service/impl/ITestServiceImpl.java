package com.hivin.service.impl;

import com.hivin.dao.TestDao;
import com.hivin.service.IHelloWorldService;
import com.hivin.service.ITestService;
import com.hivin.vo.TestDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/2/21
 */
@Service("testService")
public class ITestServiceImpl implements ITestService {

    @Resource
    TestDao testDao;

    @Override
    public TestDO getTestDOById(int id) {
        return testDao.getById(id);
    }
}
