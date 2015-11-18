package com.test.future;

import java.util.concurrent.Future;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

//Async Http Lib 중  future 방식 
public class AsyncFutureCaller {
	public static void main(String[] args) throws Exception{
		System.out.println(Thread.currentThread().getName());
		//내부적으로 Netty를 사용함
		AsyncHttpClient asyncCaller = new AsyncHttpClient();
		
		AsyncHttpClient.BoundRequestBuilder requestBuilder = asyncCaller.prepareGet("http://www.daum.net");
		
		long callStart = System.currentTimeMillis();
		//요청을 보내고 요청보냈다는 확인용 티켓(Future)발급
		Future<Response> future = requestBuilder.execute();
		System.out.println("Call Time : "+(System.currentTimeMillis()-callStart));
		
		doSomething();
		
		long beforeRes = System.currentTimeMillis();
		//요청을 보내고 반환값 꺼내봄
		Response response = future.get();
		System.out.println("Res Time : "+(System.currentTimeMillis()-beforeRes));
		System.out.println(response.getContentType());
		
	}
	private static void doSomething(){
		System.out.println("요청보내고 내가 하고 싶은 일함");
	}
}
