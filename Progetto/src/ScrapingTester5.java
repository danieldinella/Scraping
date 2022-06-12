
public class ScrapingTester5 {

	public static void main(String[] args) {
		String nome = "Valeriano";
		String link = "https://it.wikipedia.org/wiki/Valeriano";
		Imperatore imp = new Imperatore(nome, link, true);
    	imp.setDinastia();
    	imp.setPadre();
    	imp.setMadre();
    	imp.setConiuge();
    	imp.setFigli();
	}

}
