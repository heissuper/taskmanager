package com.alibaba.workbench.taskmng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
@Slf4j
public class HealthCheckController {

    @RequestMapping("/health_check")
    @ResponseBody
    public String healthCheck() {
    	String  ipInfo = getHttpPage( );
        return "ok      " +  ipInfo;
    }
    
    private String  getHttpPage( ) {
		try {
			String url = "https://myip.ipip.net/";
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).build();
			Response response = client.newCall(request).execute();
			String ipInfo = response.body().string();
			return ipInfo;
		}
		catch( Exception e) {
			log.info("发生异常：" + e.getMessage(), e );
			return "获取访问外网失败";
		}
	}
	
}
