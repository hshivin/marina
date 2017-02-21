package com.hivin.tools;


import java.io.*;
import java.text.SimpleDateFormat;

public class FileUtil {

  public static boolean writeRus(String filePath, String data) {
    try {
      File f = new File(filePath);
      if (f.exists()) {
      } else {
        f.createNewFile();// 不存在则创建
      }
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      java.util.Date uDate = new java.util.Date();
      String strDate = df.format(uDate);

      BufferedWriter output = new BufferedWriter(new FileWriter(f, true));
      output.write(data);
      output.close();

      return true;

    } catch (Exception e) {
      return false;
    }

  }

  public static void mkdir(String d) {
    File file = new File(d);
//如果文件夹不存在则创建
    if (!file.exists() && !file.isDirectory()) {

      file.mkdir();
    }

  }


  public static void writeFile(String filePath, String sets) {
    try {
      FileWriter fw = new FileWriter(filePath);
      PrintWriter out = new PrintWriter(fw);
      out.write(sets);
      out.println();
      fw.close();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static String readFile(String path) {
    File file = new File(path);
    BufferedReader reader = null;
    String laststr = "";
    try {
      reader = new BufferedReader(new FileReader(file));
      String tempString = null;
      while ((tempString = reader.readLine()) != null) {
        laststr = laststr + tempString;
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
    return laststr;
  }

}
