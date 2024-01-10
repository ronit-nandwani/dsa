package intermediate.hashing_basics;

import java.util.*;

public class HashingBasics {
    // Count Unique Elements
    // You are given an array A of N integers. Return the count of elements with
    // frequncy 1 in the given array.
    // Problem Constraints
    // 1 <= N <= 105
    // 1 <= A[i] <= 109
    // Input Format
    // First argument A is an array of integers.
    // Output Format
    // Return an integer.
    // Example Input
    // Input 1:
    // A = [3, 4, 3, 6, 6]
    // Input 2:
    // A = [3, 3, 3, 9, 0, 1, 0]
    // Example Output
    // Output 1:
    // 1
    // Output 2:
    // 2
    // Example Explanation
    // Explanation 1:
    // Only the element 4 has a frequency 1.
    // Explanation 2:
    // The elements 9 and 1 has a frequncy 1.
    public int countUniqueElemnts(int[] A) {
        int count = 0;
        int n = A.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(A[i])) {
                int freq = map.get(A[i]);
                map.put(A[i], freq + 1);
            } else {
                map.put(A[i], 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (map.get(A[i]) == 1) {
                count++;
            }
        }
        return count;
    }

    // Common Elements
    // Given two integer arrays, A and B of size N and M, respectively. Your task is
    // to find all the common elements in both the array.
    // NOTE:
    // Each element in the result should appear as many times as it appears in both
    // arrays.
    // The result can be in any order.
    // Problem Constraints
    // 1 <= N, M <= 105
    // 1 <= A[i] <= 109
    // Input Format
    // First argument is an integer array A of size N.
    // Second argument is an integer array B of size M.
    // Output Format
    // Return an integer array denoting the common elements.
    // Example Input
    // Input 1:
    // A = [1, 2, 2, 1]
    // B = [2, 3, 1, 2]
    // Input 2:
    // A = [2, 1, 4, 10]
    // B = [3, 6, 2, 10, 10]
    // Example Output
    // Output 1:
    // [1, 2, 2]
    // Output 2:
    // [2, 10]
    // Example Explanation
    // Explanation 1:
    // Elements (1, 2, 2) appears in both the array. Note 2 appears twice in both
    // the array.
    // Explantion 2:
    // Elements (2, 10) appears in both the array.
    public ArrayList<Integer> commonElements(ArrayList<Integer> A, ArrayList<Integer> B) {
        // Just write your code below to complete the function. Required input is
        // available to you as the function arguments.
        // Do not print the result or any output. Just return the result via this
        // function.
        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < A.size(); i++) {
            hm.put(A.get(i), hm.getOrDefault(A.get(i), 0) + 1);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < B.size(); i++) {
            if (hm.containsKey(B.get(i))) {
                ans.add(B.get(i));
                if (hm.get(B.get(i)) == 1) {
                    hm.remove(B.get(i));
                    continue;
                }
                hm.put(B.get(i), hm.getOrDefault(B.get(i), 0) - 1);
            }
        }

        return ans;
    }

    // Find the count of the sub arrays in the array which sums to zero. Since the
    // answer can be very large, return the remainder on dividing the result with
    // 109+7
    public int countSubArrayWithZeroSum(int[] A) {
        int n = A.length;
        long[] prefixSum = new long[n];
        prefixSum[0] = A[0];
        long count = 0;
        HashMap<Long, Integer> hm = new HashMap<Long, Integer>();
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }
        long sum = 0;
        hm.put(sum, 1);
        for (int i = 0; i < n; i++) {
            if (hm.containsKey(prefixSum[i])) {
                int c = hm.get(prefixSum[i]);
                count += c;
                c++;
                hm.put(prefixSum[i], c);
            } else {
                hm.put(prefixSum[i], 1);
            }
        }
        return (int) (count % (1000000000 + 7));
    }

    // Given an array of integers A, find and return whether the given array
    // contains a non-empty subarray with a sum equal to 0.
    //
    // If the given array contains a sub-array with sum zero return 1, else return
    // 0.
    public int subArrayWithSumZero(int[] A) {
        // Just write your code below to complete the function. Required input is
        // available to you as the function arguments.
        // Do not print the result or any output. Just return the result via this
        // function.

        int n = A.length;
        long[] prefixSum = new long[n];
        prefixSum[0] = A[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }

        HashSet<Long> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (prefixSum[i] == 0) {
                return 1;
            }
            set.add(prefixSum[i]);
        }
        if (set.size() < n) {
            return 1;
        } else {
            return 0;
        }
    }

    // Given an array A of N integers, return the number of unique elements in the
    // array.
    public int countDistinctElements(int[] A) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        return set.size();
    }

    // Given an integer array A of size N, find the first repeating element in it.
    //
    // We need to find the element that occurs more than once and whose index of the
    // first occurrence is the smallest.
    //
    // If there is no repeating element, return -1.
    public int findFirstRepeatingElement(int[] A) {
        int n = A.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (map.get(A[i]) > 1) {
                return A[i];
            }
        }
        return -1;
    }

    // Given an array A. You have some integers given in the array B.
    // For the i-th number, find the frequency of B[i] in the array A and return a
    // list containing all the frequencies.
    public int[] frequencyOfElementQuery(int[] A, int[] B) {
        int n = A.length;
        int nn = B.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
        }
        for (int i = 0; i < nn; i++) {
            // Can be done like this as well or in short hand
            // if(map.containsKey(B[i])) {
            // B[i] = map.get(B[i]);
            // } else {
            // B[i] = 0;
            // }
            B[i] = map.getOrDefault(B[i], 0);
        }
        return B;
    }
}
