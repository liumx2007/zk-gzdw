package com.zzqx.support.utils.net;

import com.zzqx.mvc.commons.CountInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpClientUploadFile {
    public String httpClientUploadFile(MultipartFile file,String token) {
//        final String remote_url = "http://192.168.1.99:8080/demo/file/upload";// 第三方服务器请求地址
        CountInfo countInfo = new CountInfo();
//        final String remote_url = countInfo.FILE_UPLOAD+"?businessEnumType=15&userId="+1+"&retainFileName=1&saveToDb=2&sortNumber=1&serverToken="+token;
        final String remote_url = countInfo.FILE_UPLOAD;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            String fileName = file.getOriginalFilename();
            HttpPost httpPost = new HttpPost(remote_url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
//            builder.addTextBody("filename", fileName);// 类似浏览器表单提交，对应input的name和value
            builder.addTextBody("businessEnumType","15");
            builder.addTextBody("userId", "1");
            builder.addTextBody("retainFileName","1");
            builder.addTextBody("saveToDb","2");
            builder.addTextBody("sortNumber","1");
            builder.addTextBody("serverToken",token);
            builder.addTextBody("login-token",token);
            builder.addTextBody("sso-type","business");
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
