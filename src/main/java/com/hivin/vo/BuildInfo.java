package com.hivin.vo;

import com.offbytwo.jenkins.model.Build;
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
public class BuildInfo {
    private Integer number;
    private Integer queueId;
    private String url;


}
