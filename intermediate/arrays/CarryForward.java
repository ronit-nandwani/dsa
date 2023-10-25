public class CarryForward {
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
