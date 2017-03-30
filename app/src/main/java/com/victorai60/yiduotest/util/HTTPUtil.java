package com.victorai60.yiduotest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author: victor
 * Date: 2015-12-25 12:25
 * Email: 497042813@qq.com
 */
public class HTTPUtil {

    public static String get(String urlString) {
        HttpURLConnection connection = null;
        InputStream is = null;

        try {
            URL url = new URL(urlString);
            // 获得URL对象
            connection = (HttpURLConnection) url.openConnection();
            // 获得HttpURLConnection对象
            connection.setRequestMethod("GET");
            // 默认为GET
            connection.setUseCaches(false);
            // 不使用缓存
            connection.setConnectTimeout(10000);
            // 设置超时时间
            connection.setReadTimeout(10000);
            // 设置读取超时时间
            connection.setDoInput(true);

            // 设置是否从HttpURLConnection读入，默认情况下是true;
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 相应码是否为200
                is = connection.getInputStream();
                // 获得输入流
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                // 包装字节流为字符流
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
