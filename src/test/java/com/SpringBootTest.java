package com;

import com.hivin.service.ITestService;
import com.hivin.vo.TestDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;


/**
 * Created by
 *
 * @auth:hivin
 * @date:17/1/23
 */

@ActiveProfiles(value="dev")
public class SpringBootTest extends CaseBase{

    @Autowired
    @Qualifier("testService")
    private ITestService testService;

    @Test
    public void testApp() {
        TestDO user= testService.getTestDOById(7);
        Assert.assertNotNull(user);
    }

}
