import java.util.ArrayList;
//import java.util.Queue;

public class PathFinderQueue {
	Queue<Coordinate> myQueue = new Queue<Coordinate>();
	// ArrayList<Coordinate> enqueue = new ArrayList<Coordinate>();
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

	public void pathFind(Coordinate[][] map, int rows, int cols, int rooms) { 
		
		

		for (int room = 1; room < rooms; room++){
		 // separate rows to each room

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

			myQueue.enqueue(wolverineLocation);
			while (myQueue.size() > 0) {
				currCell = myQueue.peek();
				dequeue.add(myQueue.dequeue());
				int enqueuerX = currCell.getRow(), enqueuerY = currCell.getCol();
				
				
				
				//enqueue algo
				// north
				if ((enqueuerX - 1) >= 0
						&& (map[enqueuerX - 1][enqueuerY].getValue().equals(".")
								|| map[enqueuerX - 1][enqueuerY].getValue().equals("|"))
						&& map[enqueuerX - 1][enqueuerY].getVisited() == false) { // first round of enqueueing
					if (map[enqueuerX - 1][enqueuerY].getValue().equals("|")) {
						myQueue.enqueue(map[enqueuerX - 1][enqueuerY]);
						break;
					}

					myQueue.enqueue(map[enqueuerX - 1][enqueuerY]);
					map[enqueuerX - 1][enqueuerY].setVisited(true);
				}
				// south
				if ((enqueuerX + 1) < map.length
						&& (map[enqueuerX + 1][enqueuerY].getValue().equals(".")
								|| map[enqueuerX + 1][enqueuerY].getValue().equals("|"))
						&& map[enqueuerX + 1][enqueuerY].getVisited() == false) {
					if (map[enqueuerX + 1][enqueuerY].getValue().equals("|")) {
						myQueue.enqueue(map[enqueuerX + 1][enqueuerY]);
						break;
					}
					myQueue.enqueue(map[enqueuerX + 1][enqueuerY]);
					map[enqueuerX + 1][enqueuerY].setVisited(true);
				}

				// east
				if ((enqueuerY + 1) < map[0].length
						&& (map[enqueuerX][enqueuerY + 1].getValue().equals(".")
								|| map[enqueuerX][enqueuerY + 1].getValue().equals("|"))
						&& map[enqueuerX][enqueuerY + 1].getVisited() == false) {
					if (map[enqueuerX][enqueuerY + 1].getValue().equals("|")) {
						myQueue.enqueue(map[enqueuerX][enqueuerY + 1]);
						break;
					}
					myQueue.enqueue(map[enqueuerX][enqueuerY + 1]);
					map[enqueuerX][enqueuerY + 1].setVisited(true);
				}

				// west
				if ((enqueuerY - 1) >= 0
						&& (map[enqueuerX][enqueuerY - 1].getValue().equals(".")
								|| map[enqueuerX][enqueuerY - 1].getValue().equals("|"))
						&& map[enqueuerX][enqueuerY - 1].getVisited() == false) {
					if (map[enqueuerX][enqueuerY - 1].getValue().equals("|")) {
						myQueue.enqueue(map[enqueuerX][enqueuerY - 1]);
						break;
					}
					myQueue.enqueue(map[enqueuerX][enqueuerY - 1]);
					map[enqueuerX][enqueuerY - 1].setVisited(true);
				}

			}

			Coordinate curr = dequeue.get(dequeue.size() - 1);
			System.out.print("\n");

			map[dequeue.get(dequeue.size() - 1).getRow()][dequeue.get(dequeue.size() - 1).getCol()].setValue("+");
			String tileLast = curr.getValue() + " " + curr.getRow() + " " + curr.getCol();
			//set last tile to + 

			preResCoord += tileLast+ "\n";

			
			// go thru dequeue list and see if touching previous coord, if so, change value and print
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
			
			
			myQueue.clear();
			dequeue.clear();

			
		}

		//room ends
		
		
		
		
		
		
		
		
		//repeat but instead of looking for path to | look for path to $
		
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

		myQueue.enqueue(wolverineLocation);
		while (myQueue.size() > 0) {
			currCell = myQueue.peek();
			dequeue.add(myQueue.dequeue());
			int enqueuerX = currCell.getRow(), enqueuerY = currCell.getCol();
			// north
			if ((enqueuerX - 1) >= 0
					&& (map[enqueuerX - 1][enqueuerY].getValue().equals(".")
							|| map[enqueuerX - 1][enqueuerY].getValue().equals("$"))
					&& map[enqueuerX - 1][enqueuerY].getVisited() == false) { // first round of enqueueing
				if (map[enqueuerX - 1][enqueuerY].getValue().equals("$")) {
					myQueue.enqueue(map[enqueuerX - 1][enqueuerY]);
					treasureCheck = true;
					break;
				}
				
				
				myQueue.enqueue(map[enqueuerX - 1][enqueuerY]);
				map[enqueuerX - 1][enqueuerY].setVisited(true);
			}
			// south
			if ((enqueuerX + 1) < map.length
					&& (map[enqueuerX + 1][enqueuerY].getValue().equals(".")
							|| map[enqueuerX + 1][enqueuerY].getValue().equals("$"))
					&& map[enqueuerX + 1][enqueuerY].getVisited() == false) {
				if (map[enqueuerX + 1][enqueuerY].getValue().equals("$")) {
					myQueue.enqueue(map[enqueuerX + 1][enqueuerY]);
					treasureCheck = true;
					break;
				}
				myQueue.enqueue(map[enqueuerX + 1][enqueuerY]);
				map[enqueuerX + 1][enqueuerY].setVisited(true);
			}

			// east
			if ((enqueuerY + 1) < map[0].length
					&& (map[enqueuerX][enqueuerY + 1].getValue().equals(".")
							|| map[enqueuerX][enqueuerY + 1].getValue().equals("$"))
					&& map[enqueuerX][enqueuerY + 1].getVisited() == false) {
				if (map[enqueuerX][enqueuerY + 1].getValue().equals("$")) {
					myQueue.enqueue(map[enqueuerX][enqueuerY + 1]);
					treasureCheck = true;
					break;
				}
				myQueue.enqueue(map[enqueuerX][enqueuerY + 1]);
				map[enqueuerX][enqueuerY + 1].setVisited(true);
			}

			// west
			if ((enqueuerY - 1) >= 0
					&& (map[enqueuerX][enqueuerY - 1].getValue().equals(".")
							|| map[enqueuerX][enqueuerY - 1].getValue().equals("$"))
					&& map[enqueuerX][enqueuerY - 1].getVisited() == false) {
				if (map[enqueuerX][enqueuerY - 1].getValue().equals("$")) {
					myQueue.enqueue(map[enqueuerX][enqueuerY - 1]);
					treasureCheck = true;
					break;
				}
				myQueue.enqueue(map[enqueuerX][enqueuerY - 1]);
				map[enqueuerX][enqueuerY - 1].setVisited(true);
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