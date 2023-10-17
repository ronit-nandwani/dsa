public class Strings {

    // Given a string A, you are asked to reverse the string and return the reversed string.

    public String simpleReverse(String A) {
        // Optimized
        int n = A.length();
        char B[] = new char[n];
        for(int i=n-1;i>=0;i--) {
            B[n-i-1] = A.charAt(i);
        }
        return String.valueOf(B);
        // With more time complexity
//        int n = A.length();
//        String s = "";
//        for(int i=n-1;i>=0;i--) {
//            s += A.charAt(i);
//        }
//        return s;
    }


    // Toggle case
    // You are given a character string A having length N, consisting of only lowercase and uppercase latin letters.
    //You have to toggle case of each character of string A. For e.g 'A' is changed to 'a', 'e' is changed to 'E', etc.
    public String toggleCase(String A) {
        // Optimized
        char B[] = A.toCharArray();
        for(int i=0;i<B.length;i++) {
            if(B[i] >= 'A' && B[i] <= 'Z') {
                B[i] = (char)(B[i] + 32);
            } else {
                B[i] = (char)(B[i] - 32);
            }
        }
        return String.valueOf(B);
        // More time taking
//        String s = "";
//        for(int i=0;i<A.length();i++) {
//            if(A.charAt(i) >= 65 && A.charAt(i) <= 90) {
//                char ch = (char)(A.charAt(i) + 32);
//                s += ch;
//            } else {
//                char ch = (char)(A.charAt(i) - 32);
//                s += ch;
//            }
//        }
//        return s;
    }


}
