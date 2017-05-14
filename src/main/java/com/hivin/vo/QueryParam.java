package com.hivin.vo;

import lombok.Data;

/**
 * Created by
 * 参数对象
 *
 * @auth:hivin
 * @date:17/3/15
 */
@Data
public class QueryParam {
    private int period;//时间间隔
    private String from;//开始时间
    private String to;//结束时间
    private String timeType;//查询时间类型和period关联使用
    private String dbName;//数据库名称
//    private String slowLogFile;//慢查询文件路径

    private String filter;//过滤条件  暂不实现

}
