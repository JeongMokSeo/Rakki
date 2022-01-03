package ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SeleniumTests {
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private final String WEB_DRIVER_PATH = "C:\\tools\\chromedriver_win32\\chromedriver.exe";
	private String url = "http://spring.sjmworld.net/product/shop?amount=12&type=C&keyword=1";
	private WebDriver driver;
	
	public SeleniumTests(){
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver = new ChromeDriver();		
	}		
	
	@Test
	public void crawl() throws InterruptedException, ParseException, MalformedURLException, IOException {
		driver.get(url);
		log.info(driver);		
//		log.info(driver.getPageSource());
		
		List<WebElement> list = driver.findElements(By.cssSelector(".col-sm-6"));
		log.info(list.size());
		
		for(int i = 0; i < list.size(); i++){
			WebElement product = driver.findElements(By.cssSelector(".col-sm-6")).get(i);
			product.findElement(By.cssSelector("a")).click();
			
			//페이지 로딩 대기
			Thread.sleep(1000);
			
			 String productName = driver.findElement(By.cssSelector(".translated_name")).getText();
			 //제품명 한글확인
			 log.info("productName:"+productName);	
			 driver.navigate().back();
			 
			//페이지 로딩 대기
			Thread.sleep(1000);

		}
		
	}
	
	static By select(String selector) {
		return By.cssSelector(selector);
	}
}
