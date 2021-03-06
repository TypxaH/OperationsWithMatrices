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
            System.out.printf("\nEnter the elements of the matrix %c in row %d!\n", nameOfMatrix, i + 1);
            for (int j = 0; j < colsNumber; j++) {
                System.out.printf(" %c[%d][%d] = \r", nameOfMatrix, i + 1, j + 1);
                matrix[i][j] = scanner.nextDouble();
            }
        }

        return matrix;
    }

    private static void inputSquareMatrix() {
        int n;
        System.out.print("Enter number of the rows of a square matrix ?: ");
        n = scanner.nextInt();
        matrixA = inputMatrix(n, n);

        System.out.println("\nThe matrix A is:");
        printMatrix(matrixA);
    }

    private static void inputTwoMatrices() {
        matrixA = inputMatrix(getRows(), getColumns(), 'A');
        matrixB = inputMatrix(matrixA.length, matrixA[0].length, 'B');

        System.out.println("\nThe matrix A is:");
        printMatrix(matrixA);
        System.out.println("\nThe matrix B is:");
        printMatrix(matrixB);
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
        double[][] cofactorMatrix = new double[matrix.length - 1][matrix[0].length - 1];
        int nextR = 0, nextC = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == col) {
                    continue;
                }
                cofactorMatrix[nextR][nextC] = matrix[i][j];
                nextC++;
            }
            nextC = 0;
            nextR++;
        }

        return cofactorMatrix;
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
        if (determinant == 0) {
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

    //?????????????? ?? ????????? ???? ????????? ?? ?????? ??? ?? ?????????. ???????? ?? ???????? ?? ????????? ???????.
    public static double calculateDeterminant(double[][] matrix) {
        double sum = 0;
        int sign = 1;
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        for (int col = 0; col < matrix[0].length; col++) {
            sum += sign * matrix[0][col] * calculateDeterminant(getCofactor(matrix, 0, col));
            sign *= -1;
        }

        return sum;
    }

    private static void printMenu() {
        System.out.println("\n----------Operations with matrices----------");
        System.out.println("1. Input and print the matrix (A) from keyboard;");
        System.out.println("2. Matrix addition (A + B);");
        System.out.println("3. Matrix subtraction (A - B);");
        System.out.println("4. Matrix multiplication (A * B);");
        System.out.println("5. Determinant of a matrix (det(A));");
        System.out.println("6. Inverse of a matrix (A-1);");
        System.out.println("7. Check if a square matrix can be converted to a identity matrix;");
        System.out.println("---------------------------------------------------------");
        System.out.print("Choose an operation (1-7): ");
    }

    private static void identityMenuItem() {
        inputSquareMatrix();
        double determinant = calculateDeterminant(matrixA);
        if (determinant != 0) {
            System.out.println("\nThe matrix A can be converted to the identity matrix.");
        } else {
            System.out.println("\nThe matrix A can't be converted to the identity matrix.");
        }
    }

    private static void inverseMatrixMenuItem() {
        inputSquareMatrix();
        result = getInverseMatrix(matrixA);
        if (result != null) {
            System.out.println("\nThe inverse of the matrix A is the matrix:");
            printMatrix(result);
        } else {
            System.out.println("\nThe matrix A is NOT invertible.");
        }
    }

    private static void multiplicationMenuItem() {
        inputTwoMatrices();
        result = multiplyMatrices(matrixA, matrixB);
        if (result == null) {
            System.out.println("The number of the column in matrix A must be equal to the number of the rows in matrix B!");
        } else {
            System.out.println("\nThe result matrix of the multiplication is:");
            printMatrix(result);
        }
    }

    private static void subtractionMenuItem() {
        inputTwoMatrices();
        result = subtractMatrices(matrixA, matrixB);
        System.out.println("\nThe result matrix of the subtraction is:");
        printMatrix(result);
    }

    private static void additionMenuItem() {
        inputTwoMatrices();
        result = addMatrices(matrixA, matrixB);
        System.out.println("\nThe result matrix of the addition is:");
        printMatrix(result);
    }

    private static void inputMenuItem() {
        matrixA = inputMatrix(getRows(), getColumns());
        System.out.println("\nThe input matrix is:");
        printMatrix(matrixA);
    }

    public static void main(String[] args) {
        printMenu();
        int operation = scanner.nextInt();
        switch (operation) {
            case 1:
                inputMenuItem();
                break;
            case 2:
                additionMenuItem();
                break;
            case 3:
                subtractionMenuItem();
                break;
            case 4:
                multiplicationMenuItem();
                break;
            case 5:
                inputSquareMatrix();
                System.out.println("\nThe determinant of the matrix is det(A) = " + calculateDeterminant(matrixA));
                break;
            case 6:
                inverseMatrixMenuItem();
                break;

            case 7:
                identityMenuItem();
                break;
            default:
                System.out.println("Wrong choice!");
        }


/*        double[][] arrayA = {
                {2, -10, -2},
                {14, 12, 10},
                {4, -2, 2},
        };
        double[][] arrayB = {
                {6, 10, -2},
                {0, -12, -4},
                {-5, 2, -2},
        };*/
//        printMatrix(addMatrices(arrayA,arrayB));

//        printMatrix(subtractMatrices(arrayA,arrayB));

        /*result = multiplyMatrices(arrayA, arrayB);
        if (result == null) {
            System.out.println("The number of the column in matrix A must be equal to the number of the rows in matrix B!");
        } else {
            System.out.println("\nThe result matrix of the multiplication is:");
            printMatrix(result);
        }*/

//        System.out.println(calculateDeterminant(arrayA));

      /*  result = getInverseMatrix(arrayA);
        if (result != null) {
            System.out.println("\nThe inverse of the matrix A is the matrix:");
            printMatrix(result);
        } else {
            System.out.println("\nThe matrix A is NOT invertible.");
        }*/

       /* double determinant = calculateDeterminant(arrayA);
        if (determinant != 0) {
            System.out.println("\nThe matrix A can be converted to the identity matrix.");
        } else {
            System.out.println("\nThe matrix A can't be converted to the identity matrix.");
        }*/
    }
}