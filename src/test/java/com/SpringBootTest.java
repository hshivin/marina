package com;

import com.hivin.WebApplication;
import com.hivin.configure.Master;
import com.hivin.dao.TestDao;
import com.hivin.service.ITestService;
import com.hivin.vo.TestDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by
 *
 * @auth:hivin
 * @date:17/1/23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {WebApplication.class})
@ActiveProfiles(value="dev")
public class SpringBootTest {

    @Autowired
    @Qualifier("testService")
    private ITestService testService;

    @Test
    public void testApp() {
        TestDO user= testService.getTestDOById(7);
        Assert.assertNotNull(user);
    }

}
