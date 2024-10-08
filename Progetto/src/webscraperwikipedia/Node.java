package webscraperwikipedia;
import java.util.ArrayList;  
import java.util.Collections;

/** Node class
 * This class contains a node attributes, all of them get initialized in the constructor
 * method and they are useful when it comes to draw the node thanks to its get methods
 * 
 * @author Luca Ciafardoni
 * @version 1.1.2
 */
public class Node {
	private int tile_y;	
	private int tile_x;
	private int[] top = {0, 0};
	private int[] bottom = {0, 0};
	private int n_children;
	private boolean imp = false;
	private String text;
	private final int RECT_H = 70;
	private final int RECT_B = 210;
	
	/**
	 * This method defines all the Node object attributes values by calculating them 
	 * starting from the given parameters.
	 * The informations contained in the String t parameter will be extracted to be put into
	 * the right parameters and then edited to put them in the text attribute.
	 * The remaining parameters will be used to calculate coordinates 
	 * 
	 * @param t, the semi-processed text
	 * @param tile_y, node y tile
	 * @param tile_x, node x tile
	 * @param members_per_height, tells how many members there in the same y tile
	 * @param img_width, the already calculated final image width 
	 */
	public Node(String t, int tile_y, int tile_x, ArrayList<Integer> members_per_height, int img_width) {
		//NODE TILES
		this.tile_x = tile_x;
		this.tile_y = tile_y;
		
		//TEXT, N_CHILDREN AND IMP CALCULATION
		//In order to store the number of children in a line the index of ":" needs to be found
		if (t.contains(":")) {		    
			//The character next to ":" is a number
			int index = t.indexOf(":")+1;
			//The 2 characters next to ":" will be converted to integer numbers
			//Example1: "3 " ---> 3		space next to a single digit number gets removed
			//Example2: "12" ---> 12    a member usually has got up to 2-digit number children
			this.n_children = Integer.parseInt(t.substring(index, index+2).replace(" ", ""));
			//The substring N_F: nn gets removed from the final result text
			String childrenNotation = t.substring(index-5, index+2);
			t = t.replace(childrenNotation, "");
		}
		//The tabs in text will be remove since they aren't useful anymore
		t = t.replace("\t", "");
		//If the text contains the character "*"...
		if (t.contains("*")) {
			// ... it will get removed from the final result text
			t = t.replace("*", "");
			// And this will mean that the node contains an emperor
			this.imp = true;
		}
		//If the text contains "+ [" ...
		if (t.contains("+ [")) {
			// ... all the brackets containing its spouses, signs and commas will get removed
			t = t.replace("+", "").replace(",", " ");
			t = t.replace("[", "").replace("]", "");
		}
		//The final resulting text is now ready and will be written in a second place
		this.text = t;
		
		//TOP POINT CALCULATION
		//The pixel separating the end of the image is a constant number, 20
		int border = 20;
		//Y coordinate gets calculated using this formula
		this.top[0] = ((tile_y-1)*150) + border;
		//X coordinate gets calculated using this formulas ...
		// ... if there is only one node in the current y_tile ... 
		if(members_per_height.get(tile_y-1) == 1)
			// ... X coordinate is just half the image width
			this.top[1] = (int) (img_width/2);
		// ... otherwise the X coordinate needs to be calculated differently
		else {
			int a = (int) img_width/(members_per_height.get(tile_y-1)*2);
			int b = (int) img_width/(members_per_height.get(tile_y-1));
			// X coordinate is related to other existing nodes in the same y_tile
			this.top[1] = (int) a + b*(tile_x-1);
		}
		
		//BOTTOM POINT CALCULATION
		//Top coordinates just need to be shifted
		this.bottom[0] = this.top[0] + RECT_H;
		this.bottom[1] = this.top[1];
	}
	
	/**
	 * tile_x getter method
	 * @return node x tile
	 */
	public int getTileX() {
		return tile_x;
	}
	/** 
	 * tile_y getter method
	 * @return node y tile
	 */
	public int getTileY() {
		return tile_y;
	}
	/** 
	 * n_children getter method
	 * @return node number of children
	 */
	public int getN_children(){	
		return n_children;
	}
	/** 
	 * imp true/false getter method
	 * @return true if node contains an emperor or empress
	 */
	public boolean hasImp(){	
		return imp;			
	}
	/** 
	 * text getter method
	 * @return node text
	 */
	public String getText(){	
		return text;
	}
	/** 
	 * RECT_H getter method
	 * @return node rectangle height
	 */
	public int getRECT_H(){	
		return RECT_H;
	}
	/** 
	 * RECT_B getter method
	 * @return node rectangle base
	 */
	public int getRECT_B(){	
		return RECT_B;	
	}
	/** 
	 * Top x coordinate getter method
	 * @return node top x coordinate
	 */
	public int getTopX(){
		return top[1];
	}
	/**
	 * Top y coordinate getter method
	 * @return node top y coordinate
	 */
	public int getTopY(){
		return top[0];
	}
	/**
	 * Bottom x coordinate getter method
	 * @return node bottom x coordinate
	 */
	public int getBtmX(){
		return bottom[1];
	}
	/** 
	 * Bottom y coordinate getter method
	 * @return node bottom y coordinate
	 */
	public int getBtmY(){
		return bottom[0];
	}
}