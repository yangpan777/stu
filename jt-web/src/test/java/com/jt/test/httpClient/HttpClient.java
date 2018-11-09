package com.jt.test.httpClient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClient {
	//get方法
	@Test
	public void doGet() throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();

		String url = "http://www.tmooc.cn";
		//HttpGet get = new HttpGet(url);
		HttpPost post = new HttpPost(url);

		String method = 
				post.getRequestLine().getMethod();
		System.out.println("获取请求类型:"+method);
		String http = 
				post.getRequestLine().getProtocolVersion().toString();
		System.out.println("获取请求协议:"+http);
		
		//发起http请求
		CloseableHttpResponse response 
				= client.execute(post);
		
		//判断状态信息是否正确
		if(response.getStatusLine().getStatusCode() == 200){
			String result = 
					EntityUtils.toString(response.getEntity());
			//System.out.println(result);
		}
	}
}
