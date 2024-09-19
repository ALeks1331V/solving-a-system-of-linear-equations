package org.example;

class GaussMethod{
    public static double[][] matrix;
    public static int size;
    private static boolean solvable = true;

    public GaussMethod(double[][] matrix, int size) {
        this.matrix = matrix;
        this.size = size;
    }

    public void Solution(){
        checkIfSolvable();
        if (solvable == false){
            System.out.println("Система не имеет решений");
            return;
        }
        stepView();

        double[] solution = new double[size];

        // Reverse substitution
        for (int i = size - 1; i >= 0; i--) {
            solution[i] = matrix[i][size] / matrix[i][i];
            for (int j = i + 1; j < size; j++) {
                solution[i] -= (matrix[i][j] / matrix[i][i]) * solution[j];
            }
        }
        System.out.println("Решение системы методом Гаусса:");
        for (int i = 0; i < size; i++) {
            System.out.println("x"+ (i+1) + " = " + solution[i]);
        }
    }

    private static void checkIfSolvable(){ //checks for null rows
        //boolean flag1 = false;
        for (int i = 0; i < size; i++) {
            boolean allZero = true;
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero && matrix[i][size] != 0) {
                System.out.println("Система несовместна.");
                solvable = false;
            }
            if (allZero && matrix[i][size] == 0) {
                System.out.println("Найдена тривиальная строка (0 = 0).");
                solvable = false;
            }
        }
    }


    /*
     *This method, first by rearranging the rows, helps to avoid zeros on
     *the main diagonal and brings the matrix to a more convenient form for
     *further transformation of the matrix and bringing it to a stepwise form
     */
    private static void stepView(){
        for (int i = 0; i < size; i++) {
            //Searching for the maximum element in the current column
            double maxEl = Math.abs(matrix[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < size; k++) {
                if (Math.abs(matrix[k][i]) > maxEl) {
                    maxEl = Math.abs(matrix[k][i]);
                    maxRow = k;
                }
            }

            //rearranging strings
            double[] temp = matrix[maxRow];
            matrix[maxRow] = matrix[i];
            matrix[i] = temp;

            //reduction to the upper triangular view (step view)
            for (int k = i + 1; k < size; k++) {
                double factor = matrix[k][i] / matrix[i][i];
                for (int j = i; j <= size; j++) {
                    matrix[k][j] -= factor * matrix[i][j];
                }
            }
        }
    }
}