
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
	private ArrayList coniugi = new ArrayList();
	private List<WebElement> wikiTab;

	/**
	 * Create an object Person with a given name and link to his website.
	 *  
	 * @param nome		Person's name
	 * @param link		Web site link
	 */
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
		this.wikiTab = driver.findElements(By.xpath("//div[@id = 'mw-content-text']"
				+ "/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr/th"));
	}

	/**
	 * Gets the sons of this person
	 * @return	sons
	 */
	public ArrayList getFigli() {
		return this.figli;
	}
	
	/**
	 * Gets the dinasty of this person
	 * @return	dinasty
	 */
	public String getDinastia() {
		return this.dinastia;
	}
	
	/**
	 * Gets the name of this person
	 * @return	name
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Gets the father of this person
	 * @return	father
	 */
	public String getPadre() {
		return this.padre;
	}
	
	/**
	 * Gets the mother of this person
	 * @return	mother
	 */
	public String getMadre() {
		return this.madre;
	}
	
	/**
	 * Gets the consorts of this person
	 * @return	consorts
	 */
	public ArrayList getConiugi() {
		return this.coniugi;
	}

	/**
	 * Gets the wikitable of the website
	 * @return	wikitable
	 */
	public List<WebElement> getWikiTab(){
		return this.wikiTab;
	}
	
	/**
	 * Gets the website of this person
	 * @return	website
	 */
	public WebDriver getDriver() {
		return this.driver;
	}
	
	/**
	 * Sets the dinasty of this person
	 */
	public void setDinastia() {
		
		// GET DINASTY'S NAME
		List<WebElement> dina = driver.findElements(By.partialLinkText("Dinastia"));
		
		// IF DINASTY'S EXISTS THEN SET IT
		if (dina.size() != 0) {
			this.dinastia = dina.get(0).getText();
		}
	}
	
	/**
	 * Sets the sons of this person
	 */
	public void setFigli() {
		
		// GET THE NUMBER OF SONS' TABLE LINE
		int i = -1;
		int j = 1;
		for (WebElement t : this.wikiTab) {
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
					
					if(per.getCheckImp()) {
						per.closeDriver(per.getDriver());
						Imperatore imp = new Imperatore(nome, link, true);
						imp.setDinastia();
						imp.setPadre();
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
						
					}
				}
			}
			
		}
		
		
		
	}

	/**
	 * Sets the father of this person
	 */
	public void setPadre() {

		// GET NUMBER OF DAD'S TABLE LINE
		int i = -1;
		int j = 1;
		for (WebElement t : this.wikiTab) {
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

	/**
	 * Sets the mother of this person
	 */
	public void setMadre() {
		
		// GET NUMBER OF MOM'S TABLE LINE
		int i = -1;
		int j = 1;
		for (WebElement t : this.wikiTab) {
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

	/**
	 * Sets the consorts of this person
	 */
	public void setConiuge() {
		
		// GET NUMBER OF CONSORTS' TABLE LINE
		int i = -1;
		int j = 1;
		for (WebElement t : this.wikiTab) {
			if (t.getText().equals("Coniuge") | t.getText().equals("Coniugi") | t.getText().equals("Consorte") | t.getText().equals("Consorti")) {
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
					this.coniugi.add(r.getText());
				}
			}
		}
	}

	/**
	 * Checks if the person is an emperor
	 */
	public boolean getCheckImp() {
        // CHECK IF THE PERSON IS AN EMPEROR
        for (WebElement t : this.wikiTab) {
            if (t.getText().equals("Regno") | t.getText().equals("In carica")) {
                // IF THE PERSON IS AN EMPEROR CHANGE HIS STATUS
                return true;
            }
        }
        return false;
    }
	
	/**
	 * Close the Web Driver
	 * @param driver	Chrome Web Driver
	 */
	public void closeDriver(WebDriver driver) {
		driver.quit();
	}
}