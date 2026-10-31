1
import advcalc.Ext;
import advcalc.Matrix;
import advcalc.Trig;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calc calc = new Calc();

        try (Scanner scanner = new Scanner(System.in)) {
            try {
                showMainMenu(scanner, calc);
            } catch (NoSuchElementException e) {
                // End input cleanly when the input stream closes.
            }
        }

        System.out.println("Goodbye.");
    }

    private static void showMainMenu(Scanner scanner, Calc calc) {
        boolean running = true;
        while (running) {
            System.out.println("\nSelect a calculator:");
            System.out.println("1. Basic Calculator");
            System.out.println("2. Trigonometry");
            System.out.println("3. Advanced Math");
            System.out.println("4. Matrix Operations");
            System.out.println("0. Exit");
            int choice = readInt(scanner, "Choice: ");

            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    showBasicMenu(scanner, calc);
                    break;
                case 2:
                    showTrigonometryMenu(scanner);
                    break;
                case 3:
                    showAdvancedMenu(scanner);
                    break;
                case 4:
                    showMatrixMenu(scanner);
                    break;
                default:
                    System.out.println("Choose a calculator from 0 to 4.");
            }
        }
    }

    private static void showBasicMenu(Scanner scanner, Calc calc) {
        while (true) {
            System.out.println("\nBasic Calculator:");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("0. Back");
            int choice = readInt(scanner, "Choice: ");
            if (choice == 0) {
                return;
            }

            try {
                double first;
                double second;
                switch (choice) {
                    case 1:
                        first = readDouble(scanner, "First number: ");
                        second = readDouble(scanner, "Second number: ");
                        System.out.println("Result: " + calc.add(first, second));
                        break;
                    case 2:
                        first = readDouble(scanner, "First number: ");
                        second = readDouble(scanner, "Second number: ");
                        System.out.println("Result: " + calc.subtract(first, second));
                        break;
                    case 3:
                        first = readDouble(scanner, "First number: ");
                        second = readDouble(scanner, "Second number: ");
                        System.out.println("Result: " + calc.multiply(first, second));
                        break;
                    case 4:
                        first = readDouble(scanner, "First number: ");
                        second = readDouble(scanner, "Second number: ");
                        System.out.println("Result: " + calc.divide(first, second));
                        break;
                    default:
                        System.out.println("Choose an operation from 0 to 4.");
                }
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void showTrigonometryMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nTrigonometry (angles in degrees):");
            System.out.println("1. Sine");
            System.out.println("2. Cosine");
            System.out.println("3. Tangent");
            System.out.println("0. Back");
            int choice = readInt(scanner, "Choice: ");
            if (choice == 0) {
                return;
            }

            double angle;
            switch (choice) {
                case 1:
                    angle = readDouble(scanner, "Angle in degrees: ");
                    System.out.println("Result: " + Trig.sin(angle));
                    break;
                case 2:
                    angle = readDouble(scanner, "Angle in degrees: ");
                    System.out.println("Result: " + Trig.cos(angle));
                    break;
                case 3:
                    angle = readDouble(scanner, "Angle in degrees: ");
                    System.out.println("Result: " + Trig.tan(angle));
                    break;
                default:
                    System.out.println("Choose an operation from 0 to 3.");
            }
        }
    }

    private static void showAdvancedMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdvanced Math:");
            System.out.println("1. Power");
            System.out.println("2. Natural logarithm");
            System.out.println("3. Factorial");
            System.out.println("0. Back");
            int choice = readInt(scanner, "Choice: ");

            try {
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Result: " + Ext.power(readDouble(scanner, "Base: "),
                                readDouble(scanner, "Exponent: ")));
                        break;
                    case 2:
                        System.out.println("Result: " + Ext.log(readDouble(scanner, "Value: ")));
                        break;
                    case 3:
                        System.out.println("Result: " + Ext.factorial(readInt(scanner, "Non-negative integer: ")));
                        break;
                    default:
                        System.out.println("Choose an operation from 0 to 3.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void showMatrixMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nMatrix Operations:");
            System.out.println("1. Add matrices");
            System.out.println("0. Back");
            int choice = readInt(scanner, "Choice: ");
            switch (choice) {
                case 0:
                    return;
                case 1:
                    try {
                        addMatrices(scanner);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Choose an operation from 0 to 1.");
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            if (!scanner.hasNext()) {
                throw new NoSuchElementException("Input ended.");
            }
            System.out.println("Please enter a valid integer.");
            scanner.next();
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            if (!scanner.hasNext()) {
                throw new NoSuchElementException("Input ended.");
            }
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
    }

    private static void addMatrices(Scanner scanner) {
        int rows = readPositiveInt(scanner, "Number of rows: ");
        int columns = readPositiveInt(scanner, "Number of columns: ");
        Matrix first = readMatrix(scanner, "first", rows, columns);
        Matrix second = readMatrix(scanner, "second", rows, columns);

        System.out.println("Result:");
        Matrix.add(first, second).display();
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            int value = readInt(scanner, prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Enter a value greater than zero.");
        }
    }

    private static Matrix readMatrix(Scanner scanner, String name, int rows, int columns) {
        double[][] values = new double[rows][columns];
        System.out.println("Enter values for the " + name + " matrix:");
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                values[row][column] = readDouble(scanner,
                        "Element [" + (row + 1) + "][" + (column + 1) + "]: ");
            }
        }
        return new Matrix(values);
    }
}
