package com.simple.asyncexams.simple;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryPointController {
	
	@Autowired
	private NotificationService notiService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String fakeCall(){
		return "this request is normal";
	}
	
	@RequestMapping(value = "/fakeNoti", method = RequestMethod.GET)
	public String fakeNotification(){
		notiService.sendNotification("dummy", "dummy", null);
		return "fake Notifincation called.";
	}
	
	@Test
	public void 서버올리지않고_직접Async메서드호출_Sync로호출됨(){
		System.out.println("호출");
		new FakeNotificationService().sendNotification("subject", "message", null);
		System.out.println("동기 방식으로 호출");
	}
}
