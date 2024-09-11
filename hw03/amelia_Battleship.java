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
            System.out.println("Enter ship "+ (i+1)+ " location:");
            if (input.hasNextInt()) {
                player1[i][0] = input.nextInt();
                player1[i][1] = input.nextInt();
            }
        }
        char[][] player1GridMap = new char[5][5];
        for (int[] row : player1)
            System.out.println(Arrays.toString(row));

    }
    private static void printInvalidInputMessage() {
        System.out.println("Invalid input entered. Terminating...");
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
