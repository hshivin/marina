package com.hivin.tools;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static HttpResponse sendPost(String url, Map<String, String> map) {
        HttpResponse response = null;
        try {
            //POST的URL
            HttpPost httppost = new HttpPost(url);
            //建立HttpPost对象
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //建立一个NameValuePair数组，用于存储欲传送的参数
            for (String key : map.keySet()) {
                params.add(new BasicNameValuePair(key, map.get(key)));
            }
            //添加参数
            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            //设置编码
            response = new DefaultHttpClient().execute(httppost);
            //发送Post,并返回一个HttpResponse对象
            //Header header = response.getFirstHeader("Content-Length");
            //String Length=header.getValue();

        } catch (Exception e) {

        }
        return response;
    }

    public static String getHttpResult(HttpResponse response) {
        String result = "";
        try {
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
                //得到返回的字符串
                System.out.println(result);
                //打印输出
                //如果是下载文件,可以用response.getEntity().getContent()返回InputStream
            }

        } catch (Exception e) {

        }
        return result;
    }

    public static String getGitBranch(String url, Map<String, String> map, String loadingUrl, Map<String, String> loadingMap) {
        String result = "";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            //POST的URL
            HttpPost httppost = new HttpPost(loadingUrl);
            //建立HttpPost对象
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //建立一个NameValuePair数组，用于存储欲传送的参数
            for (String key : loadingMap.keySet()) {
                params.add(new BasicNameValuePair(key, loadingMap.get(key)));
            }
            //添加参数
            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httppost.setHeader("Accept-Encoding", "gzip, deflate, br");
            httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httppost.setHeader("Cache-Control", "max-age=0");
            httppost.setHeader("Connection", "keep-alive");
            httppost.setHeader("Content-Length", "325");
            httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");


            //设置编码
            httpclient.execute(httppost);
            CookieStore cookiestore = httpclient.getCookieStore();

            params.clear();
            for (String key : map.keySet()) {
                params.add(new BasicNameValuePair(key, map.get(key)));
            }
            httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpclient.setCookieStore(cookiestore);
            HttpResponse response = httpclient.execute(httppost);

            //发送Post,并返回一个HttpResponse对象
            //Header header = response.getFirstHeader("Content-Length");
            //String Length=header.getValue();
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
            }

        } catch (Exception e) {

        }
        return result;
    }

    public static String getGitBranch(String url, String param, String cookie) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "text/javascript, text/html, application/xml, text/xml, */*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//            conn.setRequestProperty("Cookie", "JSESSIONID.315a3e2b=vhczudtkncf1v4vrgoh1pshk;Path=/;HttpOnly");
            conn.setRequestProperty("Cookie", String.valueOf(cookie));
            conn.setRequestProperty("Jenkins-Crumb", "3a91a0940edfa57adbb178bb636e2fee");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String getCookie(String url, Map<String, String> map) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            conn.setRequestProperty("Connection ", "Keep-Alive");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.setRequestProperty("Content-Length", "325");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            conn.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
//            conn.setRequestProperty("Cookie","jenkins-timestamper-offset=-28800000; screenResolution=1280x800; JSESSIONID.315a3e2b=1tcfygktw9yr8k7naalw9huwp");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            String param = "";
            for (String key : map.keySet()) {
                param += key + "=" + map.get(key) + "&";
            }
            param=param.substring(0,param.length()-1);

            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            result = conn.getHeaderField("Set-Cookie");

//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.split(";")[0];
    }
}