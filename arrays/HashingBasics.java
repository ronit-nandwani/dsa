import java.util.*;

public class HashingBasics {

    // Given an array of integers A, find and return whether the given array contains a non-empty subarray with a sum equal to 0.
    //
    //If the given array contains a sub-array with sum zero return 1, else return 0.
    public int subArrayWithSumZero(int[] A) {
        // Just write your code below to complete the function. Required input is available to you as the function arguments.
        // Do not print the result or any output. Just return the result via this function.

        int n = A.length;
        long[] prefixSum = new long[n];
        prefixSum[0] = A[0];
        for(int i=1;i<n;i++) {
            prefixSum[i] = prefixSum[i-1] + A[i];
        }

        HashSet<Long> set = new HashSet<>();
        for(int i=0;i<n;i++) {
            if(prefixSum[i]==0) {
                return 1;
            }
            set.add(prefixSum[i]);
        }
        if(set.size()<n) {
            return 1;
        } else {
            return 0;
        }
    }

    // Given an array A of N integers, return the number of unique elements in the array.
    public int countDistinctElements(int[] A) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<A.length;i++) {
            set.add(A[i]);
        }
        return set.size();
    }

    // Given an integer array A of size N, find the first repeating element in it.
    //
    //We need to find the element that occurs more than once and whose index of the first occurrence is the smallest.
    //
    //If there is no repeating element, return -1.
    public int findFirstRepeatingElement(int[] A) {
        int n = A.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            if(map.containsKey(A[i])) {
                map.put(A[i],map.get(A[i])+1);
            } else {
                map.put(A[i],1);
            }
        }

        for(int i=0;i<n;i++) {
            if(map.get(A[i]) > 1) {
                return A[i];
            }
        }
        return -1;
    }

    // Given an array A. You have some integers given in the array B.
    //For the i-th number, find the frequency of B[i] in the array A and return a list containing all the frequencies.
    public int[] frequencyOfElementQuery(int[] A, int[] B) {
        int n = A.length;
        int nn = B.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            if(map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i])+1);
            } else {
                map.put(A[i], 1);
            }
        }
        for(int i=0;i<nn;i++) {
            // Can be done like this as well or in short hand
            // if(map.containsKey(B[i])) {
            //                B[i] = map.get(B[i]);
            //            } else {
            //                B[i] = 0;
            //            }
            B[i] = map.getOrDefault(B[i], 0);
        }
        return B;
    }
}
