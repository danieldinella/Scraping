
import java.util.ArrayList; 
import java.util.Collections;

public class Node {
	/* int tile_y and tile_x attributes
	 *  Useful for coordinate, rectangle placing and arrow pointing calculations
	 * int[] top and bottom attributes
	 *  Used when it comes to define the coordinates position of rectangle and
	 *  connection arrows drawing
	 * int n_children attribute
	 *  Specifies the number of outgoing arrows from a node, doesn't work in duplicate
	 *  nodes
	 * boolean imp attribute
	 *  1 = Was an Emperor, 0 = Wasn't an Emperor 
	 *  
	 */
	private int tile_y;	
	private int tile_x;
	private int[] top = {0, 0};
	private int[] bottom = {0, 0};
	private int n_children;
	private boolean imp = false;
	private String text;
	private final int RECT_H = 70;
	private final int RECT_B = 200;
	
	/* NODE CONSTRUCTOR METHOD
	 * This method defines all the Node object attributes values by calculating them 
	 * starting from the given parameters.
	 * The informations contained in the String t parameter will be extracted to be put into
	 * the right parameters and then edited to put them in the text attribute.
	 * The remaining parameters will be used to calculate coordinates 
	 * 
	 * @param t							The semi-processed text
	 * @param tile_y 					Node y tile
	 * @param tile_x    				Node x tile
	 * @param members_per_height		Tells how many members there in the same y tile
	 * @param img_width					The already calculated final image width 
	 */
	public Node(String t, int tile_y, int tile_x, ArrayList<Integer> members_per_height, int img_width) {
		//NODE TILES
		this.tile_x = tile_x;
		this.tile_y = tile_y;
		
		//TEXT, N_CHILDREN AND IMP CALCULATION
		if (t.contains(":")) {		    
			int index = t.indexOf(":")+1;
			this.n_children = Integer.parseInt(t.substring(index, index+2).replace(" ", ""));
			String childrenNotation = t.substring(index-5, index+2);
			t = t.replace(childrenNotation, "");
		}
		t = t.replace("\t", "");
		if (t.contains("*")) {	
			t = t.replace("*", "");
			this.imp = true;
		}
		if (t.contains("+ [")) {
			t = t.replace("+", "").replace(",", " ");
			t = t.replace("[", "").replace("]", "");
		}
		this.text = t;
		
		//TOP POINT CALCULATION
		int border = 20;
		this.top[0] = ((tile_y-1)*150) + border;
		if(members_per_height.get(tile_y-1) == 1)
			this.top[1] = (int) (img_width/2);
		else {
			int a = (int) img_width/(members_per_height.get(tile_y-1)*2);
			int b = (int) img_width/(members_per_height.get(tile_y-1));
			this.top[1] = (int) a + b*(tile_x-1);
		}
		
		//BOTTOM POINT CALCULATION
		this.bottom[0] = this.top[0] + RECT_H;
		this.bottom[1] = this.top[1];
	}
	
	public int getTileX() {
		return tile_x;
	}
	public int getTileY() {
		return tile_y;
	}
	public int getN_children(){	
		return n_children;
	}
	public boolean hasImp(){	
		return imp;			
	}
	public String getText(){	
		return text;
	}
	public int getRECT_H(){	
		return RECT_H;
	}
	public int getRECT_B(){	
		return RECT_B;	
	}
	public int getTopX(){
		return top[1];
	}
	public int getTopY(){
		return top[0];
	}
	public int getBtmX(){
		return bottom[1];
	}
	public int getBtmY(){
		return bottom[0];
	}
}
