import java.util.ArrayList;

public class Coordinate {
	String value; // the value of the current focused cell in the map
	int row; // the current row
	int col; // the current column
	boolean visited; // if the cell has been visited or not

	public Coordinate() {
		value = "";
		row = 0;
		col = 0;
		visited = false;
	}

	public Coordinate(ArrayList<String> currMap, int i) {
		
		
		String[] curr = currMap.get(i).split("\\s");
		value = curr[0];
		row = Integer.parseInt(curr[1]);
		col = Integer.parseInt(curr[2]);

	}
	
	//currMap example: [. 0 0, . 0 1, . 0 2, . 0 3, . 1 0, @ 1 1, . 1 2, $ 1 3, . 2 0, @ 2 1, . 2 2, . 2 3, W 3 0, . 3 1, @ 3 2, . 3 3] 
	//will use i and then get the values of each element for value, row, or col

	
	//getters and setters

	public String getValue() {
		return value;
	}

	public boolean getVisited() {
		return visited;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setVisited(boolean setter) {
		visited = setter;
	}

	public void setValue(String valuer) {
		value = valuer;
	}
}