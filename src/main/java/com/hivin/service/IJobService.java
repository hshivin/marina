package com.hivin.service;

import com.hivin.vo.GitBranchInfo;
import com.hivin.vo.JobInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/2/21
 */
public interface IJobService {
    /**
     * 获取job信息
     *
     * @param jobName
     * @return
     */
    public JobInfo getJobInfo(String jobName);

    public List<GitBranchInfo> getJobGitBranchAndBatch(String jobName);

    public String getJobConfigure(String jobName);

    /**
     * 执行job
     *
     * @param jobName
     * @return
     */
    public int buildJob(String jobName);

    public int buildJob(String jobName, Map<String, String> params);

    /**
     * 获取build状态
     *
     * @param jobName
     * @param num
     * @return
     */
    public Boolean isJobFinish(String jobName, int num);

}
