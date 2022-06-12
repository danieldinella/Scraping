
public class ScrapingTester4 {

	public static void main(String[] args) {
		String nome = "Settimio Severo";
		String link = "https://it.wikipedia.org/wiki/Settimio_Severo";
		Imperatore imp = new Imperatore(nome, link, true);
    	imp.setDinastia();
    	imp.setPadre();
    	imp.setMadre();
    	imp.setConiuge();
    	imp.setFigli();
	}

}
