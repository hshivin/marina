package com.hivin.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/9
 */
@Data
public class QueryReviewRecordHistory {

    private Long id;
    private String checksum;
    private String sample;
    private String fingerprint;
    private String distillate;
    private String db;
    private String host;
    private String user;
    private Integer queryCount;
    private Date tsMin;
    private Date tsMax;

    private Float queryTimeSum;
    private Float queryTimeMin;
    private Float queryTimeMax;
    private Float queryTimePct95;
    private Float queryTimeStddev;
    private Float queryTimeMedian;

    private Float lockTimeSum;
    private Float lockTimeMin;
    private Float lockTimeMax;
    private Float lockTimePct95;
    private Float lockTimeStddev;
    private Float lockTimeMedian;

    private Float rowsSentSum;
    private Float rowsSentMin;
    private Float rowsSentMax;
    private Float rowsSentPct95;
    private Float rowsSentStddev;
    private Float rowsSentMedian;

    private Float rowsExaminedSum;
    private Float rowsExaminedMin;
    private Float rowsExaminedMax;
    private Float rowsExaminedPct95;
    private Float rowsExaminedStddev;
    private Float rowsExaminedMedian;

    private Float rowsReadSum;
    private Float rowsReadMin;
    private Float rowsReadMax;
    private Float rowsReadPct95;
    private Float rowsReadStddev;
    private Float rowsReadMedian;

    private Date createTime;


}
