package com.hivin.vo;

import com.offbytwo.jenkins.JenkinsServer;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/1
 */
@Component
public class JenkinsBean {
    @Resource
    SecretInfo secretInfo;

    private JenkinsServer jenkinsServer;

    public JenkinsServer getJenkinsServer() {
        try {
            this.jenkinsServer = new JenkinsServer(new URI(secretInfo.getUrl()), secretInfo.getUsername(), secretInfo.getPassword());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return jenkinsServer;
    }


}
