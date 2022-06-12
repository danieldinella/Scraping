
public class ScrapingTester1 {

	public static void main(String[] args) {
		String nome = "Augusto";
		String link = "https://it.wikipedia.org/wiki/Augusto";
		Imperatore imp = new Imperatore(nome, link, true);
    	imp.setDinastia();
    	imp.setPadre();
    	imp.setMadre();
    	imp.setConiuge();
    	imp.setFigli();
	}

}
