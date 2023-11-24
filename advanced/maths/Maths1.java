package advanced.maths;

import java.util.HashMap;

public class Maths1 {
    // Given an array of integers A and an integer B, find and return the number of pairs in A whose sum is divisible by B.
    // Since the answer may be large, return the answer modulo (109 + 7).
    // Note: Ensure to handle integer overflow when performing the calculations.
    public int pairSumDivisibleByM(int[] A, int B) {
        int n = A.length;
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        int ans = 0;
        for(int i=0;i<n;++i) {
            int val = A[i]%B;
            int pair = 0;
            if(val == 0) {
                pair = 0;
            } else {
                pair = B-val;
            }
            boolean p = freq.containsKey(pair);
            boolean v = freq.containsKey(val);
            if(p) {
                int c = freq.get(pair);
                ans = ((ans + c) % 1000000007);
            }
            if(v) {
                int c = freq.get(val);
                freq.put(val,c+1);
            } else {
                freq.put(val,1);
            }
        }
        return ans;
    }
}
