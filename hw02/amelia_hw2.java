import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("List of operations: add subtract multiply divide alphabetize");
        System.out.println("Enter an operation:");
        Scanner input = new Scanner(System.in); //Initialize scanner object
        String operation = input.next();
        
        switch (operation.toLowerCase()) {
            case "add":
                System.out.println("Enter two integer: ");
                // Scanner object only need to initialize once
                // Scanner integers_add = new Scanner(System.in);
                checkValidInput(input, "int");
                int add_first = input.nextInt();
                checkValidInput(input, "int");
                int add_second = input.nextInt();
                System.out.println("Answer: " + (add_first + add_second));
                break;
            case "subtract":
                System.out.println("Enter two integer: ");
                // Scanner integers_subtract = new Scanner(System.in);
                checkValidInput(input, "int");
                int sub_first = input.nextInt();
                checkValidInput(input, "int");
                int sub_second = input.nextInt();
                System.out.println("Answer: " + (sub_first - sub_second));
                break;
            case "multiply":
                System.out.println("Enter two doubles: ");
                // Scanner multi = new Scanner(System.in);
                checkValidInput(input, "double");
                double multi_first = input.nextDouble();
                checkValidInput(input, "double");
                double multi_second = input.nextDouble();
                System.out.printf("Answer: %.2f\n", multi_first * multi_second);
                break;
            case "divide":
                System.out.println("Enter two doubles: ");
                // Scanner divide = new Scanner(System.in);
                double divide_first = input.nextDouble();
                double divide_second = input.nextDouble();
                if (divide_second != 0) {
                    System.out.printf("Answer:  %.2f\n", (divide_first/divide_second));
                }
                else
                    printInvalidInputMessage();
                break;
            case "alphabetize":
                System.out.println("Enter two words: ");
                String first = input.next();
                String second = input.next();
                int compare = first.compareToIgnoreCase(second);
                if (compare == 0) {
                    System.out.println("Answer: Chicken or Egg.");
                }
                else if (compare > 0) {
                    System.out.println("Answer: "+first + " comes before "+second+" alphabetically.");
                }
                else
                    System.out.println("Answer: "+second + " comes before "+first+" alphabetically.");

                break;
            default:
                printInvalidInputMessage();
        }
        input.close(); //close the scanner
    }
    //Method for checking input type
    private static void printInvalidInputMessage() {
        System.out.println("Invalid input entered. Terminating...");
    }
    
    private static void checkValidInput(Scanner input, String dataType) {
        switch(dataType) {
            case "int":
                if (!input.hasNextInt()) {
                    printInvalidInputMessage();
                }
                break;
            case "double":
                if (!input.hasNextDouble()) {
                    printInvalidInputMessage();
                }
                break;
            default:
                break;
        }
    }
}
