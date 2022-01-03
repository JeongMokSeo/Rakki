package com.rakki.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
@WebAppConfiguration
public class BuyControllerTests {
	
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
	
	//구매하기 사이즈화면 GET		
	@WithMockUser
	@Test
	public void testSelect() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/buy/select")
		.param("product_no","320"))
		.andReturn()
		.getModelAndView();

		log.info(mav.getViewName());			
	}
	
	//구매하기 체크화면 GET		
	@WithMockUser
	@Test
	public void testCheck() throws Exception {
		ModelAndView mav =  mvc.perform(MockMvcRequestBuilders.get("/buy/check")
		.param("product_no","320")
		.param("size_type", "260")
		)
		.andReturn()
		.getModelAndView();
		
		log.info(mav.getViewName());
	}	
	
	//구매하기 확인화면 GET	
	@WithMockUser
	@Test
	public void testConfirm() throws Exception {
		ModelAndView mav =  mvc.perform(MockMvcRequestBuilders.get("/buy/confirm")
				.param("product_no","320")
				.param("size_type", "260")
				)
				.andReturn()
				.getModelAndView();
		
		log.info(mav.getViewName());
	}
	
	//구매하기 확인화면 POST
	@WithMockUser
	@Test
	public void testConfirmPost() throws Exception {
		ModelAndView mav =  mvc.perform(MockMvcRequestBuilders.post("/buy/confirm")
				.param("product_no","358")    // 상품코드 358
				.param("size_type", "290")    // 사이즈 280
				.param("buy_flg", "1")        // 1:구매입찰,  2:즉시구매
				.param("id", "member7")       // 1:구매자id
				.param("immBuy", "120000")    // 즉시구매가
				.param("buy_price", "100000") // 구매입찰가
				)
				.andReturn()
				.getModelAndView();
		
		log.info(mav.getViewName());
	}		
	
}
