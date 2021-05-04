package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Matrix {

    double[][] classMatrix;
    int step;
    double m[][];

    public Matrix(double[][] matrix){
        this.classMatrix = matrix;
        this.step = 1;
    }

    public void nevyzki(){
        double[] answers = answers(calc(classMatrix));
        for (int i = 0; i < classMatrix.length; i++) {
            double temp = 0;
            for (int j = 0; j < classMatrix.length; j++) {
//                System.out.println("к " + temp + " прибавляем " + classMatrix[i][j] + " * " + answers[classMatrix.length-j-1]);
                temp += classMatrix[i][j] * answers[classMatrix.length-j-1];
//                System.out.println("получаем " + temp);
            }
            System.out.println("Невязка: " + (temp - classMatrix[i][classMatrix.length]));
        }


    }

    public double[][] calc(double[][] mat){
        if (step == classMatrix.length ) {
            determinant(mat);
            return mat;
        }

        System.out.println("step: " + step);

        double matrix[][] = maxRowFirst(mat, step);
        double multipliers[] = multipliers(matrix, step);
        matrix = multiply(matrix, multipliers, step);
        step++;
        matrix = calc(matrix);
        return matrix;
    }
    public double[][] maxRowFirst(double[][] matrix, int step){
        System.out.println("Ставим ряд с главным элементом на первое место: ");

        double[][] newMat = copyMatrix(matrix);

        double max = Math.abs(newMat[step - 1][step - 1]);
        int pos = step - 1;

        for (int i = step; i < newMat.length; i++) {
            if (max< Math.abs(newMat[i][step - 1])) {
                max = Math.abs(newMat[i][step - 1]);
                pos = i;
            }
        }

        double tmp[] =  newMat[pos];
        newMat[pos] = newMat[step - 1];
        newMat[step - 1] = tmp;
        show(newMat);

        System.out.println("------------------------------------------------------------");

        return newMat;
    }
    public double[] multipliers(double[][] matrix, int step){
        System.out.println("Коеффициенты: ");

        double multipliers[] = new double[matrix.length];
        for (int i = step; i < matrix.length; i++) {
            multipliers[i] = - matrix[i][step - 1] / matrix[step-1][step - 1];

            System.out.printf("%10s",InputClass.round(multipliers[i], 2));
            System.out.println();
        }

        System.out.println("------------------------------------------------------------");


        return multipliers;
    }
    public double[][] multiply(double[][] matrix, double multipliers[], int step){
        System.out.println("Умножаем");
        int multIndex = 0;
        for (int i = step; i < matrix.length; i++) {
            for (int j = step - 1; j < matrix.length+1; j++) {
                matrix[i][j] = InputClass.round(multipliers[i] * matrix[step - 1][j] + matrix[i][j], 2) ;
            }
        }
        show(matrix);
        System.out.println("------------------------------------------------------------");
        return matrix;
    }
    private double[] answers(double[][] matrix) {
        int len = matrix.length;
        double[] answers = new double[len];
        int count = 1;
//        System.out.println("Последний:" + matrix[len-1][len] + " делим на " + matrix[len-1][len-1]);
        answers[0] = matrix[len-1][len] / matrix[len-1][len-1];
//        System.out.println("x" + 0 + " = " + answers[0]);
        for (int i = 1; i < len; i++) {
            double last = matrix[len -1 - i][len];
//            System.out.println("Последний: " + last);
            double temp = matrix[len -1 -i][len - 1 - i];
//            System.out.println("первый: " + temp);

            for (int j = 0; j < count; j++) {
//                System.out.println("От последнего " + last + " отнимаем "+ matrix[len -1 -i][len -1 -j] +  " * " + answers[j] );
                last -= matrix[len -1 -i][len -1 -j] * answers[j];
//                System.out.println("Теперь последний " + last);
            }

            answers[count++] = last / temp;
//            System.out.println("Последний:" + last + " делим на " + temp);

//            System.out.println("x" + count + " = " + answers[count-1]);

        }





        for (int i = 0; i < answers.length; i++) {
            System.out.println("ans[" + i + "] = " + answers[i]);
        }
        show();
        return answers;
    }

    public double[][] copyMatrix(double[][] matrix){
        double[][] newMatrix = new double[matrix.length][matrix.length+1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length+1; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }
    public void show(){
        show(classMatrix);
    }
    public void show(double[][] mat){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length + 1; j++) {
                System.out.printf("%6s", mat[i][j]);
            }
            System.out.println();
        }
    }

    public double[][] generateSubArray (double A[][], int N, int j1){
        m = new double[N-1][];
        for (int k=0; k<(N-1); k++)
            m[k] = new double[N-1];

        for (int i=1; i<N; i++){
            int j2=0;
            for (int j=0; j<N; j++){
                if(j == j1)
                    continue;
                m[i-1][j2] = A[i][j];
                j2++;
            }
        }
        return m;
    }
    /*
     * Calculate determinant recursively
     */
    public double determinant(double A[][]){
        double det = 1;
        for (int i = 0; i < A.length; i++) {
            det *=A[i][i];
        }
        if (step%2 ==0) det = -det;
        System.out.println("Детерминант:" + det);
        return det;
    }
}
