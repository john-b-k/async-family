package com.test.withoutfuture;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public class AsyncCallUsingHandler {
	
	AsyncHttpClient.BoundRequestBuilder requestBuilder = null;
	
	public AsyncCallUsingHandler(){
		//Boss Worker Thread Pool 한번만 생성되게 그리고 계속그것 이용
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		requestBuilder = asyncHttpClient.prepareGet("http://www.ning.com"); 
	}
	
	public void callByHandler(){
		long start = System.currentTimeMillis();
		
		//Worker Pool 를 통해 요청
		requestBuilder.execute(new AsyncCompletionHandler<Response>(){
			@Override
			public Response onCompleted(Response response){
				System.out.println("Triggering on Complete method");
				System.out.println(response.getContentType());
				return response;
			}
			@Override
			public void onThrowable(Throwable t){
				System.out.println("Triggering on Throwable method");
			}
		});
		System.out.println(System.currentTimeMillis()-start);
	}
	
	public static void main(String[] args){
		AsyncCallUsingHandler client = new AsyncCallUsingHandler();
		for(int i=0 ;i<5 ; i++){
			client.callByHandler();
		}
	}
	/**
	 * 결과
229
1
0
1
0
Triggering on Complete method
text/html;charset=utf-8
Triggering on Complete method
text/html;charset=utf-8
Triggering on Complete method
text/html;charset=utf-8
Triggering on Complete method
text/html;charset=utf-8
Triggering on Complete method
text/html;charset=utf-8
	 */
}
