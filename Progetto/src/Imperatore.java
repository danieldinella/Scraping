import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Imperatore extends Person{
	
	private boolean checkImperatore;
	private String mandato = "";

	/**
	 * Creates an object Imperatore with a given name, link and e
	 * @param nome
	 * @param link
	 * @param imperatore
	 */
	public Imperatore(String nome, String link, boolean imperatore) {
		super(nome, link);
		this.checkImperatore = true;
	}
	

	public boolean getCheckImperatore() {
		return this.checkImperatore;
	}
	
	public String getMandato() {
		return this.mandato;
	}

	public void setCheckImperatore() {
        // CHECK IF THE PERSON IS AN EMPEROR
        for (WebElement t : super.getWikiTab()) {
            if (t.getText().equals("Regno") | t.getText().equals("In carica")) {
                // IF THE PERSON IS AN EMPEROR CHANGE HIS STATUS
                this.checkImperatore = true;
            }
        }
    }
	
	// SET METHOD
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
