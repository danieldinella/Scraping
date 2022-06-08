
import java.util.ArrayList; 
import java.util.Collections;

public class Node {
	/* Node Attributes
	 * tile_y and tile_x attributes are useful for coordinate and rectangle placing calculations,
	 * top and bottom are used when it comes to define the coordinates position of rectangle and
	 * connection arrows drawing, their amount is defined by m_children.
	 * imp tells if a node contains an emperor, this attribute is useful to pick the right icon,
	 * while the text attribute contains the node full name and his/her spouses
	 * RECT_H and RECT_B are constant values that are the rectangle base and height
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
			this.n_children = Character.getNumericValue(t.charAt(index));
			String childrenNotation = t.substring(index-5, index+1);
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
