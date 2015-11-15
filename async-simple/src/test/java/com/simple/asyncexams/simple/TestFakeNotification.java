package com.simple.asyncexams.simple;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestRootContextConfig.class,
										TestServletContextConfig.class,
										RootContextConfig.class,
										ServletContextConfig.class}	)
public class TestFakeNotification {
	@Inject
	private EntryPointController entryPoint;
	@Inject
	private NotificationService notiService;
	@Inject
	private WebApplicationContext context;
	@Inject
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception{
		System.out.println(1);
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();//standaloneSetup(new EntryPointController()).build();
		System.out.println(2);
//		webAppContextSetup(this.applicationContext).build();
	}
	@Test
	public void test2() throws Exception {
		mockMvc.perform(get("/fakeNoti")).andExpect(status().isOk());
	}
//	@Test
//	public void test(){
//		System.out.println("dd");
//		assertThat(notiService, is(notNullValue()));
//		notiService.sendNotification("subject", "message", null);
//	}
}
