package com.hivin.service;

import com.hivin.vo.JobInfo;
import com.offbytwo.jenkins.model.Plugin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/2/21
 */
public interface IJenkinsService {
    /**
     * 检查jekins心跳
     *
     * @return
     */
    public String heart();

    /**
     * 获取jekins全部jobs
     *
     * @return
     */
    public List<JobInfo> getAllJobs();

    List<Plugin> getAllPlugins();
}
