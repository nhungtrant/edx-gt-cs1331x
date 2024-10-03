// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.Scanner;

class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
        Scanner input = new Scanner(System.in); //Initialize scanner object
        
        int[][] player1_Position = ShipPositionCollector(input);
        char[][] player1GridMap = ShipGridMap(player1_Position);
        printBattleShip(player1GridMap);
        
        
        boolean destroy = false;
        while (!destroy) {
            int shipDestroyed = 0;
            int[][] player = new int[25][2];
                    
            int[] player1_TagetHistory = TargetCollector(input, player1_Position);
            
            if (shipDestroyed == 5) {
                destroy = true;
            }
        }
        
        char[][] player2GridMap = new char[5][5];
    }
    
    private static int CheckHitTarget(int shipDestroyed, int[][] player_TagetHistory, int[][] ShipPosition)  {
        for (int[] ship : ShipPosition) {
            if ((ship[0] == player_TagetHistory[-1][0]) && (ship[1] == player_TagetHistory[-1][1])) {
                System.out.println("Hit!");
                shipDestroyed++;
            }
            else
                System.out.println("Miss :(");
        }
        return shipDestroyed;
    }
    
    private static int[] TargetCollector(Scanner input, int[][] TargetHistory) {
        boolean check = false;
        int[] cordinate = null;
        while (!check) {
            System.out.println("Player 1, enter hit row/column:");
            int row = checkValidInput(input);
            int col = checkValidInput(input);
            check = checkDuplicateTarget(row,col,TargetHistory);
            System.out.println("check: "+ check);
            cordinate = new int[]{row - 1, col - 1};
            // for (int[] ship: TargetHistory){
            //     if ((ship[0]-1 == ) && (col == ship[1]+1)){
            //         System.out.println("You already have a ship there. Choose different coordinates.");
            //         return false;
            //     }
            // }
        }
        return cordinate;
    }
    
    private static boolean checkDuplicateTarget(int row, int col, int[][] TargetHistory) {
        for (int[] ship: TargetHistory){
            if ((row == ship[0]+1) && (col == ship[1]+1)){
                System.out.println("You already have a ship there. Choose different coordinates.");
                return false;
            }
        }
        return true;
    }
    
    private static int[][] ShipPositionCollector(Scanner input) {
        int[][] player = new int[5][2];
        for (int i = 0; i < 5; i++ ) {
            boolean check = false;
            while (!check) {
                System.out.println("Enter ship "+ (i+1)+ " location:");
                int row = checkValidInput(input);
                int col = checkValidInput(input);
                check = checkDuplicate(row,col,player);
                player[i][0] = row - 1;
                player[i][1] = col - 1;
            }
        }
        return player;
    }
    
    private static char[][] ShipGridMap(int[][] ShipPosition) {
        char[][] playerGridMap = new char[5][5];
        
        for (char[] map : playerGridMap) {
            for (char item : map) {
                item = '-';
            }
        }
        for (int[] ship : ShipPosition) {
            playerGridMap[ship[0]][ship[1]] = '@';
        }
        return playerGridMap;
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
        input.nextLine();
        System.out.println("Invalid coordinates. Choose different coordinates.");
        return -1;
    }
    
    private static boolean checkDuplicate(int row, int col, int[][] matrix_ships) {
        if ((row==-1)||(col==-1)){
                return false;
            }
        for (int[] ship: matrix_ships){
            if ((row == ship[0]) && (col == ship[1])){
                System.out.println("You already have a ship there. Choose different coordinates.");
                return false;
            }
        }
        return true;
    }

    // Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
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
