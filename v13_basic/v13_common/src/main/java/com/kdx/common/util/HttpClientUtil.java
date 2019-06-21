package com.kdx.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;

/**
 * @author kdx
 * @Date 2019/6/18
 */
public class HttpClientUtil {

    /**
     * 不携带参数的get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String doGet(String url) throws IOException {

        //doGet(url,null);

        //打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //输入网址(创建get对象)
        HttpGet get = new HttpGet(url);
        //按回车，执行get请求，获取response对象
        CloseableHttpResponse response = httpClient.execute(get);
        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode==200){
            //获取响应结果
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder str = new StringBuilder();
            while((len=inputStream.read(bytes))!=-1){
                str.append(new String(bytes,0,len));
            }
            return str.toString();
        }else {

            return "网络波动，请刷新。";
        }

    }

    /**
     * 请求携带参数的get请求
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, Map<String,String> param){

        //打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = null;
        CloseableHttpResponse response = null;
        String result="";
        try {
            uriBuilder = new URIBuilder(url);
            if(param!=null){
                for (String key : param.keySet()) {
                    uriBuilder.addParameter(key,param.get(key));
                }
            }
            URI uri = uriBuilder.build();
            HttpGet get = new HttpGet(uri);
            response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==200){
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpClient!=null){

                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;

    }

}
