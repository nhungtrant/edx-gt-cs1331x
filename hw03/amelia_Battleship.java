// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.Scanner;
//import java.util.Arrays; //remove?

class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");
        Scanner input = new Scanner(System.in); //Initialize scanner object
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
        
        int[][] player1Matrix = createMatrix(5);
        int[][] player2Matrix = createMatrix(5);
        
        int[][] player1_Position =ShipPositionCollector(input,player1Matrix);
        char[][] player1GridMap = ShipGridMap(player1_Position);
        printBattleShip(player1GridMap);
        
        System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
        int[][] player2_Position=ShipPositionCollector(input, player2Matrix);
        char[][] player2GridMap = ShipGridMap(player2_Position);
        printBattleShip(player2GridMap);
        
        int[][] player1_TagetHistory = new int[1][2];
        int[][] player2_TagetHistory = new int[1][2];
        boolean destroy = false;
        int ship1Destroyed = 0;
        int ship2Destroyed = 0;
        while (!destroy) {
            player1_TagetHistory = TargetCollector(input, player1_TagetHistory, 1);
            // for (int[] row : player1_TagetHistory)
            //     System.out.println(Arrays.toString(row));
            ship1Destroyed = CheckHitTarget(ship1Destroyed, player1_TagetHistory, player2_Position, 1, 2);
            char[][] player1_Target_GridMap = TargetGridMap(player1_TagetHistory, player2_Position);
            printBattleShip(player1_Target_GridMap);
            
            player2_TagetHistory = TargetCollector(input, player2_TagetHistory, 2);
            ship2Destroyed = CheckHitTarget(ship2Destroyed, player2_TagetHistory, player1_Position, 2, 1);
            char[][] player2_Target_GridMap = TargetGridMap(player2_TagetHistory, player1_Position);
            printBattleShip(player2_Target_GridMap);
            
            
            if (ship1Destroyed == 5) {
                System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENTS SHIPS!");  
                destroy = true;
            }
            
            if (ship2Destroyed == 5) {
                System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENTS SHIPS!");  
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
        // System.out.println("Target history: ");
        // for (int[] row : TargetHistory)
        //     System.out.println(Arrays.toString(row));
        // System.out.println("Ship Position: ");
        // for (int[] row : shipPosition)
        //     System.out.println(Arrays.toString(row));
        for (int row_i=1; row_i<TargetHistory.length; row_i++) {
            for (int col_i=0; col_i<TargetHistory[row_i].length; col_i++) {
                playerGridMap[TargetHistory[row_i][0]][TargetHistory[row_i][1]] = 'O';
                for (int[] ship : shipPosition) {
                    if (TargetHistory[row_i][0]==ship[0] && TargetHistory[row_i][0]==ship[1]) {
                        playerGridMap[TargetHistory[row_i][0]][TargetHistory[row_i][1]] = 'X';
                        break;
                        
                    }
                }
            }  
        }
        return playerGridMap;
    }
    
    private static int CheckHitTarget(int shipDestroyed, int[][] player_TagetHistory, int[][] ShipPosition, int num_shot, int numGotShot)  {
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
            System.out.println("PLAYER "+ num_shot+ " HIT PLAYER "+ numGotShot+ "s SHIP!");
        }
        else 
            System.out.println("PLAYER "+ num_shot+ " MISSED!");
            
        return shipDestroyed;
    }
    
    private static int[][] TargetCollector(Scanner input, int[][] TargetHistory, int num) {
        boolean check = false;
        int[][] updatedTargetHistory = new int [TargetHistory.length + 1][2];
        while (!check) {
            System.out.println("Player "+ num+ " enter hit row/column:");
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
        if ((row==-1)||(col==-1)){
            System.out.println("Invalid coordinates. Choose different coordinates.");
            return false;
            }
        if (TargetHistory.length < 2) {
            return true;
        }
        else {
            for (int row_i=1; row_i<TargetHistory.length; row_i++) {
                for (int col_i=0; col_i<TargetHistory[row_i].length; col_i++) {
                    if ((row == TargetHistory[row_i][0]) && (col == TargetHistory[row_i][1])){
                        System.out.println("You already fired on this spot. Choose different coordinates.");
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
                if (row!=-1 && col!=-1)
                {
                    matrix[i][0] = row;
                    matrix[i][1] = col;
                }
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
        return -1;
    }
    
    private static boolean checkDuplicate(int row, int col, int[][] matrix_ships) {
        if ((row==-1)||(col==-1)){
                System.out.println("Invalid coordinates. Choose different coordinates.");
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
