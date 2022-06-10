
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

import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class Person {
	
	private ChromeOptions options = new ChromeOptions();
	private WebDriver driver;
	private String nome;
	private String link;
	private String dinastia = "";
	private String padre = "";
	private String madre = "";
	private ArrayList figli = new ArrayList();
	private ArrayList coniuge = new ArrayList();
	private List<WebElement> tab;
	private boolean checkImperatore;

	// CONSTRUCTOR
	public Person(String nome, String link) {
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
	
	public Person(String nome, String link, boolean imperatore) {
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
				if (!r.getText().equals("Adottivi") && !r.getText().equals("Adottivi:")) {
					String nome = r.getText();
					String link = r.getAttribute("href");
					Person per = new Person(nome, link);
					per.setCheckImperatore();
					if(per.checkImp()) {
						per.closeDriver(per.getDriver());
						Imperatore imp = new Imperatore(nome, link, true);
						imp.setDinastia();
						imp.setPadre();
						System.out.println("NOME IMPERATORE: " + imp.getNome());
						imp.setMadre();
						imp.setConiuge();
						imp.setCheckImperatore();
						imp.setMandato();
						
						if (imp.getDinastia() != "") {
							imp.setFigli();
						}
						
						// ADD SON OBJECT TO SONS' ARRAYLIST OF THE DAD
						this.figli.add(imp);
						
						imp.closeDriver(imp.getDriver());
						
						// STAMPE PER DEBUG
						
						System.out.println("Nome: " + imp.getNome());
						System.out.println("Dinastia: " + imp.getDinastia());
						System.out.println("Madre: " + imp.getMadre());
						System.out.println("Padre: " + imp.getPadre());
						System.out.println("Coniugi: ");
						imp.printConiuge(imp.getConiuge());
						System.out.println("Figli: ");
						imp.printFigli(imp.getFigli());
						System.out.println("\n");
						System.out.println("Mandato: " + imp.getMandato());
						System.out.println("\n");
					}
					else {
						per.setDinastia();
						per.setPadre();
						per.setMadre();
						per.setConiuge();
						
						if (this.dinastia != "") {
							per.setFigli();
						}
						
						// ADD SON OBJECT TO SONS' ARRAYLIST OF THE DAD
						this.figli.add(per);
						
						per.closeDriver(per.getDriver());
						
						// STAMPE PER DEBUG
						

						System.out.println("Nome: " + per.getNome());
						System.out.println("Dinastia: " + per.getDinastia());
						System.out.println("Madre: " + per.getMadre());
						System.out.println("Padre: " + per.getPadre());
						System.out.println("Coniugi: ");
						printConiuge(per.getConiuge());
						System.out.println("Figli: ");
						printFigli(per.getFigli());
						System.out.println("\n");
						
					}
				}
			}
			
		}
		
		
		
	}
	
	public void closeDriver(WebDriver driver) {
		// CLOSE THE WEB SITE
		driver.quit();
	}
	
	public List<WebElement> getTab(){
		return this.tab;
	}
	
	public WebDriver getDriver() {
		return this.driver;
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
					+ "/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+i+"]/td")).getText();
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
	
	public void setCheckImperatore() {
		// CHECK IF THE PERSON IS AN EMPEROR
		for (WebElement t : this.tab) {
			if (t.getText().equals("Regno")) {
				// IF THE PERSON IS AN EMPEROR CHANGE HIS STATUS
				this.checkImperatore = true;
			}
		}
	}
	
	public boolean checkImp() {
		return this.checkImperatore;
	}
	
	// STAMPE PER DEBUG
	public void printConiuge(ArrayList l) {
		for (int x = 0; x < l.size(); x++) {
			System.out.println(l.get(x));
		}
	}
	public void printFigli(ArrayList l) {
		for (int x = 0; x < l.size(); x++) {
			Person fig  = (Person) l.get(x);
			System.out.println(fig.getNome());
		}
	}
}