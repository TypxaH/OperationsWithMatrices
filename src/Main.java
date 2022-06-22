import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static double[][] matrixA, matrixB, result;
    private static int getColumns() {
        System.out.print("Enter number of the columns of the matrix: ");
        return scanner.nextInt();
    }
    private static int getRows() {
        System.out.print("Enter number of the rows of the matrix: ");
        return scanner.nextInt();
    }
    public static double[][] inputMatrix(int rowsNumber, int colsNumber) {
        double[][] matrix = new double[rowsNumber][colsNumber];
        for (int i = 0; i < rowsNumber; i++) {
            System.out.printf("\nEnter the elements of the matrix in row %d!\n", i + 1);
            for (int j = 0; j < colsNumber; j++) {
                System.out.printf(" A[%d][%d] = \r", i + 1, j + 1);
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }
    public static double[][] inputMatrix(int rowsNumber, int colsNumber, char nameOfMatrix) {
        double[][] matrix = new double[rowsNumber][colsNumber];
        for (int i = 0; i < rowsNumber; i++) {
            System.out.printf("\nEnter the elements of the matrix %c in row %d!\n",nameOfMatrix, i + 1);
            for (int j = 0; j < colsNumber; j++) {
                System.out.printf(" %c[%d][%d] = \r",nameOfMatrix, i + 1, j + 1);
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }
    public static double[][] multiplyMatrices(double[][] matrixOne, double[][] matrixTwo) {
        if (matrixOne[0].length != matrixTwo.length) {
            return null;
        }
        double[][] resultMatrix = new double[matrixOne.length][matrixTwo[0].length];
        for (int rowsOne = 0; rowsOne < matrixOne.length; rowsOne++) {
            for (int colsTwo = 0; colsTwo < matrixTwo[0].length; colsTwo++) {
                double cellSum = 0;
                for (int colsOne = 0; colsOne < matrixOne[0].length; colsOne++) {
                    cellSum += matrixOne[rowsOne][colsOne] * matrixTwo[colsOne][colsTwo];
                }
                resultMatrix[rowsOne][colsTwo] = cellSum;
            }
        }
        return resultMatrix;

    }

    public static double[][] subtractMatrices(double[][] matrixOne, double[][] matrixTwo) {
        double[][] resultMatrix = new double[matrixOne.length][matrixOne[0].length];
        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                resultMatrix[i][j] = matrixOne[i][j] - matrixTwo[i][j];
            }
        }

        return resultMatrix;
    }

    public static double[][] addMatrices(double[][] matrixOne, double[][] matrixTwo) {
        double[][] resultMatrix = new double[matrixOne.length][matrixOne[0].length];
        for (int i = 0; i < matrixOne.length; i++) {
            for (int j = 0; j < matrixOne[0].length; j++) {
                resultMatrix[i][j] = matrixOne[i][j] + matrixTwo[i][j];
            }
        }

        return resultMatrix;
    }

    public static double[][] getCofactor(double[][] matrix, int row, int col) {
        double[][] tempMatrix = new double[matrix.length - 1][matrix[0].length - 1];
        int nextR = 0, nextC = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == col) {
                    continue;
                }
                tempMatrix[nextR][nextC] = matrix[i][j];
                nextC++;
            }
            nextC = 0;
            nextR++;
        }

        return tempMatrix;
    }

    public static void printMatrix(double[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (Math.round(matrix[row][column]) == matrix[row][column]) {
                    System.out.printf("% -8d", Math.round(matrix[row][column]));
                } else {
                    System.out.printf("% -8.2f", matrix[row][column]);
                }
            }
            System.out.println();
        }
    }

    public static double[][] getInverseMatrix(double[][] matrix) {
        double determinant = calculateDeterminant(matrix);
        if (determinant == 0.0) {
            return null;
        }
        double[][] inverseMatrix = new double[matrix.length][matrix.length];
        int sign;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sign = (i + j) % 2 == 0 ? 1 : -1;
                inverseMatrix[j][i] = sign * calculateDeterminant(getCofactor(matrix, i, j)) / determinant;
            }
        }
        return inverseMatrix;
    }
    //Детерминантата се изчислява чрез развиване на първия ред на матрицата. Използва се рекурсия по минорните матрици.
    public static double calculateDeterminant(double[][] matrix) {
        double sum = 0;
        int sign = 1;
        if (matrix.length == 1) {
            return matrix[0][0];
        } //else if (matrix.length == 2) {
        //    return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        //}
        for (int col = 0; col < matrix[0].length; col++) {
            sum += sign * matrix[0][col] * calculateDeterminant(getCofactor(matrix, 0, col));
            sign *= -1;
        }
        return sum;
    }

    private static void printMenu() {
        System.out.println("\n----------Operations with matrices----------");
        System.out.println("1. Enter and print the matrix (A) from keyboard;");
        System.out.println("2. Matrix addition (A + B);");
        System.out.println("3. Matrix subtraction (A - B);");
        System.out.println("4. Matrix multiplication (A * B);");
        System.out.println("5. Determinant of a matrix (det(A));");
        System.out.println("6. Inverse of a matrix (A-1);");
        System.out.println("7. Check if a square matrix can be converted to a identity matrix;");
        System.out.println("---------------------------------------------------------");
        System.out.print("Choose an operation (1-7): ");
    }

    public static void main(String[] args) {
        printMenu();       
        double determinant;
        int operation = scanner.nextInt();
        switch (operation) {
            case 1:
                matrixA = inputMatrix(getRows(), getColumns());
                System.out.println("\nThe input matrix is:");
                printMatrix(matrixA);
                break;
            case 2:
                inputTwoMatrices();
                result = addMatrices(matrixA, matrixB);
                System.out.println("\nThe result matrix of the addition is:");
                printMatrix(result);
                break;
            case 3:
                inputTwoMatrices();
                result = subtractMatrices(matrixA, matrixB);
                System.out.println("\nThe result matrix of the subtraction is:");
                printMatrix(result);
                break;
            case 4:
                matrixA = inputMatrix(getRows(), getColumns());
                matrixB = inputMatrix(getRows(), getColumns());

                result = multiplyMatrices(matrixA, matrixB);
                if (result == null) {
                    System.out.println("The number of the column in matrix A must be equal to the number of the rows in matrix B!");
                }else{
                    System.out.println("\nThe result matrix of the multiplication is:");
                    printMatrix(result);
                }
                break;
            case 5:
                System.out.print("Enter number of the rows of a square matrix А: ");
                int n = scanner.nextInt();
                matrixA = inputMatrix(n, n);
                System.out.println("\nThe matrix A is:");
                printMatrix(matrixA);
                System.out.println("\nThe determinant of the matrix is det(A) = " + calculateDeterminant(matrixA));
                break;
            case 6:
                System.out.print("Enter number of the rows of a square matrix: ");
                n = scanner.nextInt();
                matrixA = inputMatrix(n, n);
                System.out.println("\nThe matrix A is:");
                printMatrix(matrixA);
                result = getInverseMatrix(matrixA);
                if (result != null) {
                    System.out.println("\nThe inverse of the matrix A is the matrix:");
                    printMatrix(result);
                }else{
                    System.out.println("\nThe matrix A is NOT invertible.");
                }
                break;

            case 7:
                System.out.print("Enter number of the rows of a square matrix: ");
                n = scanner.nextInt();
                matrixA = inputMatrix(n, n);
                System.out.println("Matrix A is:");
                printMatrix(matrixA);
                determinant = calculateDeterminant(matrixA);
                if (determinant != 0) {
                    System.out.println("\nThe matrix A can be converted to the identity matrix.");
                }
                else{
                    System.out.println("\nThe matrix A can't be converted to the identity matrix.");
                }
                break;
            default:
                System.out.println("Wrong choice!");
        }
        /*
        double[][] array1 = {
                {1, 0, 0},
                {0, 2, 0},
                {0, 0, 1},
        };
        double[][] array2 = {
                {1, 2, 3},
                {4, 5, 7},
                {7, 8, 0},
        };
        System.out.println(calculateDeterminant(array));
        printMatrix(getCofactor(array, 2, 0));
        printMatrix(getInverseMatrix(array1));
        printMatrix(addMatrices(array1,array2));
        printMatrix(multiplyMatrices(array1,array2));
         */
    }

    private static void inputTwoMatrices() {
        matrixA = inputMatrix(getRows(), getColumns(),'A');
        matrixB = inputMatrix(matrixA.length, matrixA[0].length,'B');
        System.out.println("\nThe matrix A is:");
        printMatrix(matrixA);
        System.out.println("\nThe matrix B is:");
        printMatrix(matrixB);
    }


}