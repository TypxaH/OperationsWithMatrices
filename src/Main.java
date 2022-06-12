public class Main {

    public static double[][] multiplyMatrices(double[][] matrixOne, double[][] matrixTwo) {
        double[][] resultMatrix = new double[matrixOne.length][matrixTwo[0].length];
        for (int rowsOne = 0; rowsOne < matrixOne.length; rowsOne++) {
            for (int colsTwo = 0; colsTwo < matrixTwo[0].length; colsTwo++) {
                double cellSum = 0;
                for (int colsOne = 0; colsOne < matrixOne[0].length; colsOne++) {
                    cellSum += matrixOne[rowsOne][colsOne] * matrixTwo[colsOne][colsTwo];
                    resultMatrix[rowsOne][colsTwo] = cellSum;
                }
            }
        }
        return resultMatrix;

    }
    public static double[][] subtractMatrices(double[][] matrix, double[][] matrix1) {
        double[][] resultMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                resultMatrix[i][j] = matrix[i][j] - matrix1[i][j];
            }
        }

        return resultMatrix;
    }

    public static double[][] addMatrices(double[][] matrix, double[][] matrix1) {
        double[][] resultMatrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                resultMatrix[i][j] = matrix[i][j] + matrix1[i][j];
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
        double[][] inverseMatrix = new double[matrix.length][matrix.length];
        int sign;
        double determinant = calculateDeterminant(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sign = (i + j) % 2 == 0 ? 1 : -1;
                inverseMatrix[j][i] = sign * calculateDeterminant(getCofactor(matrix, i, j)) / determinant;
            }
        }

        return inverseMatrix;
    }

    public static double calculateDeterminant(double[][] matrix) {
        double sum = 0;
        int sign = 1;
        if (matrix.length == 1) {
            return matrix[0][0];
        } else if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        for (int col = 0; col < matrix[0].length; col++) {
            sum += sign * matrix[0][col] * calculateDeterminant(getCofactor(matrix, 0, col));
            sign *= -1;
        }

        return sum;
    }

    public static void main(String[] args) {
        double[][] array1 = {
                {2, 6, 3},
                {4, -1, 3},
                {1, 3, 2},
        };
        double[][] array2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        //System.out.println(calculateDeterminant(array));
        //printMatrix(getCofactor(array, 2, 0));
        //double[][] inverse = getInverseMatrix(array);
        //printMatrix(addMatrices(array1,array2));
        printMatrix(multiplyMatrices(array1,array2));

    }
}