package com.hivin.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/6
 */
@Data
public class JobParam {
    private String branch;
    private String gitUrl;
}
