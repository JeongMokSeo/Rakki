package com.rakki.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.rakki.domain.MemberVo;
import com.rakki.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
@WebAppConfiguration
public class CommonControllerTests {
	@Autowired(required=true) @Setter @Qualifier("BCryptPasswordEncoder")
	private PasswordEncoder encoder;
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired @Setter
	MemberService service;
	@Autowired @Setter
	CommonController controller;
	
	@Autowired @Setter
	private WebApplicationContext ctx;	
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testExists() {
		assertNotNull(ctx);
		assertNotNull(mvc);
		log.info(ctx);
		log.info(mvc);
	}
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(this.context).apply(springSecurity()).build();
		
	}
	/* ?????????
	 * ????????? ????????? ?????????
	 * 
	 * */
	@WithMockUser
	@Test
	public void testLogin() throws Exception {
//		log.info( mvc.perform(formLogin().user("user1").password("1234")).andExpect(authenticated()));
		mvc.perform(post("/login")
				.param("username", "user1")
				.param("password", "1234")
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"))
		.andExpect(authenticated());
			
		
	}
	
	
	/* ?????????
	 * ??????????????? ????????? ?????? ?????????
	 * 
	 * */
	@WithMockUser
	@Test
	public void testMypage() throws Exception {
		mvc.perform(get("/common/mypage")).andExpect(status().isOk());
	}
	
	/* ?????????
	 * ???????????? ????????? ?????? ?????????
	 * 
	 * */
	@Test
	public void testTos() throws Exception {
		mvc.perform(get("/common/tos")).andExpect(status().isOk());
	}
	/* ?????????
	 * ???????????? ????????? ?????? ?????????
	 * 
	 * */
	@Test
	public void testWish() throws Exception {
		mvc.perform(get("/common/wish")).andExpect(status().is3xxRedirection());
	}
	
	/* ?????????
	 * ???????????? ????????? ?????????
	 * 
	 * */
	@Test
	public void testLogout() throws Exception {
		mvc.perform(get("/common/logout").with(csrf())).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/rakki/main")).andExpect(unauthenticated());
	}
	
	
	@WithMockUser
	@Test
	public void testJoin() throws Exception{
		mvc.perform(post("/common/join")
				.param("id", "testjoin")
				.param("password", "1234")
				.param("email", "join@naver.com")
				.param("name", "?????????")
				.param("phone", "123123123")
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/rakki/main"))
		.andExpect(authenticated());
	}
	
	/* ?????????
	 * ???????????? ???????????? ?????????
	 * 
	 * */
	@WithMockUser
	@Test
	public void testBuying() throws Exception {
		ModelMap map = mvc.perform(MockMvcRequestBuilders.get("/common/buying")
		.param("id", "member7")
		)
		.andReturn()
		.getModelAndView()
		.getModelMap();
		
		List<?> list=(List<?>) map.get("list");
		list.forEach(log::info);
	}	
	/* ?????????
	 * ???????????? ???????????? ?????????
	 * 
	 * */
	@WithMockUser
	@Test
	public void testSelling() throws Exception {
		ModelMap map = mvc.perform(MockMvcRequestBuilders.get("/common/selling")
				.param("id", "member7")
				)
				.andReturn()
				.getModelAndView()
				.getModelMap();
		
		List<?> list=(List<?>) map.get("list");
		list.forEach(log::info);
	}	
	/* ?????????
	 * ???????????? ???????????? ?????????
	 * 
	 * */
	@WithMockUser
	@Test
	public void testModify() throws Exception {
		ModelAndView view = mvc.perform(MockMvcRequestBuilders.get("common/userModify")
				.param("id", "user1"))
				.andReturn()
				.getModelAndView();
		
		log.info(view.getViewName());
				
	}
	/* ?????????
	 * ???????????? ???????????? ?????????
	 * 
	 * */
	@WithMockUser
	@Test
	public void testDelete() throws Exception {
		ModelAndView view = mvc.perform(MockMvcRequestBuilders.get("common/deleteUser")
				.param("id", "user1"))
				.andReturn()
				.getModelAndView();
		
		log.info(view);
		
	}
	/*
	 * @?????????
	 * 2021-11-11
	 * ????????? ????????? ?????? ?????????
	 */
	@WithMockUser
	@Test
	public void testAddress() throws Exception {
		mvc.perform(get("/common/address")).andExpect(status().isOk());
	}
	
	/*
	 * @?????????
	 * 2021-11-11
	 * ????????? ?????? ????????? ?????? ?????????
	 */
	@WithMockUser
	@Test
	public void testAddressMod() throws Exception {
		mvc.perform(get("/common/addressMod")).andExpect(status().isOk());
	}
	
	/*
	 * @?????????
	 * 2021-11-11
	 * ????????? ?????? ????????? ?????? ?????????
	 */
	@WithMockUser
	@Test
	public void testAddressReg() throws Exception {
		mvc.perform(get("/common/addressReg")).andExpect(status().isOk());
	}
	
	/*
	 * @?????????
	 * 2021-11-11
	 * ???????????? ????????? ?????? ?????????
	 */
	@WithMockUser
	@Test
	public void testPayment() throws Exception {
		mvc.perform(get("/common/payment")).andExpect(status().isOk());
	}
	/*
	 * @?????????
	 * 2021-11-11
	 * ???????????? ?????? ????????? ?????? ?????????
	 */
	@WithMockUser
	@Test
	public void testPaymentMod() throws Exception {
		mvc.perform(get("/common/paymentMod")
				.param("payment_no", "23"))
		.andExpect(status().isOk());
	}
	/*
	 * @?????????
	 * 2021-11-11
	 * ???????????? ?????? ????????? ?????? ?????????
	 */
	@WithMockUser
	@Test
	public void testPaymentReg() throws Exception {
		mvc.perform(get("/common/paymentReg")).andExpect(status().isOk());
	}
	/*
	 * @?????????
	 * 2021-11-11
	 * ???????????? ?????? ????????? ?????? ?????????
	 */
	@WithMockUser
	@Test
	public void testPaymentRmv() throws Exception {
		mvc.perform(get("/common/paymentRmv")
				.param("payment_no", "23"))
		.andExpect(status().isOk());
	}
	
	
}
