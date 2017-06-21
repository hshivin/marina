package com.hivin.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hivin.enums.ErrorType;
import com.hivin.service.IJobService;
import com.hivin.tools.HttpUtil;
import com.hivin.tools.StringUtil;
import com.hivin.vo.*;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.*;
import org.apache.http.HttpResponse;
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
 * @date:17/2/21
 */
@Service("jobService")
public class IJobServiceImpl implements IJobService {
    Logger log = LoggerFactory.getLogger(IJobServiceImpl.class);
    @Resource
    JenkinsBean jenkinsBean;
    @Resource
    SecretInfo secretInfo;

    @Override
    public JobInfo getJobInfo(String jobName) {
        JobInfo jobInfo = new JobInfo();
        try {
            JobWithDetails jobWithDetails = jenkinsBean.getJenkinsServer().getJob(jobName);
            if (jobWithDetails == null) return jobInfo;

            jobInfo.setDisplayName(jobWithDetails.getDisplayName());
            jobInfo.setLastBuildNum(jobWithDetails.getLastBuild().getNumber());
            jobInfo.setQueueItem(jobWithDetails.getQueueItem());
            jobInfo.setInQueue(jobWithDetails.isInQueue());
            jobInfo.setBuildable(jobWithDetails.isBuildable());

            List<BuildInfo> list = new ArrayList<>();
            for (Build build : jobWithDetails.getAllBuilds()) {
                BuildInfo buildInfo = new BuildInfo();
                buildInfo.setNumber(build.getNumber());
                buildInfo.setUrl(build.getUrl());
                buildInfo.setQueueId(build.getQueueId());
                list.add(buildInfo);
            }
            jobInfo.setBuildInfos(list);
        } catch (Exception e) {
            log.error(ErrorType.JOB_ERROR.getName() + "获取信息异常：{}", e);
        }
        return jobInfo;
    }

    /**
     * @param jobName
     * @return
     */
    @Override
    public List<GitBranchInfo> getJobGitBranchAndBatch(String jobName) {
        List<GitBranchInfo> list = new ArrayList<>();
        try {
            JobWithDetails jobWithDetails = jenkinsBean.getJenkinsServer().getJob(jobName);
            if (jobWithDetails == null) {
                log.warn("{}-获取git分支失败", jobName);
                return list;
            }
            String url = jobWithDetails.getUrl();
            String path = "descriptorByName/net.uaznia.lukanus.hudson.plugins.gitparameter.GitParameterDefinition/fillValueItems?param=branch";
            String response = HttpUtil.getGitBranch(url + path, "param=branch",null);
            if (response == null || response.length() == 0) return list;
            JSONObject jsonObject = JSON.parseObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("values");
            list = JSONArray.parseArray(jsonArray.toJSONString(), GitBranchInfo.class);

        } catch (Exception e) {
            log.error(ErrorType.JOB_ERROR.getName() + "获取信息异常：{}", e);
        }
        return list;
    }

    private String getGitBranch(String url, Map<String, String> param) {
        String response = "";
        String path = "j_acegi_security_check";
        Map<String, String> map = new HashMap();
        map.put("j_username", secretInfo.getUsername());
        map.put("j_password", secretInfo.getPassword());
        map.put("from", "/");
        map.put("Jenkins-Crumb", "f334bbad5532e280fbd3e59d0cb84b0b");
        map.put("json", "{\"j_username\": \"admin\", \"j_password\": \"Wang123@\", \"remember_me\": false, \"from\": \"/\", \"Jenkins-Crumb\": \"f334bbad5532e280fbd3e59d0cb84b0b\"}");
        map.put("Submit", "登录");
        response = HttpUtil.getGitBranch(url, param, secretInfo.getUrl() + path, map);

        return response;
    }


    @Override
    public String getJobConfigure(String jobName) {
        String jobXml = "";
        try {
            JobWithDetails jobWithDetails = jenkinsBean.getJenkinsServer().getJob(jobName);
            if (jobWithDetails == null) return jobXml;
            jobXml = jenkinsBean.getJenkinsServer().getJobXml(jobName);
        } catch (Exception e) {
            log.error(ErrorType.JOB_ERROR.getName() + "获取信息异常：{}", e);
        }
        return jobXml;
    }

    @Override
    public int buildJob(String jobName) {
        return buildJob(jobName, null);
    }

    @Override
    public int buildJob(String jobName, Map<String, String> params) {
        int buidNum = -1;
        try {
            JobWithDetails jobWithDetails = jenkinsBean.getJenkinsServer().getJob(jobName);
            QueueReference queueReference = null;
            if (params == null) {
                queueReference = jobWithDetails.build(true);
            } else {
                queueReference = jobWithDetails.build(params, true);
            }
            jobWithDetails.getLastStableBuild();
            if (queueReference == null) return buidNum;
            String part = queueReference.getQueueItemUrlPart();
            String[] p = part.split("\\/");
            buidNum = Integer.parseInt(p[5]);
        } catch (Exception e) {
            log.error(ErrorType.JOB_ERROR.getName() + "获取信息异常：{}", e);
        }
        return buidNum;
    }

    @Override
    public Boolean isJobFinish(String jobName, int num) {
        boolean status = false;
        try {
            JobWithDetails jobWithDetails = jenkinsBean.getJenkinsServer().getJob(jobName);
            Build build = jobWithDetails.getBuildByNumber(num);
            BuildWithDetails buildWithDetails = build.details();
            status = buildWithDetails.isBuilding();
        } catch (IOException e) {
            log.error(ErrorType.JOB_ERROR.getName() + "获取信息异常：{}", e);
        }
        return status;
    }

}



