package com.hivin.service;

import java.io.IOException;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/2/21
 */
public interface IBuildService {
    /**
     * 获取build控制台日志
     *
     * @param jobName
     * @param num
     * @return
     */
    public String getBuildLog(String jobName, int num);


}
