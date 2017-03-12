package com;


import com.hivin.tools.DateUtil;
import com.hivin.tools.ShellUtil;
import org.junit.Test;

import org.springframework.test.context.ActiveProfiles;

import java.util.Date;


/**
 * Created by
 *
 * @auth:hivin
 * @date:17/1/23
 */

@ActiveProfiles(value = "dev")
public class SpringBootTest extends CaseBase {


    @Test
    public void testApp() {

    }

    public static void main(String[] args) {
        String commond = "/usr/local/bin/pt-query-digest --output=json  /data/logs/slow.log";
        System.out.println(commond);
        System.out.println(ShellUtil.excuteShell(commond));

        Long l=new Date().getTime();
        String s=DateUtil.ms2Time(String.valueOf(l),"YYYYMMddHHmmssSSS");
        System.out.println(s);
        System.out.println(s.length());

    }

}

