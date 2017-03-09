package com.hivin.vo;

import com.offbytwo.jenkins.model.QueueItem;
import lombok.Data;

import java.util.List;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/1
 */
@Data
public class JobInfo {
    private String description;
    private String displayName;
    private String jobName;
    private boolean inQueue;
    private boolean buildable;
    private Integer lastBuildNum;
    private QueueItem queueItem;
    private List<BuildInfo> buildInfos;


}
