
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
	private static String codifica = "";
	private BufferedImage img;
	private Graphics2D g2d;
	private int tree_height;
	private int tree_width;
	private int img_height;
	private int img_width;
	private ArrayList<Integer> members_per_height;
	private ArrayList<ArrayList<Integer>> tree_tiles_map;
	private ArrayList<Node> nodes_list = new ArrayList<Node>();
	
	public TreeImage(int cont) {
        ArrayList<String> lines = stringSeparetor(this.codifica.substring(1), "\n");
        
        //TREE HEIGHT CALCULATION
        this.tree_height = calcHeight(lines);
        
        //TREE MEMBERS FOR EACH HEIGHT CALCULATION
        this.members_per_height = calcWidthPerHeight(lines, this.tree_height);
        
        //TREE WIDTH CALCULATION
        this.tree_width = Collections.max(this.members_per_height);
        
        //TREE NODES TILES MAPPING CALCULATION
        this.tree_tiles_map = calcNodesTiles(lines, tree_height);
        
        //IMG SIZE CALCULATION
        this.img_width = tree_width * 210;
        this.img_height = (tree_height+1) * 150;
           
        //NODES CREATION
        for(int i = 0; i<tree_tiles_map.size(); i++) {
        	int y = tree_tiles_map.get(i).get(0);
        	int x = tree_tiles_map.get(i).get(1);
        	nodes_list.add(new Node(lines.get(i), y, x, tree_tiles_map, members_per_height, img_width));
        }
        
        img = new BufferedImage(img_width, img_height, BufferedImage.TYPE_INT_RGB);
        g2d = (Graphics2D) img.getGraphics();
        g2d.setColor(new Color(190, 50, 52));
        g2d.fillRect(0, 0, img_width, img_height);
        drawMemberRectangles(nodes_list, g2d);
        
        try {
            File outputfile = new File("history\\tree" + cont + ".png");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            System.out.println("\n\n errore");
        }
        
        
        
        //System.out.println("Altezza albero: " + this.tree_height);
        //System.out.println("Ampiezza albero: " + this.tree_width);
        //System.out.println("Schema altezza: " + this.members_per_height);
        //System.out.println("Tileset nodi: " + this.tree_tiles_map);
        //for(int i = 0; i<lines.size(); i++) {
        	
        
	}
	
	public static int countMatches(String line, String target) {
        return line.split(target, -1).length - 1;
    }
	
	public static ArrayList<String> stringSeparetor(String csv, String el) {
		//Converting NewLine separate String to array of String 
		String[] elements = csv.split(el); 
		//Convert String array to list of String 
		List<String> fixedLenghtList = Arrays.asList(elements); 
		//Copy fixed list to an ArrayList 
		ArrayList<String> listOfString = new ArrayList<String>(fixedLenghtList); 
		return listOfString;
	}
	
	
	public static int calcHeight(ArrayList<String> lines) {
		int max_height = 0, line_height = 0;
		if (lines.isEmpty()) {	return max_height;	}
        for(int i = 0; i < lines.size(); i++) {
        	line_height = countMatches(lines.get(i), "\t");
            if(line_height > max_height) {
            	max_height = line_height;
            }
        }
        return max_height;
    }
	
	public static ArrayList<Integer> calcWidthPerHeight(ArrayList<String> lines, int height) {
		ArrayList<Integer> width_per_height = new ArrayList<Integer>();
		int line_width = 0;
		if (lines.isEmpty()) {	return width_per_height;   }
		for(int i = 0; i < height+1; i++) {
			width_per_height.add(0);
		}
		for(int i = 0; i < lines.size(); i++) {
			line_width = countMatches(lines.get(i), "\t");
			width_per_height.set(line_width, width_per_height.get(line_width) + 1);
		}
		return width_per_height;
	}
	
	public static ArrayList<ArrayList<Integer>> calcNodesTiles(ArrayList<String> lines, int tree_height) {
		ArrayList<ArrayList<Integer>> y_x_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> x_counter_per_y = new ArrayList<Integer>();
		if (lines.isEmpty()) {	return y_x_list;   }
		for(int i = 0; i < tree_height+1; i++) {
			x_counter_per_y.add(0);
		}
		int x;
		int y;
		for(int i = 0; i < lines.size(); i++) {
			y = countMatches(lines.get(i), "\t");
			x = x_counter_per_y.get(y);
			x_counter_per_y.set(y, x_counter_per_y.get(y)+1);
			// y_x_list.add(new ArrayList<Integer>() {{ add(y+1); add(x+1);}});
			ArrayList<Integer> yx_toAdd = new ArrayList<Integer>();
			yx_toAdd.add(y+1);
			yx_toAdd.add(x+1);
			y_x_list.add(yx_toAdd);
			
		}
		return y_x_list;
	}
	
	public static void drawMemberRectangles(ArrayList<Node> nodes_list, Graphics2D g2d) {
		int base; 
		int height; 
		int x_start;
		int y_start;
		for(int i=0; i < nodes_list.size(); i++) {
			base = nodes_list.get(i).getRECT_B();
			height = nodes_list.get(i).getRECT_H();
			x_start = (int) nodes_list.get(i).getTopX() - base/2;
			y_start = nodes_list.get(i).getTopY();
			
			g2d.setColor(Color.WHITE);
			g2d.fillRect(x_start, y_start, base, height);
			
			g2d.setColor(Color.BLACK);
			g2d.drawRect(x_start, y_start, base, height);
			g2d.drawRect(x_start+1, y_start+1, base, height);
			
			int n_frecce = 0;
			for(int j=i;  n_frecce < nodes_list.get(i).getN_children(); j++) {
				if(nodes_list.get(i).getTileY() == (nodes_list.get(j).getTileY()-1)) {
					n_frecce++;
					g2d.drawLine(nodes_list.get(i).getBtmX(), nodes_list.get(i).getBtmY(), nodes_list.get(j).getTopX(), nodes_list.get(j).getTopY());
					g2d.drawLine(nodes_list.get(i).getBtmX()+1, nodes_list.get(i).getBtmY()+1, nodes_list.get(j).getTopX()+1, nodes_list.get(j).getTopY()+1);
				}
			}
			
			
			String pick = "";
			int x = x_start+15, y = y_start+15;
			ArrayList<String> text_lines = stringSeparetor(nodes_list.get(i).getText(), "  ");
			for(int j = 0; j < text_lines.size(); j++) {
				g2d.drawString(text_lines.get(j), x, y);
				y = y + 10;
				if(j == 0)
					x = x + 5; 
				else {
					pick = pick + Integer.toString(j);
				}
			}
			
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
	
	public static void processString(Person root, String tabs) {
		//System.out.print(tabs);
		codifica += "\n" + tabs;
		if(root.checkImperatore())
			//System.out.print("*");
			codifica += "*";
		if(root.getFigli().isEmpty())
			if(root.getConiuge().isEmpty()) 
				//System.out.print(root.getNome() + "\n");
				codifica += (root.getNome());
			else
				//System.out.print(root.getNome() + " + " + root.getConiuge() + "\n");
				codifica +=(root.getNome() + " + " + root.getConiuge());
		else {
			if(root.getConiuge().isEmpty())
				//System.out.print(root.getNome() + "\n");
				codifica += (root.getNome());
			else
				//System.out.print(root.getNome() + " + " + root.getConiuge() + "\n");
				codifica += (root.getNome() + " + " + root.getConiuge());
			ArrayList<Person> figli = root.getFigli();
			if(!root.getFigli().isEmpty())
				codifica += (" N_F :"+figli.size());
			tabs = tabs + "\t";
			for(int i = 0; i < figli.size(); i++) {
				processString(figli.get(i), tabs);
			}
		}
	}
	
	public static void resetCodifica() {
		codifica = "";
	}
}
