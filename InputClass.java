package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class InputClass {
    Integer input = null;
    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    int size;

    public double[][] choose(){
        input = null;
        while (input == null){
            System.out.println("коеффициенты матрицы: 1 - с консоли, 2 - с файла, 3 - рандом");
            try {
                input = Integer.parseInt(console.readLine().trim());
                if (input == 1) return readMatrix(null);
                else if (input == 2) {
                    System.out.println("Имя файла: ");
                    File file = new File(console.readLine().trim());
                    return readMatrix(file);
                } else if (input == 3) return random();
                else throw new IOException();
            } catch (Exception e) {
                System.out.println("Неправильный ввод, попробуйте еще");
                input = null;
            }
        }
        return null;
    }

    public int readSize(File file) throws FileNotFoundException {
        System.out.println("Размер матрицы: ");

        InputStream in = System.in;
        if (file != null) in = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        int size = 0;
        while (size == 0){
            try {
                size = Integer.parseInt(reader.readLine().trim()) ;
            } catch (Exception e) {
                System.out.println("Неправильный ввод, попробуйте еще");
                size = 0;
            }
        }
    return size;
    }
    public double[][] readMatrix(File file) throws IOException {
        size = readSize(file);
        InputStream in = System.in;
        if (file != null) in = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        double matrix[][] = new double[size][size+1];
        if (file != null) reader.readLine();
        for (int i = 0; i < size; i++) {
            String line[] = reader.readLine().replace(',','.').split(" ");
            if (line.length != size + 1) throw new IOException("Неподходящая матрица");

            for (int j = 0; j < size + 1; j++) {
                matrix[i][j] =round(Double.parseDouble(line[j]), 2)  ;
            }
        }
        return matrix;
    }
    public double[][] random() throws FileNotFoundException {
        size = readSize(null);

        double [][] randMatrix = new double[size][size+1];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size+1; j++) {
                randMatrix[i][j] = round(Math.random() * (10) - 0, 0) ;
            }
        }
        return randMatrix;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
