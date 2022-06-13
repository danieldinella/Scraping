import java.awt.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import javax.imageio.ImageIO;
import net.bytebuddy.asm.Advice.This;

/* TreeImage class
 * This class is the actor that will draw the final image after creating all the istances
 * of Node
 * 
 * @author		Luca Ciafardoni
 */
public class TreeImage {
	/* String codifica attribute
	 *  Needs to be setted, will be separeted line per line
	 * BufferedImage img attribute
	 *  The image that will be drawn and then saved
	 * Graphics2D g2d attribute
	 *  The actor object that will draw in img
	 * int tree_height and tree_width attributes
	 *  How deep and wide the tree is
	 * int img_height and img_width attributes
	 *  How wide and tall the image is
	 * ArrayList<Integer> members_per_height attribute
	 *  Each index represents the y_tile, the value represents how many nodes there are
	 * ArrayList<ArrayList<Integer>> tree_tiles_map attribute
	 *  An ArrayList of X and Y tiles pairs, maintains the lines ArrayList order
	 * ArrayList<Node> nodes_list attribute
	 *  An ArrayList of Node class objects
	 */
	private static String codifica = "";
	private static BufferedImage img;
	private static Graphics2D g2d;
	private static int tree_height;
	private static int tree_width;
	private static int img_height;
	private static int img_width;
	private static ArrayList<Integer> members_per_height;
	private static ArrayList<ArrayList<Integer>> tree_tiles_map;
	private static ArrayList<Node> nodes_list = new ArrayList<Node>();
	
	/* createImage method
	 * Calculates all the attribute values and then it creates and saves the image
	 * in a folder called "History"
	 * 
	 * @param cont 		starts from 0, specifies the image number in the "History" folder
	 */
	public static void createImage(int cont) {
		//Codifica lines get separeted to make them easier to handle
		ArrayList<String> lines = StringProcessor.separate(codifica.substring(1), "\n");
		//Duplicate lines get removed
		lines = StringProcessor.removeDuplicates(lines);
		//Tree height calculation
        tree_height = calcHeight(lines);
        //Tree members for each height calculation
        members_per_height = calcWidthPerHeight(lines);
        //Tree width calculation is the max numbers of members
        tree_width = Collections.max(members_per_height);
        //Tree nodes tiles mapping calculation
        tree_tiles_map = calcNodesTiles(lines);
        //img size calculations
        img_width = tree_width * 220;
        img_height = (tree_height+1) * 150;
        //Nodes creation
        createNodes(lines);
        
        //The img gets itialized with the newly calculated sizes
        img = new BufferedImage(img_width, img_height, BufferedImage.TYPE_INT_RGB);
        //Now g2d is able to modify img graphics
        g2d = (Graphics2D) img.getGraphics();
        //g2d fills the whole image with maroon color
        g2d.setColor(new Color(190, 50, 52));
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        //All the nodes get drawn
        drawNodes(nodes_list, g2d);
        
        //Once the img has been successfully drawn it gets saved in the "History" folder
        try {
            File outputfile = new File("history\\tree" + cont + ".png");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            System.out.println("\n\n error, path currently unavailable");
        }
	}
	
	/* calcHeight method
	 * Calculates tree height by finding the line with the highest amount
	 * of tabs (\t), the number of max tabs will be the tree height
	 * 
	 * @param lines		all the lines from codifica
	 * @return			max_height found after comparing every line
	 */
	public static int calcHeight(ArrayList<String> lines) {
		//The height gets initialized as 0
		int max_height = 0, line_height = 0;
		//If the lines ArrayList is empty, the tree depth is 0
		if (lines.isEmpty()) 
			return max_height;
		//For each element in lines
        for(int i = 0; i < lines.size(); i++) {
        	//The number of tabs (\t) means how deep is a node placed
        	line_height = StringProcessor.countMatches(lines.get(i), "\t");
        	//If the current line_height is bigger than the max_height ...
            if(line_height > max_height) 
            	// ... max_height gets updated
            	max_height = line_height;
        }
        //Then it gets returned
        return max_height;
    }
	
	/* calcWidthPerHeight method
	 * Calculates the width (number of members) for each height level of the tree
	 * by calculating the number of tabs (/t) for each line and then adding 1 to the
	 * found height depth
	 * 
	 * @param lines		all the lines from codifica
	 * @return			an array with all the members for each depth level 
	 */
	public static ArrayList<Integer> calcWidthPerHeight(ArrayList<String> lines) {
		//At first the ArrayList gets initialized
		ArrayList<Integer> width_per_height = new ArrayList<Integer>();
		//If the lines ArrayList is empty, the members per height is an empty ArrayList
		if (lines.isEmpty())
			return width_per_height; 
		//The ArrayList gets filled with as many zeros as the tree_height plus 1
		for(int i = 0; i < tree_height+1; i++)
			width_per_height.add(0);
		int line_width = 0;
		//For each line ...
		for(int i = 0; i < lines.size(); i++) {
			// ... the number of tabs indicates the line width
			line_width = StringProcessor.countMatches(lines.get(i), "\t");
			//The line width gets used as index and its value get increased by 1
			width_per_height.set(line_width, width_per_height.get(line_width) + 1);
		}
		//Then width_per_height gets returned
		return width_per_height;
	}
	
	/* calcNodesTiles method
	 * Creates a list with all the x and y tiles coordinate for each line in the
	 * corresponding index
	 * 
	 * Example:	Codfica: Vespasiano
	 * 						 Tito
	 * 							 Giulia Flavia 
	 * 							 Flavia						  
	 *						 Domiziano
	 * 			Result: [[1,1], [2,1], [3,1], [3,2], [2,2]]
	 * 
	 * 
	 * @param lines		all the lines from codifica
	 * @return			an array with lines coordinates at the corresponding index
	 */
	public static ArrayList<ArrayList<Integer>> calcNodesTiles(ArrayList<String> lines) {
		//At first the ArrayList gets initialized
		ArrayList<ArrayList<Integer>> y_x_list = new ArrayList<ArrayList<Integer>>();
		//This ArrayList contains X coordinate for each Y coordinate
		ArrayList<Integer> x_counter_per_y = new ArrayList<Integer>();
		//If the lines ArrayList is empty, the members per height is an empty ArrayList
		if (lines.isEmpty()) {	return y_x_list;   }
		//The ArrayList gets filled with as many zeros as the tree_height plus 1
		for(int i = 0; i < tree_height+1; i++)
			x_counter_per_y.add(0);
		//Temporary variables
		int y, x;
		//For each line in lines ArrayList
		for(int i = 0; i < lines.size(); i++) {
			//The number of tabs (\t) indicates the level of depth
			y = StringProcessor.countMatches(lines.get(i), "\t");
			x = x_counter_per_y.get(y);
			//The X coordinate at Y level gets increased by one
			x_counter_per_y.set(y, x+1);
			//Tempororay ArrayList of the Y_X coordinates to add
			ArrayList<Integer> yx_toAdd = new ArrayList<Integer>();
			yx_toAdd.add(y+1);
			yx_toAdd.add(x+1);
			y_x_list.add(yx_toAdd);
		}
		//Then y_x list gets returned to tree_tiles_map
		return y_x_list;
	}
	
	/* createNodes method
	 * Creates the list of nodes to draw using the attributes calculated before,
	 * each iteration creates a new Node object
	 * 
	 * @param lines		all the lines from codifica	
	 */
	public static void createNodes(ArrayList<String> lines) {
		//The nodes list gets cleared for every search call to avoid overlapping trees
		nodes_list.clear();
		//For each element in tree_tiles_map ... 
		for(int i = 0; i<tree_tiles_map.size(); i++) {
        	int y = tree_tiles_map.get(i).get(0);
        	int x = tree_tiles_map.get(i).get(1);
        	// ... a new Node object gets created and put in the nodes_list
        	nodes_list.add(new Node(lines.get(i), y, x, members_per_height, img_width));
        }
	}
	
	/* drawNodes method
	 * After the nodes list gets filled all the nodes and outgoing arrows are ready to be
	 * drawn.
	 * For each iteration, g2d performs these actions:
	 * 1. draws the rectangle filling it using white color
	 * 2. draws the rectangle's borders using black color
	 * 3. draws the rectangle's arrows indicating his children. Starting from the current node index
	 *    contained in nodes_list, the for cycles looks for the first current node number of children
	 *    nodes with the following y_tile, since the nodes are organized in the known order this
	 *    will mean they are current nodes's children.
	 *    For each match, g2d will draw a line starting from the parent Bottom coordinates to the
	 *    children Top coordinates
	 * 4. draws the text contained in the current node text attribute inside the white rectangle's
	 *    area and it also calculate the number of text lines, this data will be useful to pick
	 *    the icons image to draw in the next step
	 * 5. draws the icon image next to the names contained in the rectangle
	 * 
	 * @param nodes_list	list of the tree's nodes
	 * @param g2d			the actor object that draws the image
	 */
	public static void drawNodes(ArrayList<Node> nodes_list, Graphics2D g2d) {
		int base, height, x_start, y_start;
		for(int i=0; i < nodes_list.size(); i++) {
			//Node rectangles data gets extracted, rounded eventually
			base = nodes_list.get(i).getRECT_B();
			height = nodes_list.get(i).getRECT_H();
			x_start = (int) nodes_list.get(i).getTopX() - base/2;
			y_start = nodes_list.get(i).getTopY();
			
			//1
			//Rectangle gets filled with white color
			g2d.setColor(Color.WHITE);
			g2d.fillRect(x_start, y_start, base, height);
			//2
			//Then the border gets drawn with black color
			g2d.setColor(Color.BLACK);
			g2d.drawRect(x_start, y_start, base, height);
			g2d.drawRect(x_start+1, y_start+1, base, height);
			//3
			int n_frecce = 0;
			//This condition becomes false when the cycle encounters a node in the same y tile or when elements are over ...
			// ... and this is useful to avoid drawing errors after duplicate lines get removed
			boolean cond = true;
			//The cycle ends when all the arrows have been drawn, the condition is true and elements are over ...
			// ... the cycle starts from the next position of the current drawn node
			for(int j=i+1;  n_frecce < nodes_list.get(i).getN_children() && cond && j < nodes_list.size(); j++) {
				//If the current node is 1 y tile deeper than the starting node...
				if(nodes_list.get(i).getTileY() == (nodes_list.get(j).getTileY()-1)) {
					n_frecce++;
					//... 2 lines get drawn from the starting node top to the current node bottom to form a thicker arrow
					g2d.drawLine(nodes_list.get(i).getBtmX(), nodes_list.get(i).getBtmY(), nodes_list.get(j).getTopX(), nodes_list.get(j).getTopY());
					g2d.drawLine(nodes_list.get(i).getBtmX()+1, nodes_list.get(i).getBtmY()+1, nodes_list.get(j).getTopX()+1, nodes_list.get(j).getTopY()+1);
				}
				else if (nodes_list.get(i).getTileY() == (nodes_list.get(j).getTileY()) || j >= nodes_list.size()) {
					cond = false;
				}
			}
			//4
			//This attribute is used to pick the right image in the next step
			String pick = "";
			//Coordinates shifting to draw right into the rectangle
			int x = x_start+15, y = y_start+15;
			//Each name in the text gets separeted
			ArrayList<String> text_lines = StringProcessor.separate(nodes_list.get(i).getText(), "  ");
			//Each name gets written following the original order
			for(int j = 0; j < text_lines.size(); j++) {
				//The line gets written
				g2d.drawString(text_lines.get(j), x, y);
				//Y coordinate shift
				y = y + 10;
				//Spoused indentation in the rectangle
				if(j == 0)
					//X coordinate shift
					x = x + 5; 
				else {
					//For each line a number gets added
					//Example: 3 lines ----> 123	2 lines ----> 12
					pick = pick + Integer.toString(j);
				}
			}
			//5
			//The image to draw next to the Node text needs to be chose
			BufferedImage toDraw = null;
			//If the Node contains an emperor the image will be different ...
			if(nodes_list.get(i).hasImp()) {
				try { toDraw = ImageIO.read(new File("_icons/imp_"+pick+".png")); } 
				catch (IOException e) {	e.printStackTrace(); }
			}
			// ... from an ordinart member
			else {
				try { toDraw = ImageIO.read(new File("_icons/mem_"+pick+".png")); } 
				catch (IOException e) {	e.printStackTrace(); }
			}
			//Then it gets drawn
			g2d.drawImage(toDraw, x_start+3, y_start+2, toDraw.getWidth(), toDraw.getHeight(), null);
		}
	}
	
	/* setCodifica method
	 * @param s 	The semi-processed String
	 */
	public static void setCodifica(String s) {
		codifica = s;
	}
}
