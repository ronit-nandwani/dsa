package advanced.sorting;

import java.util.Arrays;

public class Sorting {
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
        int[] arr = {6,3,3,6,7,8,7,3,7};
        // Expected Output: [3,3,3,6,6,7,7,7,8]
        System.out.println(Arrays.toString(countSort(arr)));
    }
}
