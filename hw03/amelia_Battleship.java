// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.Scanner;
import java.util.Arrays; //remove?

class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
        Scanner input = new Scanner(System.in); //Initialize scanner object
        
        int[][] player_matrix = createMatrix(5);
        int[][] player1_Position = ShipPositionCollector(input, player_matrix);
        char[][] player1GridMap = ShipGridMap(player1_Position);
        printBattleShip(player1GridMap);
        
        int[][] player1_TagetHistory = new int[1][2];
        boolean destroy = false;
        int shipDestroyed = 0;
        while (!destroy) {
            System.out.println("Start collecting");        
            player1_TagetHistory = TargetCollector(input, player1_TagetHistory);
            
            // for (int[] row : player1_TagetHistory)
            //     System.out.println(Arrays.toString(row));
            
            shipDestroyed = CheckHitTarget(shipDestroyed, player1_TagetHistory, player1_Position);
            char[][] player1_Target_GridMap = TargetGridMap(player1_TagetHistory, player1_Position);
            printBattleShip(player1_Target_GridMap);
            if (shipDestroyed == 5) {
                System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENTS SHIPS!");  
                destroy = true;
            }
        }
    }
    
    private static char[][] TargetGridMap(int[][] TargetHistory, int[][] shipPosition) {
        char[][] playerGridMap = new char[5][5];
        for (char[] map : playerGridMap) {
            for (char item : map) {
                item = '-';
            }
        }
        
        for (int row_i=1; row_i<TargetHistory.length; row_i++) {
            for (int col_i=0; col_i<TargetHistory[row_i].length; col_i++) {
                playerGridMap[TargetHistory[row_i][0]][TargetHistory[row_i][1]] = 'O';
                for (int[] ship : shipPosition) {
                    if (TargetHistory[row_i][0]==ship[0] && TargetHistory[row_i][0]==ship[1]) {
                        playerGridMap[TargetHistory[row_i][0]][TargetHistory[row_i][1]] = 'X';
                        
                    }
                }
            }  
        }
        return playerGridMap;
    }
    
    private static int CheckHitTarget(int shipDestroyed, int[][] player_TagetHistory, int[][] ShipPosition)  {
        int row = player_TagetHistory[player_TagetHistory.length - 1][0];
        int col = player_TagetHistory[player_TagetHistory.length - 1][1];
        int hit = 0;
        for (int[] ship : ShipPosition) {
            if ((ship[0] == row) && (ship[1] == col)) {
                hit = 1;
                shipDestroyed++;
            }
        }
        if (hit==1) {
            System.out.println("PLAYER 1 HIT PLAYER 2s SHIP!");
        }
        else 
            System.out.println("PLAYER 2 MISSED!");
            
        return shipDestroyed;
    }
    
    private static int[][] TargetCollector(Scanner input, int[][] TargetHistory) {
        boolean check = false;
        int[][] updatedTargetHistory = new int [TargetHistory.length + 1][2];
        while (!check) {
            System.out.println("Player 1, enter hit row/column:");
            int row = checkValidInput(input);
            int col = checkValidInput(input);
            check = checkTargetDuplicate(row,col,TargetHistory);
            if (row!= -1 && col!= -1) {
                updatedTargetHistory[updatedTargetHistory.length-1][0] = row;
                updatedTargetHistory[updatedTargetHistory.length-1][1] = col;
            }
        }
        for (int row=0; row<TargetHistory.length; row++) {
            for (int col=0; col<TargetHistory[row].length; col++) {
                updatedTargetHistory[row][col] = TargetHistory[row][col];
            }
        }
        return updatedTargetHistory;
    }
    
    private static boolean checkTargetDuplicate(int row, int col, int[][] TargetHistory) {
        if (TargetHistory.length < 2) {
            return true;
        }
        else {
            for (int row_i=1; row_i<TargetHistory.length; row_i++) {
                for (int col_i=0; col_i<TargetHistory[row_i].length; col_i++) {
                    if ((row == TargetHistory[row_i][0]) && (col == TargetHistory[row_i][1])){
                        System.out.println("You already have a ship there. Choose different coordinates.");
                        return false;
                    }   
                }
            }
            return true;
        }
    }
    
    private static int[][] createMatrix(int numRow) {
        int[][] matrix = new int[numRow][2];
        for (int[] row : matrix) {
            row[0] = -1;
            row[1] = -1;
        }
        return matrix;
    }
    
    private static int[][] ShipPositionCollector(Scanner input, int[][] matrix) {
        for (int i = 0; i < 5; i++ ) {
            boolean check = false;
            while (!check) {
                System.out.println("Enter ship "+ (i+1)+ " location:");
                int row = checkValidInput(input);
                int col = checkValidInput(input);
                check = checkDuplicate(row,col,matrix);
                matrix[i][0] = row;
                matrix[i][1] = col;
            }
        }
        return matrix;
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
            if (num>-1 && num<5) {
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
