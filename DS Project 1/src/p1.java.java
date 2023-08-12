import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class p1 {

	public static void main(String[] args) {
		
		//options: --Stack, --Queue
		// then type file name 
		//ex: --Stack testCase4.txt
		String fileName= args[1];
		String typeRun = args[0];
	
		if(!typeRun.equals("--Stack") && !typeRun.equals("--Queue") && !typeRun.equals("--Opt")) {
			IllegalCommandLineInputsException();
			System.exit(0);
		}
		

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
//		System.out.println("Map in coord form: \n");

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

					IncorrectMapFormatException();
				
				}
				
				
				int count = 0;// curr row
				// take in cols and # of rooms
				scanner1.nextLine(); // move scanner to next line
				Coordinate[][] coordsArray = new Coordinate[rows*rooms][cols];

			//	System.out.println(rows + " " + cols + " " + rooms); // print the syntax at top (for example, 4 4 1)

				while (scanner1.hasNextLine()) {

					String line = scanner1.nextLine();
					for (int i = 0; i < cols; i++) {
					//	System.out.println(line.charAt(i) + " " + count + " " + i); //ITS NOT WORKING BECUZ ONCE IN DOUBLE DIGITS OF ROWS, IT WILL NOT GET THE RIGHT CHAR
						coords.add(line.charAt(i) + " " + count + " " + i);

						if(!String.valueOf(line.charAt(i)).equals("@") &&  !String.valueOf(line.charAt(i)).equals(".") &&  !String.valueOf(line.charAt(i)).equals("W") &&  !String.valueOf(line.charAt(i)).equals("$") &&  !String.valueOf(line.charAt(i)).equals("|")               ) {
							IllegalMapCharacterException();
							System.exit(0);
						}
						
					}
					count++;

					if (line.length() == 0) {// no more lines
						break;
					}

				}
					if (count != rows * rooms) {
						IncompeleteMapException(); //check coords fits

					}
				
				
				int counter = 0;

				for (int i = 0; i < rows*rooms; i++) {
					for (int j = 0; j < cols; j++) {
						coordsArray[i][j] = new Coordinate(coords, counter);
						counter++;
					}
				}
				
				
				long startTime = System.currentTimeMillis();

				
				if (typeRun.equals("--Stack")) {
				PathFinderStack resStack = new PathFinderStack();
				resStack.pathFind(coordsArray, rows, cols, rooms);
				
				}else if (typeRun.equals("--Queue")) {
				
				PathFinderQueue resQueue = new PathFinderQueue();
				resQueue.pathFind(coordsArray, rows, cols, rooms);
				} else if (typeRun.equals("--Opt")) {
					PathFinderOpt resOpt = new PathFinderOpt();
					resOpt.pathFind(coordsArray, rows, cols, rooms);
				}
				
				
				long endTime = System.currentTimeMillis() - startTime;
				
				System.out.println("\n"+ "Runetime in milliseconds: " + endTime);
				
				
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
					IncorrectMapFormatException(); //files that don't start with a pair of positive numbers as the first line

				
				}
				Coordinate[][] coordsArray = new Coordinate[rows*rooms][cols];

		//		System.out.println(rows + " " + cols + " " + rooms); // print the syntax at top (for example, 4 4 1)
				scanner2.nextLine();
int count = 0;
				while (scanner2.hasNextLine()) {
					String line = scanner2.nextLine();
					
					coords.add(line.charAt(0) + " " + line.charAt(2) + " " + line.charAt(4)); //WHAT IF charAT 2 AND 4 IS NOT THE ROW OR WHATEVER? WHAT IF ROWS ARE IN DOUBLE DIGITS AND MISPLACES EVEYRHTING?
					
					if(!String.valueOf(line.charAt(0)).equals("@") &&  !String.valueOf(line.charAt(0)).equals(".") &&  !String.valueOf(line.charAt(0)).equals("W") &&  !String.valueOf(line.charAt(0)).equals("$") &&  !String.valueOf(line.charAt(0)).equals("|")               ) {
						IllegalMapCharacterException();
						System.exit(0);
					}

			//		System.out.println(line);
					count++;

				}
				
				if (count != rows*rooms) {
					IncompeleteMapException(); //check coords fits

				}

				int counter = 0;
				
					for (int i = 0; i < rows*rooms; i++) {
						for (int j = 0; j < cols; j++) {
							coordsArray[i][j] = new Coordinate(coords, counter);
							counter++;
						}
					
				}
					
					

					long startTime = System.currentTimeMillis();

					

					if (typeRun.equals("--Stack")) {
					PathFinderStack resStack = new PathFinderStack();
					resStack.pathFind(coordsArray, rows, cols, rooms);
					
					}else if (typeRun.equals("--Queue")) {
					
					PathFinderQueue resQueue = new PathFinderQueue();
					resQueue.pathFind(coordsArray, rows, cols, rooms);
					}else if (typeRun.equals("--Opt")) {
						PathFinderOpt resOpt = new PathFinderOpt();
						resOpt.pathFind(coordsArray, rows, cols, rooms);
					}

						
				
					long endTime = System.currentTimeMillis() - startTime;
					
					System.out.println("\n"+ "Runetime in milliseconds: " + endTime);
					
					
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private static void IncorrectMapFormatException() {
		// TODO Auto-generated method stub
        System.out.println("IncorrectMapFormatException");

	}

	private static void IncompeleteMapException() {
		// TODO Auto-generated method stub
        System.out.println("IncompeleteMapException");

	}

	private static void IllegalMapCharacterException() {
		// TODO Auto-generated method stub
        System.out.println("IllegalMapCharacterException");

	}

	private static void IllegalCommandLineInputsException() {
		// TODO Auto-generated method stub
        System.out.println("IllegalCommandLineInputsException");

	}
}