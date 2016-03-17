package com.weebinar.gcm;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;


public class PushAgent {

	private static final String API_KEY ="515386264625";
	private static final Sender sender = new Sender(API_KEY); // 서버 API Key 입력


	public Result send(String pushId, String deviceId,String prodName, boolean isAtiqutteTime){
		if ( pushId==null || pushId.trim().length() == 0 ) return null;
		Result result=null;
		String message = makeMessage(prodName);
		Message gcmMsg = new Message.Builder()
		.addData("message",  message)
		//		.addData("title","중고몬")
		//		.addData("notId",((int)(System.currentTimeMillis()/100000))+"" ) // notibar에 여러개 띄울 때 필요.
		//		.addData("atiqutte", isAtiqutteTime+"")
		.build();

		try {
			result = sender.sendNoRetry(gcmMsg, pushId);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}


	private String makeMessage(String prodName) {
		String message = null;
		message = "["+prodName+"] 제품이 등록되었습니다.";
		return message;
	}

	public static void main(String args[]) throws Exception{
		//		System.out.println((int)(System.currentTimeMillis()/100000));
		String pushId="APA91bH7WzX5JMetQKWApIyIHCq-Ug-QTQW5S3sTEyFJT0wBs4ouwniAcUqG5aGEWR-1okwj277dAXVu-pmnC78-E2hvckGWGZ7xZl-nqLyhJ_nFtodY0L0rR1YmMFNhwI0OkTC_vxPp";
		new PushAgent().send(pushId, "aaa", "아이폰",false);
	}

}
