package com.hivin.service.impl;


import com.alibaba.fastjson.JSON;
import com.hivin.enums.ErrorType;
import com.hivin.service.IBuildService;
import com.hivin.service.IJobService;
import com.hivin.vo.JenkinsBean;
import com.hivin.vo.JobInfo;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/2/21
 */
@Service("buildService")
public class IBuildServiceImpl implements IBuildService {
    Logger log = LoggerFactory.getLogger(IBuildServiceImpl.class);
    @Resource
    JenkinsBean jenkinsBean;

    @Override
    public String getBuildLog(String jobName, int num) {
        String result = "";
        try {
            JobWithDetails jobWithDetails = jenkinsBean.getJenkinsServer().getJob(jobName);
            Build build = jobWithDetails.getBuildByNumber(num);
            BuildWithDetails buildWithDetails = build.details();
            result = buildWithDetails.getConsoleOutputHtml();
        } catch (IOException e) {
            log.error(ErrorType.BUILD_ERROR.getName() + "获取信息异常：{}", e);
        }
        return result;
    }


}



