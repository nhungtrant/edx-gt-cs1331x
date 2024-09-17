// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.Scanner;
import java.util.Arrays; //remove?

class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
        Scanner input = new Scanner(System.in); //Initialize scanner object
        int[][] player1 = new int[5][2];
        for (int i = 0; i < 5; i++ ) {
            boolean check = false;
            while (!check) {
                // System.out.println("check1 : "+ check);
                System.out.println("Enter ship "+ (i+1)+ " location:");
                int row = checkValidInput(input);
                int col = checkValidInput(input);
                if ((row==-1)||(col==-1)) {
                    System.out.println("fick: ");
                }
                // System.out.println("check2: "+ check);
                check = checkDuplicate(row,col,player1);
                player1[i][0] = row;
                player1[i][1] = col;
            }
        }
        char[][] player1GridMap = new char[5][5];
        for (int[] line : player1)
            System.out.println(Arrays.toString(line));
    }
    private static void printInvalidInputMessage() {
        System.out.println("Invalid input entered. Terminating...");
    }
    
    private static int checkValidInput(Scanner input) {
        if (input.hasNextInt()) {
            int num = input.nextInt();
            if (num>0 && num<6) {
                return num;
            }
        }
        // input.nextLine();
        System.out.println("Invalid coordinates. Choose different coordinates.");
        input.nextLine();
        return -1;
    }
    
    private static boolean checkDuplicate(int row, int col, int[][] matrix_ships) {
        for (int[] ship: matrix_ships){
            if ((row == ship[0]) && (col == ship[1])){
                System.out.println("Match ship");
                return false;
            }
        }
        return true;
    }

    // Use this method to print game boards to the console.
	private static void printBattleShip(int[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
}
