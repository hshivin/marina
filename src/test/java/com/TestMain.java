package com;

import com.hivin.tools.DateUtil;
import com.hivin.tools.ShellUtil;

import java.util.Date;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/3/16
 */
public class TestMain {

    public static void main(String[] args) {
//        String commond = "/usr/local/bin/pt-query-digest --output=json  /data/logs/slow.log";
        String commond = "which pt-query-digest | awk '{print $1}'";
        System.out.println(commond);
//        commond=ShellUtil.excuteShell(commond)+" --output=json  /data/logs/slow.log";
        System.out.println(commond);
        System.out.println(ShellUtil.excuteShell(commond));

        Long l=new Date().getTime();
        String s= DateUtil.ms2Time(String.valueOf(l),"YYYYMMddHHmmssSSS");
        System.out.println(s);
        System.out.println(s.length());

    }
}
