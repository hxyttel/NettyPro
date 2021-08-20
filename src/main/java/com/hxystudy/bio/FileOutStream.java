package com.hxystudy.bio;

import java.io.*;

/**
 * @Author ： hxy
 * @create ： 2021/8/20 15:35
 * @Description :
 */
public class FileOutStream {
    public static void main(String[] args) throws IOException {
        // 将数据写入文件中
       // String str = "BIO stream";
        File file = new File("D://foTest.txt");
      /*  FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(str.getBytes());
        fileOutputStream.close();*/

        // 从文件中读出数据
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String str = "" ;
        System.out.println(bufferedReader.readLine());
        while ((str = bufferedReader.readLine()) != null){
            stringBuffer = stringBuffer.append(str);
        }
        System.out.println(stringBuffer);
    }
}
