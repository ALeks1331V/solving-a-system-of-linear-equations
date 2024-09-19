package org.example;

import org.apache.commons.math4.legacy.linear.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        double[][] matrix = getMatrix();
        printMatrix(matrix);
        getMatrix();

        System.out.println("\nРешение библиотекой Apache Commons Math: ");
        LibSolution(matrix);

        System.out.println("\nРешение итерационным методом:");
        iterativeMethod iMatrix = new iterativeMethod(matrix, matrix.length);
        iMatrix.Solution();

        GaussMethod gMatrix = new GaussMethod(matrix, matrix.length);
        System.out.println();
        gMatrix.Solution();
    }

    private static void printMatrix(double[][] matrix){
        for (int i=0; i<3; i++){
            for (int j = 0; j < 4; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static void LibSolution(double[][] matrix) {
        double[][] coefMatrix = new double[matrix.length][matrix.length];
        double[] solutions = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                coefMatrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            solutions[i] = matrix[i][matrix.length];
        }

        RealMatrix coefficients = new Array2DRowRealMatrix(coefMatrix);
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(solutions);
        RealVector solution = solver.solve(constants);
        System.out.println(solution);
    }

    private static double[][] getMatrix() throws IOException {
        String filePath = "PATH";
        List<Double[]> matrix = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;

        while ((line = reader.readLine()) != null) {
            line = line.replaceAll(" ", "");
            line = line.replaceAll("\\+", "");
            line = line.replaceAll("=", "");
            String[] sArray = line.split("[a-z]");
            Double[] dArray = new Double[sArray.length];

            for (int i = 0; i < sArray.length; i++) {
                dArray[i] = Double.parseDouble(sArray[i]);
            }

            matrix.add(dArray);
        }
        reader.close();

        double[][] matrixD = new double[matrix.size()][matrix.size() + 1];
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size() + 1; j++) {
                matrixD[i][j] = matrix.get(i)[j];
            }
        }
        return matrixD;
    }
}
