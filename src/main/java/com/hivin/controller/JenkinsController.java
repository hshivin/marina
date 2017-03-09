package com.hivin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hivin.service.IBuildService;
import com.hivin.service.IJenkinsService;
import com.hivin.service.IJobService;
import com.hivin.tools.BeanTool;
import com.hivin.vo.GitBranchInfo;
import com.hivin.vo.JobInfo;
import com.hivin.vo.JobParam;
import com.sun.istack.internal.NotNull;
import org.apache.commons.beanutils.BeanMap;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/jk")
public class JenkinsController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(JenkinsController.class);

    @Resource(name = "jkService")
    IJenkinsService jkService;

    @Resource(name = "buildService")
    IBuildService buildService;

    @Resource(name = "jobService")
    IJobService jobService;

    @RequestMapping(value = "/heart", method = RequestMethod.GET)
    public String heartCheck() {
        return jkService.heart();
    }

    @RequestMapping(value = "/getBuildLog/{jobName}", method = RequestMethod.POST)
    public String getBuildLog(@PathVariable(value = "jobName") String jobName, @RequestParam(value = "num") Integer num) {
        return buildService.getBuildLog(jobName, num);
    }

    @RequestMapping(value = "/getJobInfo/{jobName}", method = RequestMethod.GET)
    public JobInfo getJobInfo(@PathVariable(value = "jobName") String jobName) {
        return jobService.getJobInfo(jobName);
    }


    @RequestMapping(value = "/getGitBranchAndTag/{jobName}", method = RequestMethod.GET)
    public List<GitBranchInfo> getGitBranchAndTag(@PathVariable(value = "jobName") String jobName) {
        return jobService.getJobGitBranchAndBatch(jobName);
    }

    @RequestMapping(value = "/runJob/{jobName}", method = RequestMethod.POST)
    public int runJob(@PathVariable(value = "jobName") String jobName, @RequestBody JobParam jobParam) {
        Map<String, String> map = BeanTool.objToMap(jobParam);
        int buidNum = jobService.buildJob(jobName, map);
        return buidNum;
    }

    @RequestMapping(value = "/getBuildStatus/{jobName}", method = RequestMethod.GET)
    public Boolean getBuildStatus(@PathVariable(value = "jobName") String jobName, @RequestParam(value = "buildNum") Integer buildNum) {
        return buildService.getBuildStatus(jobName, buildNum);
    }

    @RequestMapping(value = "/getAllJobs", method = RequestMethod.GET)
    public List<JobInfo> getAllJobs() {

        return jkService.getAllJobs();
    }
}
