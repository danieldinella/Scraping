import java.util.ArrayList;  
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

public class StringProcessor {
	/* String codifica attribute
	 * codifica is a string where each line has this format:
	 * 
	 *   Emperor	 Name	    Reign 	 	Spouses	  N° of Children
	 * 	  Y / N    Mandatory   Optional	  Optional		Optional
	 *      |		   |		  |			  |				|
	 * 	    |          |		  |			  |				|
	 *      |          |		  |			  |				|
	 *      V          V		  V			  V				V
	 *   "*"/""   "Full name"  " (n-n)"	  " + [List]"    " N_F:n"
	 *   
	 * Example:
	 *	"*Vespasiano (69-79) + [Flavia Domitilla, Caenis] N_F:3				/n
	 *  	 *Tito (79-81) + [Arrecina Tertulla, Marcia Furnilla] N_F:2 	/n
	 * 		 	 Giulia Flavia (79-81) + [Tito Flavio Sabino]				/n
	 * 			 Flavia														/n
	 * 		 *Domiiziano (81-96) + [Domizia Longina] N_F:2					/n
	 * 			 Flavio Clemente											/n
	 * 			 Flavia Domitilla											/n
	 * 		 Flavia Domitilla minore"
	 * Each line has got a blank space (" ") at the end except the last one										
	 */
	private static String codifica;
	
	/* separate method
	 * Splits a string in a list of strings using a given parameter as a separator
	 * 
	 * @param toSeparate		string to separate
	 * @param el				separation parameters
	 * @return 					the list of lines contained in a string
	 */
	public static ArrayList<String> separate(String toSeparate, String el) {
		//The string gets splitted into an Array using el as a parameter
		String[] elements = toSeparate.split(el); 
		//The new Array gets converted into a List
		List<String> fixedLenghtList = Arrays.asList(elements);
		//The new List gets converted into an ArrayList
		ArrayList<String> listOfString = new ArrayList<String>(fixedLenghtList); 
		//The new ArrayList gets returned
		return listOfString;
	}
	
	/* countMatches method
	 * Counts how many times a substring appears in a string
	 * 
	 * @param str				string to analyze	
	 * @param target			substring to search
	 * @return					how many times target appears in str
	 */
	public static int countMatches(String str, String target) {
		//The string gets splitted using target as parameter, n° of matches in new list size - 1
        return str.split(target, -1).length - 1;
    }
	
	/* processString method (Persona as root)
	 * Starting from an initial number of tabs of 0 and the Person object called root, 
	 * the method performs a pre-order visit using recursive calls using his children and
	 * the number of tabs plus 1.
	 * Each call assembles a line of the string codifica following the his syntax using
	 * controls for each Person object's attribute
	 * 
	 * @param root		Person object root
	 * @param tabs 		Number of tabs for each line start, initial value = 0
	 */
	public static void processString(Person root, String tabs) {
		//Blank space and start a new line (\n)
		codifica += " \n" + tabs;	
		//Control if the root hasn't got any children ...
		if(root.getFigli().isEmpty())
			//... if that's the case only its name and spouses needs to be added to the line
			//If the root hasn't got any spouses ...
			if(root.getConiuge().isEmpty()) 
				//... only its name gets added
				codifica += (root.getNome());
			else
				//... otherwise its name and spouses get added
				codifica +=(root.getNome() + " + " + root.getConiuge());
		//Otherwise...
		else {
			//... if that's the case only its name and spouses needs to be added to the line
			//If the root hasn't got any spouses ...
			if(root.getConiuge().isEmpty())
				//... only its name and years of reign get added
				codifica += (root.getNome());
			else
				//... otherwise its name, years of reign and spouses get added
				codifica += (root.getNome() + " + " + root.getConiuge());
			//Since root has got children they will be put in an ArrayList
			ArrayList figli = root.getFigli();
			//If figli ArrayList isn't empty ...
			if(!root.getFigli().isEmpty())
				//... the number of children will be added next
				codifica += (" N_F :"+figli.size());
			//Tabs number gets increased by 1
			tabs = tabs + "\t";
			//For each children in the ArrayList ...
			for(int i = 0; i < figli.size(); i++) {
				//... the current element will be put in a temporary Persona type variable
				Person fi = (Person) figli.get(i);
				//If the child is an emperor ...
				if(fi.checkImp()) {
					// ... it will be casted as an Imperatore type object, its mandato attribute will be conserved
					Imperatore imp = (Imperatore) figli.get(i);
					//Then the method using an Imperatore as root will be recursevily called
					processString(imp, tabs);
				}
				else {
					// ... it will be casted as a Persona type object
					Person per = (Person) figli.get(i);
					//Then the method using a Imperatore as root will be recursevily called
					processString(per, tabs);
				}
			}
		}
	}
	
	/* processString method (Imperatore as root)
	 * Starting from an initial number of tabs of 0 and the Person object called root, 
	 * the method performs a pre-order visit using recursive calls using his children and
	 * the number of tabs plus 1.
	 * Each call assembles a line of the string codifica following the his syntax using
	 * controls for each Person object's attribute
	 * It adds a "*" character to symbolize the current line concerns an emperor 
	 * 
	 * @param root		Imperatore object root
	 * @param tabs 		Number of tabs for each line start, initial value = 0
	 */
	public static void processString(Imperatore root, String tabs) {
		//Blank space and start a new line (\n)
		codifica += " \n" + tabs;
		//"*" character means there is an emperor in the current line
		codifica += " *";
		//Control if the root hasn't got any children ...
		if(root.getFigli().isEmpty())
			//... if that's the case only its name and spouses needs to be added to the line
			//If the root hasn't got any spouses ...
			if(root.getConiuge().isEmpty()) 
				//... only its name and years of reign get added
				codifica += (root.getNome() + " (" + root.getMandato() + ")");
			else
				//... otherwise its name, years of reign and spouses get added
				codifica +=(root.getNome() + " (" + root.getMandato() + ")" + " + " + root.getConiuge());
		//Otherwise...
		else {
			//... if that's the case only its name and spouses needs to be added to the line
			//If the root hasn't got any spouses ...
			if(root.getConiuge().isEmpty())
				//... only its name and years of reign get added
				codifica += (root.getNome() + " (" + root.getMandato() + ")");
			else
				//... otherwise its name, years of reign and spouses get added
				codifica += (root.getNome() + " (" + root.getMandato() + ")" + " + " + root.getConiuge());
			//Since root has got children they will be put in an ArrayList
			ArrayList figli = root.getFigli();
			//If figli ArrayList isn't empty ...
			if(!root.getFigli().isEmpty())
				//... the number of children will be added next
				codifica += (" N_F :"+figli.size());
			//Tabs number gets increased by 1
			tabs = tabs + "\t";
			//For each children in the ArrayList ...
			for(int i = 0; i < figli.size(); i++) {
				//... the current element will be put in a temporary Persona type variable
				Person fi = (Person) figli.get(i);
				//If the child is an emperor ...
				if(fi.checkImp()) {
					// ... it will be casted as an Imperatore type object, its mandato attribute will be conserved
					Imperatore imp = (Imperatore) figli.get(i);
					//Then the method using an Imperatore as root will be recursevily called
					processString(imp, tabs);
				}
				else {
					// ... it will be casted as a Persona type object
					Person per = (Person) figli.get(i);
					//Then the method using a Imperatore as root will be recursevily called
					processString(per, tabs);
				}
			}
		}
	}
	
	/* removeDuplicates method
	 * To prevent duplicate nodes and sub-trees, this function creates a new 
	 * ArrayList of lines without duplicate lines still maintaining the current
	 * order.
	 * Example ["A", "C", "B", "C", "C", "A", "B"]  ------> ["A", "C", "B"]
	 * Example ["A ", "C", "B", "C", "C", "A", "B"] ------> ["A ", "C", "B", "A"]
	 * To prevent the last case from happening the method performs further controls
	 * 
	 * @param lines		ArrayList containg lines with duplicates
	 * @return			new ArrayList without duplicates
	 */
	public static ArrayList<String> removeDuplicates(ArrayList<String> lines) {
		//Creates a new ArrayList
		ArrayList<String> new_lines = new ArrayList<String>();
		//For each element contained in the lines ArrayList
		for(String line: lines)
			//If the new ArrayList doesn't contain the current element ...
			if(!new_lines.contains(line))
				// ... it gets added to the new ArrayList
				new_lines.add(line);
		//Last line in the new ArrayList gets controlled (ex: "Giulia Lavilla")
		String last_line = new_lines.get(new_lines.size()-1);
		//If the new ArrayList contains the last line + a blank space (ex: "Giulia Lavilla ") ...
		if(new_lines.contains(last_line+" "))
			// ... the last line gets removed
			new_lines.remove(new_lines.size()-1);
		//The new ArrayList gets returned
		return new_lines;
	}
	
	/* getCodifica method
	 * When it comes to get the codifica attribute the first line contains
	 * "null\n ", this line must be removed otherwise a null Node will be
	 * created
	 * 
	 * @return			fixed codifica attribute
	 */
	public static String getCodifica() {
		codifica = codifica.replace("null\n", "");
		return codifica.substring(1);
	}
	
	/* resetCodifica method
	 * Every time a new Tree gets created, codifica attribute needs to be
	 * re-setted to prevent overlapping dynasty trees
	 */
	public static void resetCodifica() {
		codifica = "";
	}
}
