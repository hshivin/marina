package com.hivin.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShellUtil {
    static Logger logger = LoggerFactory.getLogger(ShellUtil.class);

    public static int executeShell(String shellCommand, String basePath) {
        int success = 0;
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {

            Process pid = null;
            String[] cmd = {"/bin/sh", "-c", shellCommand};
            //执行Shell命令
            pid = Runtime.getRuntime().exec(cmd);
            if (pid != null) {
                stringBuffer.append("进程号：").append(pid.toString()).append("\r\n");
                //bufferedReader用于读取Shell的输出内容
                bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
                pid.waitFor();
            } else {
                stringBuffer.append("没有pid\r\n");
            }
            String line = null;
            //读取Shell的输出内容，并添加到stringBuffer中
            while (bufferedReader != null &&
                    (line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\r\n");
            }
        } catch (Exception ioe) {
            logger.error(String.valueOf(ioe.getMessage()));
        } finally {
            if (bufferedReader != null) {
                OutputStreamWriter outputStreamWriter = null;
                try {
                    bufferedReader.close();
                    //将Shell的执行情况输出到日志文件中
                    logger.info("shell执行结果为:{}", stringBuffer.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        outputStreamWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            success = 1;
        }
        return success;
    }


    public static List<String> doShell(String shellCommand) {

        List<String> success = new ArrayList<String>();
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        //格式化日期时间，记录日志时使用

        try {

            Process pid = null;
            String[] cmd = {"/bin/sh", "-c", shellCommand};
            //执行Shell命令
            pid = Runtime.getRuntime().exec(cmd);
            if (pid != null) {
                stringBuffer.append("进程号：").append(pid.toString()).append("\r\n");
                //bufferedReader用于读取Shell的输出内容
                bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
                pid.waitFor();
            } else {
                stringBuffer.append("没有pid\r\n");
            }
            String line = null;
            //读取Shell的输出内容，并添加到stringBuffer中
            while (bufferedReader != null &&
                    (line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\r\n");
                success.add(line);
            }
        } catch (Exception ioe) {

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    logger.info("shell执行结果为:{}", stringBuffer.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    public static String excuteShell(String shellCommand) {

        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        //格式化日期时间，记录日志时使用

        try {

            Process pid = null;
            String[] cmd = {"/bin/sh", "-c", shellCommand};
            //执行Shell命令
            pid = Runtime.getRuntime().exec(cmd);
            if (pid != null) {
                //bufferedReader用于读取Shell的输出内容
                bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
                pid.waitFor();
            } else {
                stringBuffer.append("没有pid");
            }
            String line = null;
            //读取Shell的输出内容，并添加到stringBuffer中
            while (bufferedReader != null &&
                    (line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\r\n");
            }
        } catch (Exception ioe) {

        }
        String res = stringBuffer.reverse().delete(0,2).reverse().toString();
        return res;
    }


}
