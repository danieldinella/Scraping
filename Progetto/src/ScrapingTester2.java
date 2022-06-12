
public class ScrapingTester2 {

	public static void main(String[] args) {
		String nome = "Vespasiano";
		String link = "https://it.wikipedia.org/wiki/Vespasiano";
		Imperatore imp = new Imperatore(nome, link, true);
    	imp.setDinastia();
    	imp.setPadre();
    	imp.setMadre();
    	imp.setConiuge();
    	imp.setFigli();
	}

}
