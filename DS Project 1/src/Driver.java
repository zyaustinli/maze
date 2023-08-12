
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	// Coordinate[][] coordsArray3;

	public static void main(String[] args) {
		
		
		String fileName= args[0];
	
		//COULDNT GET ARGS TO WORK FOR RUNNING STACK OR QUEUE, USE LINE 89 AND 145 AND JUST CHANGE METHODS ACCORDINGLY
		

		Scanner scanner;
		
		File f = new File(fileName);
		int type = 100;
		try {
			scanner = new Scanner(f);
			scanner.nextLine();
			String line = scanner.nextLine();
			if (Character.getNumericValue(line.charAt(2)) == 0) {
				type = 0; // coord system
			} else {
				type = 1; // reg
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		if (type == 0) {
			System.out.println("Input is in coord system");
		} else if (type == 1) {
			System.out.println("Input is in map-based system");
		}
		System.out.println("Map in coord form: \n");

		if (type == 1) {// reg form, must convert to coord system and then add to coordsArray to find
						// path
			Scanner scanner1;
			ArrayList<String> coords = new ArrayList<String>();

			try {
				// code that might throw a special error
				scanner1 = new Scanner(f);
				int rows = scanner1.nextInt();
				int cols = scanner1.nextInt();
				int rooms = scanner1.nextInt();
				if (rows< 0 || cols < 0 || rooms < 0) { //files that don't start with a pair of positive numbers as the first line

					System.out.println("input error");
				
				}
				
				
				int count = 0;// curr row
				// take in cols and # of rooms
				scanner1.nextLine(); // move scanner to next line
				Coordinate[][] coordsArray = new Coordinate[rows*rooms][cols];

				System.out.println(rows + " " + cols + " " + rooms); // print the syntax at top (for example, 4 4 1)

				while (scanner1.hasNextLine()) {

					String line = scanner1.nextLine();
					for (int i = 0; i < cols; i++) {
						System.out.println(line.charAt(i) + " " + count + " " + i);
						coords.add(line.charAt(i) + " " + count + " " + i);
						
					}
					count++;

					if (line.length() == 0) {// no more lines
						break;
					}

				}
					if (count != rows * rooms) {
						System.out.println("input error"); //check coords fits

					}
				
				
				int counter = 0;

				for (int i = 0; i < rows*rooms; i++) {
					for (int j = 0; j < cols; j++) {
						coordsArray[i][j] = new Coordinate(coords, counter);
						counter++;
					}
				}
				
				
				long startTime = System.currentTimeMillis();

				
				
				//PathFinderStack resStack = new PathFinderStack();
				//resStack.pathFind(coordsArray, rows, cols, rooms);
				
				PathFinderQueue resQueue = new PathFinderQueue();
				resQueue.pathFind(coordsArray, rows, cols, rooms);

				
				
				long endTime = System.currentTimeMillis() - startTime;
				
				System.out.println("\n"+ "Runetime: " + endTime);
				
				
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		if (type == 0) {// if in coord form, then can just add each row to coordArray2

			Scanner scanner2;
			ArrayList<String> coords = new ArrayList<String>();

			try {
				scanner2 = new Scanner(f);
				int rows = scanner2.nextInt();
				int cols = scanner2.nextInt();
				int rooms = scanner2.nextInt();
				if (rows< 0 || cols < 0 || rooms < 0) {
					System.out.println("input error"); //files that don't start with a pair of positive numbers as the first line

				
				}
				Coordinate[][] coordsArray = new Coordinate[rows*rooms][cols];

				System.out.println(rows + " " + cols + " " + rooms); // print the syntax at top (for example, 4 4 1)
				scanner2.nextLine();
int count = 0;
				while (scanner2.hasNextLine()) {
					String line = scanner2.nextLine();
					
					coords.add(line.charAt(0) + " " + line.charAt(2) + " " + line.charAt(4));

					System.out.println(line);
					count++;

				}
				
				if (count != rows*rooms) {
					System.out.println("input error"); //check coords fits

				}

				int counter = 0;
				
					for (int i = 0; i < rows*rooms; i++) {
						for (int j = 0; j < cols; j++) {
							coordsArray[i][j] = new Coordinate(coords, counter);
							counter++;
						}
					
				}
					
					

					long startTime = System.currentTimeMillis();

					
					
						//PathFinderStack resStack = new PathFinderStack();
						//resStack.pathFind(coordsArray, rows, cols, rooms);
						
						PathFinderQueue resQueue = new PathFinderQueue();
						resQueue.pathFind(coordsArray, rows, cols, rooms);

						
				
					long endTime = System.currentTimeMillis() - startTime;
					
					System.out.println("\n"+ "Runetime: " + endTime);
					
					
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}
}