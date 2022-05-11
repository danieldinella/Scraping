import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.graph.ElementOrder.Type;

public class person {
	
	
	private String nome;
	private ArrayList figli;
	private WebDriver driver;
	private String link;
	private String dinastia;

	public person(String nome, String link) {
		//URL settings
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		
		this.nome = nome;
		
		//Opening wikipedia
		this.driver = new ChromeDriver();
		this.link = link;
		this.figli = new ArrayList();
		driver.get(link);
	}
	
	public ArrayList getFigli() {
		return this.figli;
	}
	
	public void setDinastia() {
			List<WebElement> web = this.driver.findElements(By.partialLinkText("Dinastia"));
			if (web.size() != 0) {
				this.dinastia = web.get(0).getText();
			}
	}
	
	public String getDinastia() {
		return this.dinastia;
	}
	
	
	
	public ArrayList setFigli() {
		List<WebElement> imp = driver.findElements(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr/th"));
		int indice = -1;
		int indice2 = 1;
		for (WebElement tab : imp) {
			if (tab.getText().equals("Figli")) {
				indice = indice2 + 1;
			}
			else {
				indice2++;
			}
		}
		if(indice != -1) {
			List<WebElement> imp2 = driver.findElements(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+indice+"]/td/a"));
			for (WebElement tab : imp2) {
				String nome = tab.getText();
				String link = tab.getAttribute("href");
				person per = new person(nome, link);
				per.setDinastia();
				if (this.dinastia != "") {
					per.setFigli();
				}
				
				if (this.nome == "Giulia maggiore") System.out.println(this.nome);
				this.figli.add(per);
			}
			this.driver.quit();
			return this.figli;
		}
		else {
			this.driver.quit();
			return new ArrayList();
		}
	}

	public String getNome() {
		return nome;
	}
	
	public void printFigli(ArrayList l) {
		for (int x = 0; x < l.size(); x++) {
			person p = (person) l.get(x);
			System.out.println(p.getNome());
		}
	}
}
