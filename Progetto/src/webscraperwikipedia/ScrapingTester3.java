package webscraperwikipedia;
import java.util.ArrayList;

public class ScrapingTester3 {

	public static void main(String[] args) {
		String nome = "Adriano";
        String link = "https://it.wikipedia.org/wiki/Adriano";
        Imperatore imp = new Imperatore(nome, link);
        imp.setDinastia();
        imp.setPadre();
        imp.setMadre();
        imp.setConiuge();
        imp.setCheckImp();
        imp.setMandato();
        imp.setFigli();
        System.out.println("Nome: " + imp.getNome());
        System.out.println("Mandato: " + imp.getMandato());
        System.out.println("Nome padre: " + imp.getPadre());
        System.out.println("Nome madre: " + imp.getMadre());
        System.out.println("Nome dinastia: " + imp.getDinastia());

        System.out.println("coniugi: ");
        ArrayList<String> coniugi = imp.getConiugi();

        for (String x : coniugi) {
            System.out.println(x);
        }

        System.out.println("Figli: ");
        ArrayList<Person> figli = imp.getFigli();
        for(Person x : figli) {
            System.out.println(x.getNome());
        }
	}

}
