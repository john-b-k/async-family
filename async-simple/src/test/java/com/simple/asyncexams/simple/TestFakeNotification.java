package com.simple.asyncexams.simple;

import static org.junit.Assert.assertTrue;
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
//Only one AsyncConfigurer may exist
@ContextConfiguration(classes = {/*TestRootContextConfig.class,
										TestServletContextConfig.class,*/
										RootContextConfig.class,
										ServletContextConfig.class}	)
@WebAppConfiguration
public class TestFakeNotification {
	@Inject
	private EntryPointController entryPoint;
	@Inject
	private NotificationService notiService;
	@Inject
	private WebApplicationContext context;
//	@Inject
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();//standaloneSetup(new EntryPointController()).build();
	}
	@Test
	public void _1비동기메서드간접호출_컨테이너없이동작_어싱크는미작동() throws Exception {
		mockMvc.perform(get("/fakeNoti")).andExpect(status().isOk());
	}
	@Test
	public void _2비동기메서드직접호출_어싱크는미작동(){
		System.out.println("비동기메서드직접호출_어싱크는미작동");
		assertTrue(notiService!= null);
		notiService.sendNotification("subject", "message", null);
	}
}
