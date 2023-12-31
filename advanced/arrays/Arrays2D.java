package advanced.arrays;

public class Arrays2D {
    // You are given a 2D integer matrix A, make all the elements in a row or column zero if the A[i][j] = 0. Specifically, make entire ith row and jth column zero.
    public int[][] rowToColumnZero(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        for(int r=0;r<n;r++) {
            boolean flag = false;
            for(int c=0;c<m;c++) {
                if(A[r][c] == 0) {
                    flag = true;
                }
            }
            if(flag == true) {
                for(int c=0;c<m;c++) {
                    if(A[r][c] != 0) {
                        A[r][c] = -1;
                    }
                }
            }
        }

        for(int c=0;c<n;c++) {
            boolean flag = false;
            for(int r=0;r<m;r++) {
                if(A[r][c] == 0) {
                    flag = true;
                }
            }
            if(flag == true) {
                for(int r=0;r<m;r++) {
                    if(A[r][c] != 0) {
                        A[r][c] = -1;
                    }
                }
            }
        }

        for(int r=0;r<n;r++) {
            for(int c=0;c<m;c++) {
                if(A[r][c] == -1) {
                    A[r][c] = 0;
                }
            }
        }
        return A;
    }

    // Given a binary sorted matrix A of size N x N. Find the row with the maximum number of 1.
    public int findRowWithMax1(int[][] A) {
        int n=A.length,m=A[0].length;
        int i=0,j=m-1,ans=0;

        while(i<n && j>=0) {
            while(j>=0 && A[i][j]==1) {
                j--;
                ans=i;
            }
            i++;
        }
        return ans;
    }

    // Given an integer A, generate a square matrix filled with elements from 1 to A2 in spiral order and return the generated square matrix.
    // For N=5 output is below
    // [ [1,   2,  3,  4, 5],
    //  [16, 17, 18, 19, 6],
    //  [15, 24, 25, 20, 7],
    //  [14, 23, 22, 21, 8],
    //  [13, 12, 11, 10, 9] ]
    public int[][] generateSpiralMatrix(int n) {
        int r=0,c=0;
        int[][] arr = new int[n][n];
        int e = 1;
        while(n>1) {
            int i=c,j=c;
            for(int k=0;k<n-1;k++) {
                arr[i][j] = e++;
                j++;
            }
            for(int k=0;k<n-1;k++) {
                arr[i][j] = e++;
                i++;
            }
            for(int k=0;k<n-1;k++) {
                arr[i][j] = e++;
                j--;
            }
            for(int k=0;k<n-1;k++) {
                arr[i][j] = e++;
                i--;
            }
            r++;c++;n=n-2;
        }
        if(n==1) {
            arr[r][c] = e;
        }
        return arr;
    }


    // Given a matrix of integers A of size N x M and an integer B.
    //In the given matrix every row and column is sorted in non-decreasing order. Find and return the position of B in the matrix in the given form:
    //If A[i][j] = B then return (i * 1009 + j)
    //If B is not present return -1.
    //Note 1: Rows are numbered from top to bottom and columns are numbered from left to right.
    //Note 2: If there are multiple B in A then return the smallest value of i*1009 +j such that A[i][j]=B.
    //Note 3: Expected time complexity is linear
    //Note 4: Use 1-based indexing
    public int searchInRoWColumnSortedMatrix(int[][] A, int B) {
        int n=A.length,m=A[0].length;
        int i =0,j=m-1;

        int c = -1;
        while(i<n && j>=0) {
            if(A[i][j] == B) {
                int t = (i+1)*1009 + j+1;
                if(c == -1) {
                    c = t;
                }
                if (t<c) {
                    c = t;
                }
                j--;
            } else if(A[i][j] > B) {
                j--;
            } else {
                i++;
            }
        }
        return c;
    }
}
