package com.xyys.memorytext.net;

import com.xyys.memorytext.constant.ConstantValue;
import com.xyys.memorytext.constant.GlobalParams;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;


public class HttpClientUtil {
	private HttpClient client;

	private HttpPost post;
	private HttpGet get;

	public HttpClientUtil() {
		client = new DefaultHttpClient();
		// 判断是否需要设置代理信息
		if (StringUtils.isNotBlank(GlobalParams.PROXY)) {
			// 设置代理信息
			HttpHost host = new HttpHost(GlobalParams.PROXY, GlobalParams.PORT);
			client.getParams()
					.setParameter(ConnRoutePNames.DEFAULT_PROXY, host);
		}
	}

	/**
	 * 向指定的链接发送xml文件
	 * 
	 * @param uri
	 * @param xml
	 */
	public InputStream sendXml(String uri, String xml) {
		//创建一个post请求对象，用来想服务器发送数据（xml 或 其他自定义）
		post = new HttpPost(uri);

		try {
			//1、封装要发送的xml字符串为实体对象，并指定编码
			StringEntity entity = new StringEntity(xml, ConstantValue.ENCONDING);
			//2、设置post请求的xml实体对象
			post.setEntity(entity);
			//3、（客户端client）（执行execute）（一个post请求），返回一个（http响应response）
			HttpResponse response = client.execute(post);

			//4、判断获得响应码是否为200成功标识
			if (response.getStatusLine().getStatusCode() == 200) {
				return response.getEntity().getContent(); //响应成功的话，获得响应内容
			}

		} catch (Exception e) {
			//其他响应码则走这里 异常
			e.printStackTrace();
		}

		return null;

	}

}
