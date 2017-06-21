package com;

import com.hivin.service.IJenkinsService;
import com.hivin.service.IJobService;
import com.hivin.tools.HttpUtil;
import com.offbytwo.jenkins.model.Plugin;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by
 *
 * @auth:hivin
 * @date:17/1/23
 */

@ActiveProfiles(value = "dev")
public class SpringBootTest extends CaseBase {

    protected TeamCreateModel teamCreateMode = TeamCreateFactory.teamCreateMode;


    @Resource(name = "jobService")
    IJobService jobService;

    @Resource(name = "jkService")
    IJenkinsService jenkinsService;

    @Test
    public void testJobService() {
        String jobName = "first";
//        jobService.getJobConfigure(jobName);
        jobService.getJobGitBranchAndBatch(jobName);

//        Map<String, String> map = new HashMap<>();
//        map.put("branch", "auth");
//        jobService.buildJob(jobName, map);

         System.out.println(teamCreateMode.getTeamName());

    }

    @Test
    public void testAllPlugins() {

        List<Plugin> plugins = jenkinsService.getAllPlugins();
        for (Plugin plugin : plugins) {
            if (plugin.getUrl().contains("gitparameter.GitParameterDefinition")) {
                String s = plugin.getUrl();
            }
        }

    }


    public static void main(String[] args) {

        String response = "";
        String path = "j_acegi_security_check?lang=en_US";
        Map<String, String> map = new HashMap();
        map.put("j_username", "admin");
        map.put("j_password", "Wang123@");
        map.put("from", "/");
        map.put("Jenkins-Crumb", "f334bbad5532e280fbd3e59d0cb84b0b");
        map.put("json", "{\"j_username\": \"admin\", \"j_password\": \"Wang123@\", \"remember_me\": false, \"from\": \"/\", \"Jenkins-Crumb\": \"f334bbad5532e280fbd3e59d0cb84b0b\"}");
        map.put("Submit", "登录");

        String url = "http://127.0.0.1:8088/job/first/descriptorByName/net.uaznia.lukanus.hudson.plugins.gitparameter.GitParameterDefinition/fillValueItems?param=branch&lang=en_US";

        String header = HttpUtil.getCookie("http://127.0.0.1:8088/" + path, map);
//         header="JSESSIONID.315a3e2b=ormwv8ctevkvz98qnebvm498;Path=/;HttpOnly";
        response = HttpUtil.getGitBranch(url, "param=branch", header);

    }
}
