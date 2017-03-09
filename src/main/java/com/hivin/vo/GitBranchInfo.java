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
public class GitBranchInfo {

    private String name;
    private boolean selected;
    private String value;

}
