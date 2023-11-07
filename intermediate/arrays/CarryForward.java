package intermediate.arrays;

import java.util.ArrayList;

public class CarryForward {

    // Given an array A of length N, return the sub array from B to C.
    public ArrayList<Integer> subArrayFromBtoC(ArrayList<Integer> A, int B, int C) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i=B;i<=C;i++) {
            arr.add(A.get(i));
        }
        return arr;
    }

    // Generate all sub arrays
    // You are given an array A of N integers.
    // Return a 2D array consisting of all the sub arrays of the array
    public ArrayList<ArrayList<Integer>> allSubArrays(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> arr= new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<A.size();i++){
            for(int j=i;j<A.size();j++){
                ArrayList<Integer> ar = new ArrayList<>();
                for(int k=i;k<=j;k++){
                    ar.add(A.get(k));
                }
                arr.add(ar);
            }
        }
        return arr;
    }

    // You have given a string A having Uppercase English letters.
    //You have to find how many times subsequence "AG" is there in the given string.
    public int findSpecialSubsequence(String A) {
        int count = 0;
        int ans = 0;
        for(int i=A.length()-1;i>=0;i--) {
            if(A.charAt(i) == 'G') {
                count++;
            }
            if(A.charAt(i) == 'A') {
                ans = ans + count;
            }
        }
        return ans;
    }
}
