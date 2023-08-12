import java.util.ArrayList;
import java.util.Queue;

public class PathFinder {

	ArrayList<Coordinate> enqueue = new ArrayList<Coordinate>();
	ArrayList<Coordinate> dequeue = new ArrayList<Coordinate>();
	String[][] mapsolve;
	Coordinate wolverineLocation;
	Coordinate treasureLocation;
	int wolverineArrayIndex;
	int treasureArrayIndex;
	int wX, wY;
	int treasureX, treasureY;
	Coordinate enqueuer;
	boolean treasureCheck = false;

	public void pathFind(Coordinate[][] map) {
		// find wol current location
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getType().equals("W")) {
					wolverineLocation = map[i][j]; 
					wX = i;
					wY = j;
					map[i][j].setVisited(true);
				}
			}
		}
		for (int i = 0; i < map.length; i++) { // find the buck
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].getType().equals("$")) {
					treasureLocation = map[i][j]; 
					treasureX = i;
					treasureY = j;
				}
			}
		}

		if (wolverineLocation == null || treasureLocation == null) {
			System.out.println("no W or no $");
		}

		enqueue.add(wolverineLocation);

		while (enqueue.size() > 0) {
			enqueuer = enqueue.get(0);
			dequeue.add(enqueue.remove(0));
			int enqueuerX = enqueuer.getRow(), enqueuerY = enqueuer.getCol();
			//north
			if ((enqueuerX - 1) >= 0
					&& (map[enqueuerX - 1][enqueuerY].getType().equals(".")
							|| map[enqueuerX - 1][enqueuerY].getType().equals("$"))
					&& map[enqueuerX - 1][enqueuerY].getVisited() == false) { // first round of enqueueing
				if (map[enqueuerX - 1][enqueuerY].getType().equals("$")) {
					enqueue.add(map[enqueuerX - 1][enqueuerY]);
					treasureCheck = true;
					break;
				}
				enqueue.add(map[enqueuerX - 1][enqueuerY]);
				map[enqueuerX - 1][enqueuerY].setVisited(true);
			}
			// south
			if ((enqueuerX + 1) < map.length
					&& (map[enqueuerX + 1][enqueuerY].getType().equals(".")
							|| map[enqueuerX + 1][enqueuerY].getType().equals("$"))
					&& map[enqueuerX + 1][enqueuerY].getVisited() == false) {
				if (map[enqueuerX + 1][enqueuerY].getType().equals("$")) {
					enqueue.add(map[enqueuerX + 1][enqueuerY]);
					treasureCheck = true;
					break;
				}
				enqueue.add(map[enqueuerX + 1][enqueuerY]);
				map[enqueuerX + 1][enqueuerY].setVisited(true);
			}

			// east
			if ((enqueuerY + 1) < map[0].length
					&& (map[enqueuerX][enqueuerY + 1].getType().equals(".")
							|| map[enqueuerX][enqueuerY + 1].getType().equals("$"))
					&& map[enqueuerX][enqueuerY + 1].getVisited() == false) {
				if (map[enqueuerX][enqueuerY + 1].getType().equals("$")) {
					enqueue.add(map[enqueuerX][enqueuerY + 1]);
					treasureCheck = true;
					break;
				}
				enqueue.add(map[enqueuerX][enqueuerY + 1]);
				map[enqueuerX][enqueuerY + 1].setVisited(true);
			}

			// west
			if ((enqueuerY - 1) >= 0
					&& (map[enqueuerX][enqueuerY - 1].getType().equals(".")
							|| map[enqueuerX][enqueuerY - 1].getType().equals("$"))
					&& map[enqueuerX][enqueuerY - 1].getVisited() == false) {
				if (map[enqueuerX][enqueuerY - 1].getType().equals("$")) {
					enqueue.add(map[enqueuerX][enqueuerY - 1]);
					treasureCheck = true;
					break;
				}
				enqueue.add(map[enqueuerX][enqueuerY - 1]);
				map[enqueuerX][enqueuerY - 1].setVisited(true);
			}

		}

		Coordinate curr = dequeue.get(dequeue.size() - 1);
		map[dequeue.get(dequeue.size() - 1).getRow()][dequeue.get(dequeue.size() - 1).getCol()].setType("+");
		for (int i = dequeue.size() - 1; i > 0; i--) {
			if ((dequeue.get(i).getRow() == (curr.getRow())) && ((dequeue.get(i).getCol() == (curr.getCol() + 1)))) {
				System.out.print(dequeue.get(i).getType() + " ");
				System.out.print(dequeue.get(i).getRow() + " ");
				System.out.print(dequeue.get(i).getCol());
				System.out.print("\n");
				curr = dequeue.get(i);
				map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setType("+");

			}
			if (dequeue.get(i).getRow() == (curr.getRow()) && (dequeue.get(i).getCol() == (curr.getCol() - 1))) {
				System.out.print(dequeue.get(i).getType() + " ");
				System.out.print(dequeue.get(i).getRow() + " ");
				System.out.print(dequeue.get(i).getCol());
				System.out.print("\n");
				curr = dequeue.get(i);
				map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setType("+");

			}
			if ((dequeue.get(i).getRow() == (curr.getRow() - 1)) && (dequeue.get(i).getCol() == (curr.getCol()))) {
				System.out.print(dequeue.get(i).getType() + " ");
				System.out.print(dequeue.get(i).getRow() + " ");
				System.out.print(dequeue.get(i).getCol());
				System.out.print("\n");
				curr = dequeue.get(i);
				map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setType("+");

			}
			if ((dequeue.get(i).getRow() == (curr.getRow() + 1)) && (dequeue.get(i).getCol() == (curr.getCol()))) {
				System.out.print(dequeue.get(i).getType() + " ");
				System.out.print(dequeue.get(i).getRow() + " ");
				System.out.print(dequeue.get(i).getCol());
				System.out.print("\n");
				curr = dequeue.get(i);
				map[dequeue.get(i).getRow()][dequeue.get(i).getCol()].setType("+");
			}

		}
//need to figure out the above so that it properly prints answer in coord form
		for (int j = 0; j < map.length; j++) {
			System.out.println();
			for (int k = 0; k < map[0].length; k++) {
				System.out.print(map[j][k].getType());
			}
		}

	}

}