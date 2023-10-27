package advanced.arrays;

public class Arrays2D {
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
