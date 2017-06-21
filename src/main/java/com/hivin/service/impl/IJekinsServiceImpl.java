package com.hivin.service.impl;

import com.alibaba.fastjson.JSON;
import com.hivin.service.IJenkinsService;
import com.hivin.vo.JenkinsBean;
import com.hivin.vo.JobInfo;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/1
 */
@Service("jkService")
public class IJekinsServiceImpl implements IJenkinsService {
    Logger log = LoggerFactory.getLogger(IJekinsServiceImpl.class);

    @Resource
    JenkinsBean jenkinsBean;

    @Override
    public String heart() {
        String result = "work";
        JenkinsServer jenkinsServer = jenkinsBean.getJenkinsServer();
        if (!jenkinsServer.isRunning()) result = "death";
        return result;
    }

    @Override
    public List<JobInfo> getAllJobs() {
        List<JobInfo> list = new ArrayList<>();
        JenkinsServer jenkinsServer = jenkinsBean.getJenkinsServer();
        try {
            Map<String, Job> map = jenkinsServer.getJobs();
            log.info("jekins返回jobs：{}", JSON.toJSONString(map));
            for (String key : map.keySet()) {
                JobInfo jobInfo = new JobInfo();
                jobInfo.setJobName(map.get(key).getName());
                list.add(jobInfo);
            }
        } catch (IOException e) {
            log.info("jekins返回job异常：{}", e);
        }
        return list;
    }

    @Override
    public List<Plugin> getAllPlugins() {
        List<Plugin> plugins = new ArrayList<>();
        try {
            plugins = jenkinsBean.getJenkinsServer().getPluginManager().getPlugins();
        } catch (Exception e) {

        }
        return plugins;

    }
}
