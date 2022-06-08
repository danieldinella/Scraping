
import java.util.ArrayList; 
import java.util.Collections;

public class Node {
	private int tile_y;
	private int tile_x;
	private int[] top = {0, 0};
	private int[] bottom = {0, 0};
	private int n_children;
	private boolean imp = false;
	private String text;
	private int RECT_H = 70;
	private int RECT_B = 200;
	
	
	public Node(String n, int tile_y, int tile_x, ArrayList<ArrayList<Integer>> tree_tiles_map, ArrayList<Integer> members_per_height, int img_width) {
		//NODE TILES
		this.tile_x = tile_x;
		this.tile_y = tile_y;
		
		//TEXT, N_CHILDREN AND IMP CALCULATION
		if (n.contains(":")) {
			int index = n.indexOf(":")+1;
			this.n_children = Character.getNumericValue(n.charAt(index));
			String childrenNotation = n.substring(index-5, index+1);
			n = n.replace(childrenNotation, "");
		}
		n = n.replace("\t", "");
		if (n.contains("*")) {	
			n = n.replace("*", "");
			this.imp = true;
		}
		if (n.contains("+ [")) {
			n = n.replace("+", "").replace(",", " ");
			n = n.replace("[", "").replace("]", "");
		}
		this.text = n;
		
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
