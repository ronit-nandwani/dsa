public class PrefixSum {

    // Given an array, arr[] of size N, the task is to find the count of array indices
    // such that removing an element from these indices makes the sum of even-indexed
    // and odd-indexed array elements equal.
    public static int specialCount(int[] A) {
        int n = A.length;
        int[] prefixSumEven = new int[n];
        int[] prefixSumOdd = new int[n];
        for(int i=0;i<n;i++) {
            if(i==0) {
                prefixSumEven[i] = A[i];
                prefixSumOdd[i] = 0;
            } else if(i % 2 == 0) {
                prefixSumEven[i] = prefixSumEven[i-1] + A[i];
                prefixSumOdd[i] = prefixSumOdd[i - 1];
            } else {
                prefixSumOdd[i] = prefixSumOdd[i-1] + A[i];
                prefixSumEven[i] = prefixSumEven[i - 1];
            }
        }
        int count = 0;
        int sumOdd = 0;
        int sumEven = 0;
        for(int i=0;i<n;i++) {
            if(i==0) {
                sumEven = prefixSumOdd[n-1] - prefixSumOdd[i];
                sumOdd = prefixSumEven[n-1] - prefixSumEven[i];
            } else {
                sumEven = prefixSumEven[i-1] + prefixSumOdd[n-1] - prefixSumOdd[i];
                sumOdd = prefixSumOdd[i-1] + prefixSumEven[n-1] - prefixSumEven[i];
            }
            if(sumOdd == sumEven) {
                count++;
            }
        }
        return count;
    }
    public static long[] rangeSum(int[] A, int[][] B) {
        int n = A.length;
        int m = B.length;
        long[] prefixSum = new long[n];
        long[] sum = new long[m];
        prefixSum[0] = A[0];
        for(int i=1;i<n;i++) {
            prefixSum[i] = prefixSum[i-1] + A[i];
        }
        for(int i=0;i<m;i++) {
            if(B[i][0]==0) {
                sum[i] = prefixSum[B[i][1]];
            } else {
                sum[i] = prefixSum[B[i][1]] - prefixSum[B[i][0]-1];
            }
        }
        return sum;
    }
    public static int[] sumOfEvenIndexedElements(int[] A) {
        int n = A.length;
        int[] prefixSumEven = new int[n];
        for(int i=0;i<n;i++) {
            if(i==0) {
                prefixSumEven[i] = A[i];
            } else if(i % 2 == 0) {
                prefixSumEven[i] = prefixSumEven[i-1] + A[i];
            }
        }
        return prefixSumEven;
    }
    public static int[] sumOfOddIndexedElements(int[] A) {
        int n = A.length;
        int[] prefixSumOdd = new int[n];
        for(int i=0;i<n;i++) {
            if(i==0) {
                prefixSumOdd[i] = 0;
            } else if(i % 2 != 0) {
                prefixSumOdd[i] = prefixSumOdd[i - 1] + A[i];
            }
        }
        return prefixSumOdd;
    }
}
