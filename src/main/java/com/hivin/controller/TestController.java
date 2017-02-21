package com.hivin.controller;


import com.alibaba.fastjson.JSON;
import com.hivin.configure.Master;
import com.hivin.configure.Slave;
import com.hivin.dao.TestDao;
import com.hivin.service.IHelloWorldService;
import com.hivin.service.ITestService;
import com.hivin.vo.TestDO;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/test")
public class TestController {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Value("${test}")
    String test;

    @Autowired
    TestDao testDao;

    @Resource(name = "helloWorldService")
    IHelloWorldService helloWorldService;

    @Resource(name = "testService")
    ITestService testService;


    @RequestMapping(value = "/test_0", method = RequestMethod.GET)
    public String test_0(@RequestParam(value = "name", defaultValue = "hivin", required = false) String name) {

        return name + "," + helloWorldService.getHelloMessage();
    }


    @RequestMapping(value = "/test_1", method = RequestMethod.GET)
    public String test_1() {

        return test;
    }

    @RequestMapping(value = "/test_2", method = RequestMethod.GET)
    @Master
    public String test_2() {

        TestDO t = testService.getTestDOById(7);
        return JSONObject.fromObject(t).toString();
    }

    @RequestMapping(value = "/test_3", method = RequestMethod.GET)
    @Slave
    public String test_3() {

        return JSON.toJSONString(testService.getTestDOById(7));
    }

}
