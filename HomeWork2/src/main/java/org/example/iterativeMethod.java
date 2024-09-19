package org.example;

import java.util.Arrays;

class iterativeMethod{
    double[][] matrix;
    public static int size;
    private static final double e = 1.0E-6;

    public iterativeMethod(double[][] matrix, int size){
        this.matrix = matrix;
        this.size = size;
    }

    public void Solution(){

        double[] x = new double[size];
        double[] tempX = new double[size];
        Arrays.fill(x, 0);
        Arrays.fill(tempX, 0);
        double[] errorCheck;

        boolean errorRate = true;
        while(errorRate){
            errorCheck = Arrays.copyOf(x, x.length);
            for(int i = 0; i < size; i++){ //Идем по строкам (Все ровно)
                tempX[i] = matrix[i][size];
                for (int j = 0; j<size; j++){
                    if(i!=j){
                        tempX[i] -= (matrix[i][j] * x[j]);
                    }
                }
            }

            for(int i = 0; i <= x.length - 1; i++){
                x[i] = tempX[i]/matrix[i][i];
                if (Math.abs(x[i] - errorCheck[i]) < e){
                    errorRate = false;
                }
            }

        }
        for (int i = 0; i <= x.length - 1; i++){
            System.out.println("x" + (i+1) + " = " + x[i]);
        }
    }
}