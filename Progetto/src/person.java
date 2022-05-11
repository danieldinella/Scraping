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
	private String padre;
	private String madre;
	private ArrayList coniuge;

	public person(String nome, String link) {
		//URL settings
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		
		this.nome = nome;
		this.figli = new ArrayList();
		this.dinastia = "";
		this.padre = "";
		this.madre = "";
		this.coniuge = new ArrayList();
		
		//Opening wikipedia
		this.driver = new ChromeDriver();
		this.link = link;
		this.figli = new ArrayList();
		this.driver.get(link);
	}
	
	public ArrayList getFigli() {
		return this.figli;
	}
	
	public void setDinastia() {
		List<WebElement> dina = driver.findElements(By.partialLinkText("Dinastia"));
		if (dina.size() != 0) {
			this.dinastia = dina.get(0).getText();
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
				per.setPadre();
				per.setMadre();
				per.setConiuge();
				if (this.dinastia != "") {
					per.setFigli();
				}
				
				if (this.nome == "Giulia maggiore") System.out.println(this.nome);
				this.figli.add(per);
			}
			this.driver.quit();
			System.out.println("Nome: " + this.nome);
			System.out.println("Dinastia: " + this.dinastia);
			System.out.println("Madre: " + this.madre);
			System.out.println("Padre: " + this.padre);
			System.out.println("Coniugi: ");
			printConiuge(getConiuge());
			System.out.println("Figli: ");
			printFigli(getFigli());
			return this.figli;
		}
		else {
			this.driver.quit();
			return this.figli;
		}
	}

	public String getNome() {
		return this.nome;
	}
	
	public void printFigli(ArrayList l) {
		for (int x = 0; x < l.size(); x++) {
			person p = (person) l.get(x);
			System.out.println(p.getNome());
		}
	}

	public String getPadre() {
		return this.padre;
	}

	public void setPadre() {
		List<WebElement> imp = driver.findElements(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr/th"));
		int indice = -1;
		int indice2 = 1;
		for (WebElement tab : imp) {
			if (tab.getText().equals("Padre")) {
				indice = indice2 + 1;
			}
			else {
				indice2++;
			}
		}
		if(indice != -1) {
			this.padre = driver.findElement(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+indice+"]/td/a")).getText();
		}
	}

	public String getMadre() {
		return this.madre;
	}

	public void setMadre() {
		List<WebElement> imp = driver.findElements(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr/th"));
		int indice = -1;
		int indice2 = 1;
		for (WebElement tab : imp) {
			if (tab.getText().equals("Madre")) {
				indice = indice2 + 1;
			}
			else {
				indice2++;
			}
		}
		if(indice != -1) {
			this.madre = driver.findElement(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+indice+"]/td/a")).getText();
		}
	}

	public ArrayList getConiuge() {
		return this.coniuge;
	}

	public void setConiuge() {
		List<WebElement> imp = driver.findElements(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr/th"));
		int indice = -1;
		int indice2 = 1;
		for (WebElement tab : imp) {
			if (tab.getText().equals("Coniuge") | tab.getText().equals("Coniugi")) {
				indice = indice2 + 1;
			}
			else {
				indice2++;
			}
		}
		if(indice != -1) {
			List<WebElement> imp2 = driver.findElements(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+indice+"]/td/a"));
			for (WebElement tab : imp2) {
				this.coniuge.add(tab.getText());
			}
		}
	}
	
	public void printConiuge(ArrayList l) {
		for (int x = 0; x < l.size(); x++) {
			System.out.println(l.get(x));
		}
	}
}
