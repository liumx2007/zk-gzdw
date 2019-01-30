package com.zzqx.support.utils.net;


import com.zzqx.mvc.commons.CountInfo;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * httpPost请求，application/json格式
 * @author wjw
 * @date   2019-1-28 上午11:03:24
 */
public class HttpRequest {
    public static void main(String[] args) throws Exception{
        DefaultHttpClient httpClient = new DefaultHttpClient();
        CountInfo countInfo = new CountInfo();
        String url = countInfo.DW_SYSTEM_LOGIN;
        HttpPost httpPost = new HttpPost(url);

        // 设置请求的header
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");

        // 设置请求的参数
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("username", "banzhang");
        jsonParam.put("password", "admin");

        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        String json2 = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSONObject.fromObject(json2);

        // 打印执行结果
        System.out.println(jsonObject);
    }
}
