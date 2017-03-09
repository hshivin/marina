package com.hivin.vo;

import com.offbytwo.jenkins.model.Executable;
import com.offbytwo.jenkins.model.QueueTask;
import lombok.Data;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/2
 */
@Data
public class QueueItemInfo {
    private Long id;
    private Long inQueueSince;
    private String params;
    private boolean stuck;
    private QueueTask task;
    private String url;
    private String why;
    private Executable executable;
}
