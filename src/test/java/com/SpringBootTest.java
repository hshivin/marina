package com;

import com.hivin.service.IJobService;
import com.hivin.tools.HttpUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by
 *
 * @auth:hivin
 * @date:17/1/23
 */

@ActiveProfiles(value = "dev")
public class SpringBootTest extends CaseBase {

    @Resource(name = "jobService")
    IJobService jobService;

    @Test
    public void testJobService() {
        String jobName = "first";
//        jobService.getJobConfigure(jobName);
        jobService.getJobGitBranchAndBatch(jobName);

//        Map<String, String> map = new HashMap<>();
//        map.put("branch", "auth");
//        jobService.buildJob(jobName, map);
    }


    public static void main(String[] args) {

        String path = "http://127.0.0.1:8088/job/second/descriptorByName/net.uaznia.lukanus.hudson.plugins.gitparameter.GitParameterDefinition/fillValueItems";
        String cookie = "JSESSIONID.87dcef25=cm5xeh8mg48i1fivectvx7r9j; JSESSIONID.cdb34009=wwxpxagtny40d71z2mt3krgm; JSESSIONID.6dcdcef6=mp0li8fblh17b96yfge6y8o2; JSESSIONID.ebd19cd2=1djaed5v82osbc3zqa8nikh75; JSESSIONID.8966a134=1asfdke9z5owtgdmtu89wquyu; jenkins-timestamper-offset=-28800000; JSESSIONID.aae6e747=e8aoc9hkenlz1xixwsl6x1sd9; ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE=YWRtaW46MTQ4OTU0MjIzMTc1MDpjNjg5MzYyNjA3MmU0MzI1YTdjMTFlNzYwZTE4MTRhZGYwNjQzZjE3Mjk1NDMzNjVmYzIzYzIzOWI5NDBhMWJl; JSESSIONID.cdc2e27c=2cyib9mo0dar1psy7vgepzvpy; JSESSIONID.b306c367=dhjenp5jt6zw1zshp28iuszf; screenResolution=1280x800";
        String jenkinsCrumb = "3a91a0940edfa57adbb178bb636e2fee";
        String s=HttpUtil.getGitBranch(path,"param=branch");
        System.out.println(s);

         //        Map<String, String> map = new HashMap<>();
//        map.put("branch", "auth");
//        jobService.buildJob(jobName, map);
    }
}
