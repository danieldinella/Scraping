import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.graph.ElementOrder.Type;

public class person {
	
	private ChromeOptions options = new ChromeOptions();
	private WebDriver driver;
	private String nome;
	private String link;
	private String dinastia = "";
	private boolean imperatore = false;
	private String padre = "";
	private String madre = "";
	private ArrayList figli = new ArrayList();
	private ArrayList coniuge = new ArrayList();
	private List<WebElement> tab;

	// CONSTRUCTOR
	public person(String nome, String link) {
		//URL SETTINGS
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		this.driver = new ChromeDriver(options);
		
		//ATTRIBUTES SETTINGS
		this.nome = nome;
		this.link = link;
		this.driver.get(link);
		this.tab = driver.findElements(By.xpath("//div[@id = 'mw-content-text']"
				+ "/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr/th"));
	}
	
	
	// GET METHODS
	
	public ArrayList getFigli() {
		return this.figli;
	}
	
	public String getDinastia() {
		return this.dinastia;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getPadre() {
		return this.padre;
	}
	
	public String getMadre() {
		return this.madre;
	}
	
	public ArrayList getConiuge() {
		return this.coniuge;
	}
	
	
	//SET METHODS
	
	public void setDinastia() {
		
		// GET DINASTY'S NAME
		List<WebElement> dina = driver.findElements(By.partialLinkText("Dinastia"));
		
		// IF DINASTY'S EXISTS THEN SET IT
		if (dina.size() != 0) {
			this.dinastia = dina.get(0).getText();
		}
	}
	
	public void setFigli() {
		
		// GET NUMBER OF SONS' TABLE LINE
		int i = -1;
		int j = 1;
		for (WebElement t : this.tab) {
			if (t.getText().equals("Figli")) {
				i = j + 1;
			}
			else {
				j++;
			}
		}
		
		// IF SONS' LINE EXISTS
		if(i != -1) {
			
			// GET SONS' ATTRIBUTES
			List<WebElement> riga = driver.findElements(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']"
					+ "/table[@class = 'sinottico']/tbody/tr["+i+"]/td/a"));
			
			// SET NEW PERSON OBJECT WITH SONS' ATTRIBUTES
			for (WebElement r : riga) {
				String nome = r.getText();
				String link = r.getAttribute("href");
				person per = new person(nome, link);
				per.setDinastia();
				per.setImperatore();
				per.setPadre();
				per.setMadre();
				per.setConiuge();
				if (this.dinastia != "") {
					per.setFigli();
				}
				
				// ADD SON OBJECT TO SONS' ARRAYLIST OF THE DAD
				this.figli.add(per);
			}
			
		}
		
		System.out.println("Nome: " + this.nome);
		System.out.println("Dinastia: " + this.dinastia);
		if(this.imperatore) { System.out.println("E' un imperatore"); }
		System.out.println("Madre: " + this.madre);
		System.out.println("Padre: " + this.padre);
		System.out.println("Coniugi: ");
		printConiuge(getConiuge());
		System.out.println("Figli: ");
		printFigli(getFigli());
		System.out.println("\n");
		
		// CLOSE THE WEB SITE
		this.driver.quit();
	}

	public void setPadre() {

		// GET NUMBER OF DAD'S TABLE LINE
		int i = -1;
		int j = 1;
		for (WebElement t : this.tab) {
			if (t.getText().equals("Padre")) {
				i = j + 1;
			}
			else {
				j++;
			}
		}
		
		// IF DAD'S LINE EXISTS THEN SET IT
		if(i != -1) {
			this.padre = driver.findElement(By.xpath("//div[@id = 'mw-content-text']"
					+ "/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+i+"]/td/a")).getText();
		}
	}

	public void setMadre() {
		
		// GET NUMBER OF MOM'S TABLE LINE
		int i = -1;
		int j = 1;
		for (WebElement t : this.tab) {
			if (t.getText().equals("Madre")) {
				i = j + 1;
			}
			else {
				j++;
			}
		}
		
		// IF MOM'S LINE EXISTS THEN SET IT
		if(i != -1) {
			this.madre = driver.findElement(By.xpath("//div[@id = 'mw-content-text']"
					+ "/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+i+"]/td/a")).getText();
		}
	}

	public void setConiuge() {
		
		// GET NUMBER OF CONSORTS' TABLE LINE
		int i = -1;
		int j = 1;
		for (WebElement t : this.tab) {
			if (t.getText().equals("Coniuge") | t.getText().equals("Coniugi")) {
				i = j + 1;
			}
			else {
				j++;
			}
		}
		
		// IF CONSORTS' LINE EXISTS THEN SET IT
		if(i != -1) {
			List<WebElement> riga = driver.findElements(By.xpath("//div[@id = 'mw-content-text']"
					+ "/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+i+"]/td/a"));
			for (WebElement r : riga) {
				// CHECK TO WIKIPEDIA IMPERFECTIONS
				String con = r.getText();
				if (!Character.isDigit(con.charAt(0))){
					this.coniuge.add(r.getText());
				}
			}
		}
	}
	
	public void setImperatore() {
		// CHECK IF THE PERSON IS A IMPERATOR
				for (WebElement t : this.tab) {
					if (t.getText().equals("Regno")) {
						// IF THE PERSON IS A IMPERATOR CHANGE HIS STATUS
						this.imperatore = true;
					}
				}
	}

	public boolean checkImperatore() {
		return this.imperatore;
	}
	
	// STAMPE PER DEBUG
	public void printConiuge(ArrayList l) {
		for (int x = 0; x < l.size(); x++) {
			System.out.println(l.get(x));
		}
	}
	public void printFigli(ArrayList l) {
		for (int x = 0; x < l.size(); x++) {
			person fig  = (person) l.get(x);
			System.out.println(fig.getNome());
		}
	}
}
