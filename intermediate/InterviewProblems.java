package intermediate;

public class InterviewProblems {

    // Given an array of size N, find the majority element. The majority element is the element that appears more than floor(n/2) times.
    //You may assume that the array is non-empty and the majority element always exists in the array.
    public int majorityElement(final int[] A) {
        int ans = A[0], freq = 1, n = A.length;
        for(int i=1;i<n;i++) {
            if(freq == 0) {
                ans = A[i];
                freq = 1;
            } else if (ans == A[i]) {
                ++freq;
            } else {
                freq--;
            }
        }
        int count = 0;
        for(int i=0;i<n;i++) {
            if(ans == A[i]) {
                count++;
            }
        }
        if(count > n/2) {
            return ans;
        } else {
            return 0;
        }
    }

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

    //Given a binary string A. It is allowed to do at most one swap between any 0 and 1. Find and return the length of the longest consecutive 1’s that can be achieved.
    public static int longestConsecutiveOnesLength(String A) {
        char[] a = A.toCharArray();
        int n = a.length;
        int c = 0;
        int ans = 0;
        for(int i=0;i<n;i++) {
            if (a[i] == '0') {
                c += 0;
            } else {
                c += 1;
            }
        }
        if(c==n) {
            return n;
        }
        for(int i=0;i<n;i++) {
            if(a[i] == '0') {
                int l=0,r=0;
                for(int j=i-1;j>=0;j--) {
                    if(a[j] == '1') {
                        l++;
                    } else {
                        break;
                    }
                }
                for(int j=i+1;j<n;j++) {
                    if(a[j] == '1') {
                        r++;
                    } else {
                        break;
                    }
                }
                int len = l+r+1;
                if(len > c) {
                    len = c;
                }
                ans = Math.max(ans,len);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutiveOnesLength("11010110000000000"));
    }
}
