package intermediate.arrays;

import java.util.ArrayList;

public class CarryForward {

    // You are given an integer array A of size N.
    // You have to perform B operations. In one operation, you can remove either the
    // leftmost or the rightmost element of the array A.
    // Find and return the maximum possible sum of the B elements that were removed
    // after the B operations.
    // NOTE: Suppose B = 3, and array A contains 10 elements, then you can:
    // Remove 3 elements from front and 0 elements from the back, OR
    // Remove 2 elements from front and 1 element from the back, OR
    // Remove 1 element from front and 2 elements from the back, OR
    // Remove 0 elements from front and 3 elements from the back.
    public int pickFromBothSides(ArrayList<Integer> A, int B) {
        int n = A.size();
        int b = B, b2 = B, i = 0, j = n - 1, sum = 0;
        while (b > 0) {
            sum = sum + A.get(i++);
            b--;
        }

        int ans = sum;
        while (b2 > 0) {
            sum = sum - A.get(--i);
            sum = sum + A.get(j--);
            ans = Math.max(ans, sum);
            b2--;
        }
        return ans;
    }

    // Given an integer array A containing N distinct integers, you have to find all
    // the leaders in array A. An element is a leader if it is strictly greater than
    // all the elements to its right side.
    // NOTE: The rightmost element is always a leader.
    public ArrayList<Integer> leadersInAnArray(ArrayList<Integer> A) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        // since the element should be leader of all the element to its right side, we
        // can find it from the backwards
        int n = A.size();
        result.add(A.get(n - 1));
        int max = A.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            if (A.get(i) > max) {
                max = A.get(i);
                result.add(A.get(i));
            }
        }
        return result;
    }

    // Best Time to buy and sell stocks 1
    // Say you have an array, A, for which the ith element is the price of a given
    // stock on day i.
    // If you were only permitted to complete at most one transaction (ie, buy one
    // and sell one share of the stock), design an algorithm to find the maximum
    // profit.
    // Return the maximum possible profit.
    public int maxProfit(final int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int maxProfit = Integer.MIN_VALUE;
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < A.length; i++) {
            minPrice = Math.min(minPrice, A[i]);
            maxProfit = Math.max(maxProfit, A[i] - minPrice);
        }
        return maxProfit;
    }

    // Given an array A of length N, return the sub array from B to C.
    public ArrayList<Integer> subArrayFromBtoC(ArrayList<Integer> A, int B, int C) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = B; i <= C; i++) {
            arr.add(A.get(i));
        }
        return arr;
    }

    // Generate all sub arrays
    // You are given an array A of N integers.
    // Return a 2D array consisting of all the sub arrays of the array
    public ArrayList<ArrayList<Integer>> allSubArrays(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < A.size(); i++) {
            for (int j = i; j < A.size(); j++) {
                ArrayList<Integer> ar = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    ar.add(A.get(k));
                }
                arr.add(ar);
            }
        }
        return arr;
    }

    // You have given a string A having Uppercase English letters.
    // You have to find how many times subsequence "AG" is there in the given
    // string.
    public int findSpecialSubsequence(String A) {
        int count = 0;
        int ans = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) == 'G') {
                count++;
            }
            if (A.charAt(i) == 'A') {
                ans = ans + count;
            }
        }
        return ans;
    }
}
