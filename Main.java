package com.company;

public class Main {
    public static void main(String[] args) {
        while (true){
            InputClass inp = new InputClass();
            Matrix matrix = new Matrix(inp.choose());
            System.out.println("Исходная матрица: ");
            matrix.show();
            System.out.println("------------------------------------------------------------");
            matrix.nevyzki();
        }
    }
}
