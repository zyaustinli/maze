import java.util.ArrayList;
//import java.util.Queue;

public class PathFinderOpt {
	Stack<Coordinate> myStack = new Stack<Coordinate>();
	// ArrayList<Coordinate> push = new ArrayList<Coordinate>();
	ArrayList<Coordinate> dequeue = new ArrayList<Coordinate>();
	String[][] mapsolve;
	Coordinate wolverineLocation;
	Coordinate treasureLocation;
	int wolverineArrayIndex;
	int treasureArrayIndex;
	int wX, wY;
	int treasureX, treasureY;
	Coordinate currCell;
	boolean treasureCheck = false;
	String resCoord = "";
	String preResCoord = "";

	public void pathFind(Coordinate[][] map, int rows, int cols, int rooms) { // ,rooms) {
		// find wol current location


		for (int room = 1; room < rooms; room++){
		 // seperate rows to each room

			// wolverine location
			for (int i = 0; i < rows*room; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if (map[i][j].getValue().equals("W")) {
						wolverineLocation = map[i][j];
						wX = i;
						wY = j;
						map[i][j].setVisited(true);
					}
				}
			}

			myStack.push(wolverineLocation);
			while (myStack.size() > 0) {
				currCell = myStack.peek();
				dequeue.add(myStack.pop());
				int pushrX = currCell.getRow(), pushrY = currCell.getCol();
				// north
				if ((pushrX - 1) >= 0
						&& (map[pushrX - 1][pushrY].getValue().equals(".")
								|| map[pushrX - 1][pushrY].getValue().equals("|"))
						&& map[pushrX - 1][pushrY].getVisited() == false) { // first round of pushing
					if (map[pushrX - 1][pushrY].getValue().equals("|")) {
						myStack.push(map[pushrX - 1][pushrY]);
						break;
					}

					myStack.push(map[pushrX - 1][pushrY]);
					map[pushrX - 1][pushrY].setVisited(true);
				}
				// south
				if ((pushrX + 1) < map.length
						&& (map[pushrX + 1][pushrY].getValue().equals(".")
								|| map[pushrX + 1][pushrY].getValue().equals("|"))
						&& map[pushrX + 1][pushrY].getVisited() == false) {
					if (map[pushrX + 1][pushrY].getValue().equals("|")) {
						myStack.push(map[pushrX + 1][pushrY]);
						break;
					}
					myStack.push(map[pushrX + 1][pushrY]);
					map[pushrX + 1][pushrY].setVisited(true);
				}

				// east
				if ((pushrY + 1) < map[0].length
						&& (map[pushrX][pushrY + 1].getValue().equals(".")
								|| map[pushrX][pushrY + 1].getValue().equals("|"))
						&& map[pushrX][pushrY + 1].getVisited() == false) {
					if (map[pushrX][pushrY + 1].getValue().equals("|")) {
						myStack.push(map[pushrX][pushrY + 1]);
						break;
					}
					myStack.push(map[pushrX][pushrY + 1]);
					map[pushrX][pushrY + 1].setVisited(true);
				}

				// west
				if ((pushrY - 1) >= 0
						&& (map[pushrX][pushrY - 1].getValue().equals(".")
								|| map[pushrX][pushrY - 1].getValue().equals("|"))
						&& map[pushrX][pushrY - 1].getVisited() == false) {
					if (map[pushrX][pushrY - 1].getValue().equals("|")) {
						myStack.push(map[pushrX][pushrY - 1]);
						break;
					}
					myStack.push(map[pushrX][pushrY - 1]);
					map[pushrX][pushrY - 1].setVisited(true);
				}

			}

			Coordinate curr = dequeue.get(dequeue.size() - 1);
			System.out.print("\n");

			map[dequeue.get(dequeue.size() - 1).getRow()][dequeue.get(dequeue.size() - 1).getCol()].setValue("+");
			String tileLast = curr.getValue() + " " + curr.getRow() + " " + curr.getCol();

			preResCoord += tileLast+ "\n";

			for (int i = dequeue.size() - 1; i > 0; i--) {
				if ((dequeue.get(i).getRow() == (curr.getRow()))
						&& ((dequeue.get(i).getCol() == (curr.getCol() + 1)))) {

					curr = dequeue.get(i);
					map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setValue("+");
					
					preResCoord += dequeue.get(i).getValue() + " " + dequeue.get(i).getRow() + " " + dequeue.get(i).getCol() + "\n";


				}
				if (dequeue.get(i).getRow() == (curr.getRow()) && (dequeue.get(i).getCol() == (curr.getCol() - 1))) {

					curr = dequeue.get(i);
					map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setValue("+");
					preResCoord += dequeue.get(i).getValue() + " " + dequeue.get(i).getRow() + " " + dequeue.get(i).getCol() + "\n";



				}
				if ((dequeue.get(i).getRow() == (curr.getRow() - 1)) && (dequeue.get(i).getCol() == (curr.getCol()))) {

					curr = dequeue.get(i);
					map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setValue("+");
					preResCoord += dequeue.get(i).getValue() + " " + dequeue.get(i).getRow() + " " + dequeue.get(i).getCol() + "\n";


				}
				if ((dequeue.get(i).getRow() == (curr.getRow() + 1)) && (dequeue.get(i).getCol() == (curr.getCol()))) {

					curr = dequeue.get(i);
					map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setValue("+");
					preResCoord += dequeue.get(i).getValue() + " " + dequeue.get(i).getRow() + " " + dequeue.get(i).getCol() + "\n";

				}

			}
			
			
			myStack.clear();
			dequeue.clear();

			
		}

		//room ends
		
		
		
		
		
		
		
		
		
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getValue().equals("W")) {
					wolverineLocation = map[i][j];
					wX = i;
					wY = j;
					map[i][j].setVisited(true);
				}
			}
		}
		for (int i = 0; i < map.length; i++) { // find the buck
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getValue().equals("$")) {
					treasureLocation = map[i][j];
					treasureX = i;
					treasureY = j;
				}
			}
		}

		if (wolverineLocation == null || treasureLocation == null) {
			System.out.println("no W or no $");
		}

		myStack.push(wolverineLocation);
		while (myStack.size() > 0) {
			currCell = myStack.peek();
			dequeue.add(myStack.pop());
			int pushrX = currCell.getRow(), pushrY = currCell.getCol();
			// north
			if ((pushrX - 1) >= 0
					&& (map[pushrX - 1][pushrY].getValue().equals(".")
							|| map[pushrX - 1][pushrY].getValue().equals("$"))
					&& map[pushrX - 1][pushrY].getVisited() == false) { // first round of pushing
				if (map[pushrX - 1][pushrY].getValue().equals("$")) {
					myStack.push(map[pushrX - 1][pushrY]);
					treasureCheck = true;
					break;
				}
				
				
				myStack.push(map[pushrX - 1][pushrY]);
				map[pushrX - 1][pushrY].setVisited(true);
			}
			// south
			if ((pushrX + 1) < map.length
					&& (map[pushrX + 1][pushrY].getValue().equals(".")
							|| map[pushrX + 1][pushrY].getValue().equals("$"))
					&& map[pushrX + 1][pushrY].getVisited() == false) {
				if (map[pushrX + 1][pushrY].getValue().equals("$")) {
					myStack.push(map[pushrX + 1][pushrY]);
					treasureCheck = true;
					break;
				}
				myStack.push(map[pushrX + 1][pushrY]);
				map[pushrX + 1][pushrY].setVisited(true);
			}

			// east
			if ((pushrY + 1) < map[0].length
					&& (map[pushrX][pushrY + 1].getValue().equals(".")
							|| map[pushrX][pushrY + 1].getValue().equals("$"))
					&& map[pushrX][pushrY + 1].getVisited() == false) {
				if (map[pushrX][pushrY + 1].getValue().equals("$")) {
					myStack.push(map[pushrX][pushrY + 1]);
					treasureCheck = true;
					break;
				}
				myStack.push(map[pushrX][pushrY + 1]);
				map[pushrX][pushrY + 1].setVisited(true);
			}

			// west
			if ((pushrY - 1) >= 0
					&& (map[pushrX][pushrY - 1].getValue().equals(".")
							|| map[pushrX][pushrY - 1].getValue().equals("$"))
					&& map[pushrX][pushrY - 1].getVisited() == false) {
				if (map[pushrX][pushrY - 1].getValue().equals("$")) {
					myStack.push(map[pushrX][pushrY - 1]);
					treasureCheck = true;
					break;
				}
				myStack.push(map[pushrX][pushrY - 1]);
				map[pushrX][pushrY - 1].setVisited(true);
			}

		}
		
		if(treasureCheck == false){
			System.out.println("Treasure cannot be reached");
			//System.exit(0);
		}
		
		

		Coordinate curr = dequeue.get(dequeue.size() - 1);
		System.out.print("\n");
		System.out.print("ANSWER IN COORD FORMAT: \n \n");

		map[dequeue.get(dequeue.size() - 1).getRow()][dequeue.get(dequeue.size() - 1).getCol()].setValue("+");
		String tileLast = curr.getValue()+" "+curr.getRow()+" "+curr.getCol();
		
		resCoord += tileLast + "\n";
		
		
		
		
		
		
		
		for (int i = dequeue.size() -1; i > 0; i--) {
			if ((dequeue.get(i).getRow() == (curr.getRow())) && ((dequeue.get(i).getCol() == (curr.getCol() + 1)))) {

				curr = dequeue.get(i);
				map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setValue("+");
				resCoord += dequeue.get(i).getValue() + " " + dequeue.get(i).getRow() + " " + dequeue.get(i).getCol() + "\n";


			}
			if (dequeue.get(i).getRow() == (curr.getRow()) && (dequeue.get(i).getCol() == (curr.getCol() - 1))) {

				curr = dequeue.get(i);
				map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setValue("+");
				resCoord += dequeue.get(i).getValue() + " " + dequeue.get(i).getRow() + " " + dequeue.get(i).getCol() + "\n";


			}
			if ((dequeue.get(i).getRow() == (curr.getRow() - 1)) && (dequeue.get(i).getCol() == (curr.getCol()))) {

				curr = dequeue.get(i);
				map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setValue("+");
				resCoord += dequeue.get(i).getValue() + " " + dequeue.get(i).getRow() + " " + dequeue.get(i).getCol() + "\n";


			}
			if ((dequeue.get(i).getRow() == (curr.getRow() + 1)) && (dequeue.get(i).getCol() == (curr.getCol()))) {

				curr = dequeue.get(i);
				map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setValue("+");
				resCoord += dequeue.get(i).getValue() + " " + dequeue.get(i).getRow() + " " + dequeue.get(i).getCol() + "\n";

			}

		}
		
		
		map[wolverineLocation.getRow()][wolverineLocation.getCol()].setValue("W");
				
		System.out.println(resCoord+preResCoord);
		System.out.println("\n");

		System.out.println("MAP-BASED ANSWER: ");

		for (int j = 0; j < map.length; j++) {
			
			System.out.println();
			for (int k = 0; k < map[0].length; k++) {
				System.out.print(map[j][k].getValue());
			}
		}


	}
}