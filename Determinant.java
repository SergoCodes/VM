package com.company;

public class Determinant {

    double A[][];
    double m[][];
    int N;
    int start;
    int last;

    public Determinant (double A[][], int N, int start, int last){
        this.A = A;
        this.N = N;
        this.start = start;
        this.last = last;
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
    public double determinant(double A[][], int N){
        double res;

        // Trivial 1x1 matrix
        if (N == 1) res = A[0][0];
            // Trivial 2x2 matrix
        else if (N == 2) res = A[0][0]*A[1][1] - A[1][0]*A[0][1];
            // NxN matrix
        else{
            res=0;
            for (int j1=0; j1<N; j1++){
                m = generateSubArray (A, N, j1);
                res += Math.pow(-1.0, 1.0+j1+1.0) * A[0][j1] * determinant(m, N-1);
            }
        }
        return res;
    }
}