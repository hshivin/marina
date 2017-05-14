package com.hivin.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by
 * 返回对象
 *
 * @auth:hivin
 * @date:17/3/15
 */
@Data
public class SlowLogVo {
    private String sample;
    private String dbName;
    private String host;
    private int queryCount;
    private String tsMax;//运行最大时间
    private String tsMin;//运行最小时间
    private String queryAvg;
    private String queryMax;
    private String queryMedian;
    private String queryMin;
    private String queryPct95;
    private String querySum;
}
