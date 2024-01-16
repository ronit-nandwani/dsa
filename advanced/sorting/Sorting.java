package advanced.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


class Solution {
    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        Collections.sort(A, new SortByFactors());
        return A;
    }
}

class SortByFactors implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        int factorsOfA = factors(a);
        int factorsOfB = factors(b);

        if (factorsOfA == factorsOfB) return a - b;

        return factorsOfA - factorsOfB;
    }

    // Fast Factors find
    public int factors(int N) {
        int count=0;
        for(int i=1;i*i<=N; i++){
            if(N%i==0){
                if(i==N/i){
                    count++;
                }
                else{
                    count= count+2;
                }
            }
        }
        return count;
    }

    // Slow factors find
    // public int factors(int num) {
    //     int i = 1, ans = 0;

    //     while (i <= num) {
    //         if (num % i == 0) ans += 2;
    //         if (i == num) ans--;
    //         i++;
    //     }

    //     return ans;
    // }
}

public class Sorting {
    static long ic_c = 0;
    static long mod = 1000000000 + 7;

    public static void merge(int[] A, int l, int mid, int r) {
        int[] ans = new int[r-l+1];
        int i=l, j=mid+1, k=0;
        while(i<=mid && j<=r) {
            if(A[i] <= A[j]) {
                ans[k] = A[i];
                i++;
            } else {
                ic = (long)(ic + mid-i+1)%mod;
                ans[k] = A[j];
                j++;
            }
            k++;
        }
        while(i<= mid) {
            ans[k] = A[i];
            k++;i++;
        }
        while(j<= r) {
            ans[k] = A[j];
            k++;j++;
        }

        for(int ii = 0; ii < ans.length; ii++) {
            A[ii+l]=ans[ii];
        }
    }

    public static void mergeSort(int[] A, int l, int r) {
        if(l>=r) return;
        int mid = (l+r)/2;
        mergeSort(A,l,mid);
        mergeSort(A,mid+1,r);
        merge(A, l, mid, r);
    }

    // Count Inversion Pair
    // Given an array of integers A. If i < j and A[i] > A[j], then the pair (i, j) is called an inversion of A. Find the total number of inversions of A modulo (109 + 7).
    //Problem Constraints
    //1 <= length of the array <= 105
    //1 <= A[i] <= 109
    //Input Format
    //The only argument given is the integer array A.
    //Output Format
    //Return the number of inversions of A modulo (109 + 7).
    //Example Input
    //Input 1:
    //A = [1, 3, 2]
    //Input 2:
    //A = [3, 4, 1, 2]
    //Example Output
    //Output 1:
    //1
    //Output 2:
    //4
    //Example Explanation
    //Explanation 1:
    //The pair (1, 2) is an inversion as 1 < 2 and A[1] > A[2]
    //Explanation 2:
    //The pair (0, 2) is an inversion as 0 < 2 and A[0] > A[2]
    //The pair (0, 3) is an inversion as 0 < 3 and A[0] > A[3]
    //The pair (1, 2) is an inversion as 1 < 2 and A[1] > A[2]
    //The pair (1, 3) is an inversion as 1 < 3 and A[1] > A[3]
    public static int inversionCountInAnArray(int[] A) {
        int n = A.length;
        mergeSort(A,0,n-1);
        return (int)ic_c;
    }

    // Merge Two Sorted Arrays
    // Given two sorted integer arrays A and B, merge B and A as one sorted array and return it as an output.
    //Note: A linear time complexity is expected and you should avoid use of any library function.
    //Problem Constraints
    //-2×109 <= A[i], B[i] <= 2×109
    //1 <= |A|, |B| <= 5×104
    //Input Format
    //First Argument is a 1-D array representing  A.
    //Second Argument is also a 1-D array representing B.
    //Output Format
    //Return a 1-D vector which you got after merging A and B.
    //Example Input
    //Input 1:
    //A = [4, 7, 9]
    //B = [2, 11, 19]
    //Input 2:
    //A = [1]
    //B = [2]
    //Example Output
    //Output 1:
    //[2, 4, 7, 9, 11, 19]
    //Output 2:
    //[1, 2]
    //Example Explanation
    //Explanation 1:
    //Merging A and B produces the output as described above.
    //Explanation 2:
    // Merging A and B produces the output as described above.
    public int[] mergeTwoSortedArrays(final int[] A, final int[] B) {
        int n = A.length;
        int m = B.length;
        int[] ans = new int[n+m];
        int i=0, j=0, k=0;
        while(i<n && j<m) {
            if(A[i] <= B[j]) {
                ans[k] = A[i];
                i++;
            } else {
                ans[k] = B[j];
                j++;
            }
            k++;
        }
        while(i<n) {
            ans[k] = A[i];
            k++;i++;
        }
        while(j<m) {
            ans[k] = B[j];
            k++;j++;
        }
        return ans;
    }

    // Given an array A. Sort this array using Count Sort Algorithm and return the sorted array.
    public static int[] countSort(int[] A) {
        int n = A.length;
        int l = 100001;
        int[] f = new int[l];
        for(int i=0;i<n;i++) {
            f[A[i]]++;
        }
        int[] ans = new int[n];
        int j=0;
        for(int d=0;d<l;d++) {
            for(int i=1;i<=f[d];i++) {
                ans[j++] = d;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] arr = {6,3,3,6,7,8,7,3,7};
//        // Expected Output: [3,3,3,6,6,7,7,7,8]
//        System.out.println(Arrays.toString(countSort(arr)));


        // Expected Output: 3
        int[] arr = {45,10,15,25,50};
        System.out.println(inversionCountInAnArray(arr));
        System.out.println(ic_c);
    }
}
