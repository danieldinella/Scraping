
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

public class TreeImage {
	/* TreeImage Attributes
	 * codifica, members_per_height, tree_tiles_map get calculated and then used
	 * as parameters for Node objects constructor.
	 * The remaining attributes are used to draw the actual tree image
	 * 
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
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(StringProcessor.getCodifica());
		
		//CODIFICA LINES GET SEPARETED TO MAKE THEM EASIER TO HANDLE 
		ArrayList<String> lines = StringProcessor.separate(codifica.substring(1), "\n");
        
		//DUPLICATE LINES GET REMOVED
		lines = StringProcessor.removeDuplicates(lines);
		for(int i = 0; i < lines.size(); i++)
			System.out.println(lines.get(i));
		
        //TREE HEIGHT CALCULATION
        tree_height = calcHeight(lines);
        
        //TREE MEMBERS FOR EACH HEIGHT CALCULATION
        members_per_height = calcWidthPerHeight(lines);
        
        //TREE WIDTH CALCULATION
        tree_width = Collections.max(members_per_height);
        
        //TREE NODES TILES MAPPING CALCULATION
        tree_tiles_map = calcNodesTiles(lines);
        
        //IMG SIZE CALCULATION
        img_width = tree_width * 210;
        img_height = (tree_height+1) * 150;
           
        //NODES CREATION
        createNodes(lines);
        
        
        img = new BufferedImage(img_width, img_height, BufferedImage.TYPE_INT_RGB);
        g2d = (Graphics2D) img.getGraphics();
        g2d.setColor(new Color(190, 50, 52));
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        drawNodes(nodes_list, g2d);
        
        try {
            File outputfile = new File("history\\tree" + cont + ".png");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            System.out.println("\n\n errore");
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
		int max_height = 0, line_height = 0;
		if (lines.isEmpty()) {	return max_height;	}
        for(int i = 0; i < lines.size(); i++) {
        	line_height = StringProcessor.countMatches(lines.get(i), "\t");
            if(line_height > max_height) {
            	max_height = line_height;
            }
        }
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
		ArrayList<Integer> width_per_height = new ArrayList<Integer>();
		int line_width = 0;
		if (lines.isEmpty()) {	return width_per_height;   }
		for(int i = 0; i < tree_height+1; i++) {
			width_per_height.add(0);
		}
		for(int i = 0; i < lines.size(); i++) {
			line_width = StringProcessor.countMatches(lines.get(i), "\t");
			width_per_height.set(line_width, width_per_height.get(line_width) + 1);
		}
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
		ArrayList<ArrayList<Integer>> y_x_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> x_counter_per_y = new ArrayList<Integer>();
		if (lines.isEmpty()) {	return y_x_list;   }
		for(int i = 0; i < tree_height+1; i++) {
			x_counter_per_y.add(0);
		}
		int x;
		int y;
		for(int i = 0; i < lines.size(); i++) {
			y = StringProcessor.countMatches(lines.get(i), "\t");
			x = x_counter_per_y.get(y);
			x_counter_per_y.set(y, x_counter_per_y.get(y)+1);
			ArrayList<Integer> yx_toAdd = new ArrayList<Integer>();
			yx_toAdd.add(y+1);
			yx_toAdd.add(x+1);
			y_x_list.add(yx_toAdd);
		}
		return y_x_list;
	}
	
	/* createNodes method
	 * Creates the list of nodes to draw using the attributes calculated before,
	 * each iteration creates a new Node object
	 * 
	 * @param lines		all the lines from codifica	
	 */
	public static void createNodes(ArrayList<String> lines) {
		nodes_list.clear();
		for(int i = 0; i<tree_tiles_map.size(); i++) {
        	int y = tree_tiles_map.get(i).get(0);
        	int x = tree_tiles_map.get(i).get(1);
        	nodes_list.add(new Node(lines.get(i), y, x, members_per_height, img_width));
        }
	}
	
	/* drawNodes method
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
			base = nodes_list.get(i).getRECT_B();
			height = nodes_list.get(i).getRECT_H();
			x_start = (int) nodes_list.get(i).getTopX() - base/2;
			y_start = nodes_list.get(i).getTopY();
			
			//1
			g2d.setColor(Color.WHITE);
			g2d.fillRect(x_start, y_start, base, height);
			//2
			g2d.setColor(Color.BLACK);
			g2d.drawRect(x_start, y_start, base, height);
			g2d.drawRect(x_start+1, y_start+1, base, height);
			//3
			int n_frecce = 0;
			boolean cond = true;
			for(int j=i+1;  n_frecce < nodes_list.get(i).getN_children() && cond && j < nodes_list.size(); j++) {
				if(nodes_list.get(i).getTileY() == (nodes_list.get(j).getTileY()-1)) {
					n_frecce++;
					g2d.drawLine(nodes_list.get(i).getBtmX(), nodes_list.get(i).getBtmY(), nodes_list.get(j).getTopX(), nodes_list.get(j).getTopY());
					g2d.drawLine(nodes_list.get(i).getBtmX()+1, nodes_list.get(i).getBtmY()+1, nodes_list.get(j).getTopX()+1, nodes_list.get(j).getTopY()+1);
				}
				else if (nodes_list.get(i).getTileY() == (nodes_list.get(j).getTileY()) || j >= nodes_list.size()) {
					cond = false;
				}
			}
			//4
			String pick = "";
			int x = x_start+15, y = y_start+15;
			ArrayList<String> text_lines = StringProcessor.separate(nodes_list.get(i).getText(), "  ");
			for(int j = 0; j < text_lines.size(); j++) {
				g2d.drawString(text_lines.get(j), x, y);
				y = y + 10;
				if(j == 0)
					x = x + 5; 
				else {
					pick = pick + Integer.toString(j);
				}
			}
			//5
			BufferedImage toDraw = null;
			if(nodes_list.get(i).hasImp()) {
				try { toDraw = ImageIO.read(new File("_icons/imp_"+pick+".png")); } 
				catch (IOException e) {	e.printStackTrace(); }
			}
			else {
				try { toDraw = ImageIO.read(new File("_icons/mem_"+pick+".png")); } 
				catch (IOException e) {	e.printStackTrace(); }
			}
			g2d.drawImage(toDraw, x_start+3, y_start+2, toDraw.getWidth(), toDraw.getHeight(), null);
		}
	}
	
	public static void setCodifica(String s) {
		codifica = s;
	}
	public static String getCodifica() {
		return codifica;
	}
}
