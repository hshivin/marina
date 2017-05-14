package com.hivin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hivin.service.ISlowLogService;
import com.hivin.tools.DateUtil;
import com.hivin.tools.ShellUtil;
import com.hivin.vo.QueryParam;
import com.hivin.vo.SlowLogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/9
 */

@Service("slowLogService")
public class ISlowLogServiceImpl implements ISlowLogService {


    private static final Logger log = LoggerFactory.getLogger(ISlowLogServiceImpl.class);

    @Override
    public List<SlowLogVo> getSlow(QueryParam queryParam) {
        //参数清分
        clearQueryParam(queryParam);

        String slowFile = getSlowFile(queryParam.getDbName());
        log.info("分析慢查询日志文件：{}",slowFile);
        //生成命令
        String commond = appendCommond(queryParam.getFrom(), queryParam.getTo(), queryParam.getFilter(), slowFile);
        log.info("执行pt-query-digest命令：{}",commond);
        //获取报告
        List<SlowLogVo> slowLogVos = getSlowReport(commond);

        return slowLogVos;
    }


    /**
     * 获取慢查询日志文件绝对路径
     *
     * @param dbName
     * @return
     */

    private String getSlowFile(String dbName) {
        String slowFile = "/data/logs/slow.log";
        return slowFile;
    }


    /**
     * 分清参数
     *
     * @param queryParam
     * @return
     */
    private QueryParam clearQueryParam(QueryParam queryParam) {
        Date now = new Date();
        String from = "";
        String to = "";
        String type = queryParam.getTimeType();
        int period = queryParam.getPeriod();
        switch (type) {
            case "hour":
                from = DateUtil.formatDateToStandard(DateUtil.getHourDifference(now, -8 - period));
                to = DateUtil.formatDateToStandard(DateUtil.getHourDifference(now, -8));
                break;
            case "day":
                from = DateUtil.formatDateToStandard(DateUtil.getHourDifference(DateUtil.getDayDifference(now, 0 - period), -8));
                to = DateUtil.formatDateToStandard(DateUtil.getHourDifference(now, -8));
                break;
            default:
                from = DateUtil.formatDateToStandard(DateUtil.getHourDifference(DateUtil.stringToDate(queryParam.getFrom()), -8));
                to = DateUtil.formatDateToStandard(DateUtil.getHourDifference(DateUtil.stringToDate(queryParam.getTo()), -8));
                break;
        }

        if (from != "" && to != "") {
            queryParam.setFrom(from);
            queryParam.setTo(to);
        }

        return queryParam;
    }

    /**
     * 生成报告
     *
     * @param commond
     * @return
     */
    private List<SlowLogVo> getSlowReport(String commond) {
        List<SlowLogVo> logVos = new ArrayList<>();
        //执行命令
        String result = ShellUtil.excuteShell(commond);
        if (result != null && result != "") {
            JSONObject slowReport = JSON.parseObject(result);
            JSONArray jsonArray = slowReport.getJSONArray("classes");
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                SlowLogVo slowLogVo = new SlowLogVo();
                slowLogVo.setSample(jsonObject.getString("fingerprint"));
                slowLogVo.setQueryCount(jsonObject.getInteger("query_count"));

                Date tsMax = DateUtil.getHourDifference(jsonObject.getString("ts_max").replaceAll("T", " "), 8);
                Date tsMin = DateUtil.getHourDifference(jsonObject.getString("ts_min").replaceAll("T", " "), 8);
                slowLogVo.setTsMax(DateUtil.formatDateToStandard(tsMax));
                slowLogVo.setTsMin(DateUtil.formatDateToStandard(tsMin));

                JSONObject metrics = jsonObject.getJSONObject("metrics");
                slowLogVo.setDbName(metrics.getJSONObject("db").getString("value"));
                slowLogVo.setHost(metrics.getJSONObject("host").getString("value"));

                JSONObject queryTime = metrics.getJSONObject("Query_time");
                slowLogVo.setQueryAvg(queryTime.getString("avg"));
                slowLogVo.setQueryMax(queryTime.getString("max"));
                slowLogVo.setQueryMedian(queryTime.getString("median"));
                slowLogVo.setQueryMin(queryTime.getString("min"));
                slowLogVo.setQueryPct95(queryTime.getString("pct_95"));
                slowLogVo.setQuerySum(queryTime.getString("sum"));

                logVos.add(slowLogVo);
            }

        }
        return logVos;
    }

    /**
     * 生成命令
     *
     * @param from
     * @param to
     * @param filter
     * @param slowLogPath
     * @return
     */
    private String appendCommond(String from, String to, String filter, String slowLogPath) {
        StringBuffer commond = new StringBuffer();
        String digest = ShellUtil.excuteShell("which pt-query-digest | awk '{print $1}'");
        commond.append(digest).append(" --output").append(" json");
        if (filter != null && filter != "") {
            commond.append(" --filter ").append("'").append(filter).append("'");
        }
        commond.append(" --since ").append("'").append(from).append("'");
        commond.append(" --until ").append("'").append(to).append("'");

        if (filter != null && filter != "") {
            //暂不实现
        }

        commond.append(" ").append(slowLogPath);
        return commond.toString();
    }

    public static void main(String[] args) {
        ISlowLogServiceImpl slowLogService = new ISlowLogServiceImpl();
        String commond = slowLogService.appendCommond("2017-03-09 00:00:00", "2017-03-09 10:00:00", null, "/data/logs/slow.log");
        slowLogService.getSlowReport(commond);

    }
}
