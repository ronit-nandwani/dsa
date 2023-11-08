package intermediate.arrays;

import java.util.ArrayList;

public class twoDMatrix {
    // Given 2 arrays of integers A and B
    // Return a 2D array of integers such that i-th row of the array contains list of values in A such that A[j]%B[i]==0 in A in sequential order
    public ArrayList<ArrayList<Integer>> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        int n = A.size();
        int m = B.size();

        for(int i=0;i<m;++i) {
            ArrayList<Integer> ar = new ArrayList<Integer>();
            for(int j=0;j<n;j++) {
                if(A.get(j) % B.get(i) == 0) {
                    ar.add(A.get(j));
                }
            }
            arr.add(ar);
        }
        return arr;
    }

    public int[][] matrixTranspose(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        int[][] res = new int[m][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                res[j][i] = A[i][j];
            }
        }

        return res;
    }

    // You are given a n x n 2D matrix A representing an image.
    //Rotate the image by 90 degrees (clockwise).
    //You need to do this in place.
    //Note: If you end up using an additional array, you will only receive partial score.
    public void rotateSquareMatrixBy90Degrees(int[][] A) {
        int n = A.length;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                int t = A[j][i];
                A[j][i] = A[i][j];
                A[i][j] = t;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n/2; j++) {
                int t = A[i][j];
                A[i][j] = A[i][n-1-j];
                A[i][n-1-j] = t;
            }
        }
    }
    // You are given a 2D integer matrix A, return a 1D integer array containing column-wise sums of original matrix.
    public int[] columnSum(int[][] A) {
        int[] arr = new int[A[0].length];
        int sum;
        for(int i=0;i<A[0].length;i++) {
            sum = 0;
            for(int j=0;j<A.length;j++) {
                sum += A[j][i];
            }
            arr[i] = sum;
        }
        return arr;
    }

    // You are given a N X N integer matrix. You have to find the sum of all the main diagonal elements of A.
    //Main diagonal of a matrix A is a collection of elements A[i, j] such that i = j.
    public int mainDiagonalSum(final int[][] A) {
        int sum = 0;
        for(int i=0;i<A.length;i++) {
            sum += A[i][i];
        }
        return sum;
    }
}
