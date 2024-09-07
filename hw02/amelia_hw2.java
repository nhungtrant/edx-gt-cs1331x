import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("List of operations: add subtract multiply divide alphabetize");
        System.out.println("Enter an operation:");
        Scanner input = new Scanner(System.in);
        String operation = input.next();
        switch (operation.toLowerCase()) {
            case "add":
                System.out.println("Enter two integer: ");
                Scanner integers_add = new Scanner(System.in);
                int add_first = integers_add.nextInt();
                int add_second = integers_add.nextInt();
                System.out.println("Answer: " + (add_first + add_second));
                break;
            case "subtract":
                System.out.println("Enter two integer: ");
                Scanner integers_subtract = new Scanner(System.in);
                int sub_first = integers_subtract.nextInt();
                int sub_second = integers_subtract.nextInt();
                System.out.println("Answer: " + (sub_first - sub_second));
                break;
            case "multiply":
                System.out.println("Enter two doubles: ");
                Scanner multi = new Scanner(System.in);
                double multi_first = multi.nextDouble();
                double multi_second = multi.nextDouble();
                double result = multi_first * multi_second;
                System.out.printf("Answer: "+"result: %.2d\n", result);
                break;
            case "divide":
                System.out.println("Enter two doubles: ");
                Scanner divide = new Scanner(System.in);
                double divide_first = divide.nextDouble();
                double divide_second = divide.nextDouble();
                if (divide_second != 0) {
                    System.out.println("Answer: " + (divide_first/divide_second));
                }
                else
                    System.out.println("Invalid input entered. Terminating...");
                break;
            case "alphabetize":
                System.out.println("Enter two words: ");
                Scanner alpha = new Scanner(System.in);
                String first = alpha.next();
                String second = alpha.next();
                int compare = first.compareTo(second);
                if (compare == 0) {
                    System.out.println("Answer: Chicken or Egg.");
                }
                else if (compare > 1) {
                    System.out.println(first + " comes before "+second+" alphabetically.");
                }
                else
                    System.out.println(second + " comes before "+first+" alphabetically.");

                break;
            default:
                System.out.println("Invalid input entered. Terminating...");
        }
    }
}
