import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Imperatore extends Person{

	private String mandato = "";

	/**
	 * Creates an object Imperatore with a given name and link
	 * @param nome		name of the emperor
	 * @param link		link of the website of the emperor
	 */
	public Imperatore(String nome, String link) {
		super(nome, link);
	}
	
	/**
	 * Gets the mandate of the emperor
	 * @return	mandate
	 */
	public String getMandato() {
		return this.mandato;
	}

	/**
	 * Sets the mandate of the emperor
	 */
	public void setMandato() {
		// GET NUMBER OF REIGN'S TABLE LINE
		int i = -1;
		int j = 1;
		for (WebElement t : super.getWikiTab()) {
			if (t.getText().equals("Regno")) {
				i = j + 1;
			}
			if(t.getText().equals("In carica")){
				i = j + 1;
			}
			else {
				j++;
			}
		}
		
		// IF REIGN'S LINE EXISTS THEN SET IT
		if(i != -1) {
			List<WebElement> anno = super.getDriver().findElements(By.xpath("//div[@id = 'mw-content-text']"
					+ "/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+i+"]/td/a"));
			
			ArrayList numeri = new ArrayList();
			
			for (WebElement x : anno) {
				String appo = x.getText();
				char c = appo.charAt(0);
				if(Character.isDigit(c)) {
					numeri.add(x.getText());
				}
			}
			if(!numeri.isEmpty()) {
				this.mandato = this.mandato.concat(numeri.get(0) + "-" + numeri.get(numeri.size() - 1));
			}
		}	
	}
	
}
