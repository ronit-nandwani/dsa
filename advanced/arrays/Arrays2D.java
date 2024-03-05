package advanced.arrays;

public class Arrays2D {
    // Row Sum

    // 1 <= A.size() <= 103
    // 1 <= A[i].size() <= 103
    // 1 <= A[i][j] <= 103
    // Input Format
    // First argument A is a 2D array of integers.(2D matrix).
    // Output Format
    // Return an array containing row-wise sums of original matrix.
    // Example Input
    // Input 1:
    // [1,2,3,4]
    // [5,6,7,8]
    // [9,2,3,4]
    // Example Output
    // Output 1:
    // [10,26,18]
    // Example Explanation
    // Explanation 1
    // Row 1 = 1+2+3+4 = 10
    // Row 2 = 5+6+7+8 = 26
    // Row 3 = 9+2+3+4 = 18
    public int[] solve(int[][] A) {
        int[] result = new int[A.length];
        for(int i = 0; i < A.length; i++){
        for(int j = 0; j < A[0].length; j++){
        result[i] += A[i][j];
        }
        }
        return result;
    }
    // Solution by team
    // public class Solution {
    //     public int[] solve(int[][] A) {
    //         int n = A.length, m = A[0].length;
    //         int ans[] = new int[n];
    //         for(int i = 0; i < n; i++){
    //             int temp = 0;
    //             // Finding sum of elements of ith row
    //             for(int j = 0; j < m; j++){
    //                 temp += A[i][j];
    //             }
    //             ans[i] = temp;
    //         }
    //         return ans;
    //     }
    // }

    // Minor Diagonal Sum

    // You are given a N X N integer matrix. You have to find the sum of all the minor diagonal elements of A.
    // Minor diagonal of a M X M matrix A is a collection of elements A[i, j] such that i + j = M + 1 (where i, j are 1-based).
    // Problem Constraints
    // 1 <= N <= 103
    // -1000 <= A[i][j] <= 1000
    // Input Format
    // First and only argument is a 2D integer matrix A.
    // Output Format
    // Return an integer denoting the sum of minor diagonal elements.
    // Example Input
    // Input 1:
    // A = [[1, -2, -3],
    //     [-4, 5, -6],
    //     [-7, -8, 9]]
    // Input 2:
    // A = [[3, 2],
    //     [2, 3]]
    // Example Output
    // Output 1:
    // -5 
    // Output 2:
    // 4 
    // Example Explanation
    // Explanation 1:
    // A[1][3] + A[2][2] + A[3][1] = (-3) + 5 + (-7) = -5
    // Explanation 2:
    // A[1][2] + A[2][1] = 2 + 2 = 4
    public int minorDiagonalSum(final int[][] A) {
        int n = A.length, result = 0;
        for(int i = 0; i < n; i++){
            result += A[n-i-1][i];
        }
        return result;
    }
    // Solution by team
    // public class Solution {
    //     // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    //     public int solve(final int[][] A) {
    //         int N = A.length;
        
    //         int sum = 0;
    //         for (int i = 0; i < N;i++){
    //             sum += A[i][N - 1 - i];
    //         }
        
    //         return sum;
    //     }
    // }

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
