package com.jt;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	/**
	 *实现步骤
	 * 1.创建httpclient对象
	 * 2.指定url的请求地址
	 * 3.指定请求方式    	  a.查询操作一般都是get请求
	 * 				      b.如果涉及数据入库更新数据量较大是用post
	 * 				      c.涉及密码操作也用post
	 * 4.发起请求获取respanse对象，
	 * 5.判断请求是否正确，检查状态码200
	 * 6.从返回值数据获取JSON串
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	//实现GET请求
	@Test
	public void testGet() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = 
				HttpClients.createDefault();
		
		String url = "https://item.jd.com/100000287115.html";
		
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse httpResponse = 
					httpClient.execute(httpPost);
		
		
		if(httpResponse.getStatusLine().getStatusCode()
				== 200) {
			System.out.println("请求正确返回!!!!!");
			String result = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(result);
		}
		
	}
		
		
}
