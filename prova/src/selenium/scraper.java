package selenium;

import org.openqa.selenium.chrome.ChromeDriver;

public class scraper {
	
	private ChromeDriver driver;
	
	public scraper() {
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	public void getPage(String nome) {
		String sito = "https://it.wikipedia.org/wiki/" + nome;
		driver.get(sito);
	}
	

}
