package intermediate.strings;

public class Strings {
    // Count Occurences
    // Find the number of occurrences of bob in string A consisting of lowercase English alphabets.
    // Problem Constraints
    // 1 <= |A| <= 1000
    // Input Format
    // The first and only argument contains the string A, consisting of lowercase English alphabets.
    // Output Format
    // Return an integer containing the answer.
    // Example Input
    // Input 1:
    //  "abobc"
    // Input 2:
    //  "bobob"
    // Example Output
    // Output 1:
    //  1
    // Output 2:
    //  2
    // Example Explanation
    // Explanation 1:
    //  The only occurrence is at second position.
    // Explanation 2:
    //  Bob occures at first and third position.
    public int countOccurences(String A) {
        String targetString ="bob";
        int occurance=0;
        for (int i=0;i<A.length()-2;i++){
            String subString=   A.substring(i,i+3);
            if(targetString.equals(subString)){
                    occurance++;
            }
        }
        return occurance;
    }


    // You are given two lowercase strings A and B each of length N. Return 1 if they are anagrams to each other and 0 if not.
    //Note : Two strings A and B are called anagrams to each other if A can be formed after rearranging the letters of B.
    //Problem Constraints
    //1 <= N <= 105
    //A and B are lowercase strings
    //Input Format
    //Both arguments A and B are a string.
    //Output Format
    //Return 1 if they are anagrams and 0 if not
    //Example Input
    //Input 1:
    //A = "cat"
    //B = "bat"
    //Input 2:
    //A = "secure"
    //B = "rescue"
    //Example Output
    //Output 1:
    //0
    //Output 2:
    //1
    //Example Explanation
    //For Input 1:
    //The words cannot be rearranged to form the same word. So, they are not anagrams.
    //For Input 2:
    //They are an anagram.
    public int checkAnagrams(String A, String B) {
        //Optimized
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for(int i = 0 ; i < A.length() ; i++){
            freq1[A.charAt(i) - 'a']++;
            freq2[B.charAt(i) - 'a']++;
        }
        for(int i = 0 ; i < 26 ; i++){
            if(freq1[i] != freq2[i]){
                return 0;
            }
        }
        return 1;


        // Not Optimized
        // int countA = 0;
        // int countB = 0;

        // int n = A.length();

        // for(int i=0; i<n; i++)
        // {
        //     countA = countA + A.charAt(i);
        //     countB = countB + B.charAt(i);
        // }

        // if(countA == countB)
        // {
        //     return 1;
        // }

        // return 0;
    }
    // Given a string A of size N, find and return the longest palindromic substring in A.
    //Substring of string A is A[i...j] where 0 <= i <= j < len(A)
    //Palindrome string:
    //A string which reads the same backwards. More formally, A is palindrome if reverse(A) = A.
    //In case of conflict, return the substring which occurs first ( with the least starting index).
    public static int expand(char s[], int c1, int c2) { // helper for longest palindromic
        while(c1 >= 0 && c2<s.length && s[c1] == s[c2]) {
            c1--;c2++;
        }
        return c2-c1-1;
    }
    public static String longestPalindrome(String A) {
        int ans = 0;
        String s = "";
        char[] c = A.toCharArray();
        int n = A.length();
        for(int i=0;i<n;i++) {
            int c1=i,c2=i;
            int ac = expand(c,c1,c2);
            if (ac > ans) {
                ans = ac;
                s = "";
                int start = c1-ans/2,end = 1+c1+ans/2;
                for(int j=start;j<end;j++) {
                    s = s + c[j];
                }
            }
        }
        for(int i=0;i<n-1;i++) {
            int c1=i,c2=i+1;
            int ac = expand(c,c1,c2);
            if (ac > ans) {
                ans = ac;
                s = "";
                int start = 1+c1-ans/2,end = c2+ans/2;
                for(int j=start;j<end;j++) {
                    s = s + c[j];
                }
            }
        }
        return s;
    }

    // You are given a string A of size N.
    //Return the string A after reversing the string word by word.
    //NOTE:
    //A sequence of non-space characters constitutes a word.
    //Your reversed string should not contain leading or trailing spaces, even if it is present in the input string.
    //If there are multiple spaces between words, reduce them to a single space in the reversed string.
    public String reverseStringWordByWord(String A) {
        String[] arr = A.split(" ");
        String ans = "";
        for(int i=arr.length-1;i>=0;i--) {
            if(arr[i].length() > 0) {
                if(ans.length() != 0) {
                    ans = ans += " ";
                }
                ans = ans + arr[i];
            }
        }
        return ans;
    }

    // Akash likes playing with strings. One day he thought of applying following operations on the string in the given order:
    //Concatenate the string with itself.
    //Delete all the uppercase letters.
    //Replace each vowel with '#'.
    //You are given a string A of size N consisting of lowercase and uppercase alphabets. Return the resultant string after applying the above operations.
    //NOTE: 'a' , 'e' , 'i' , 'o' , 'u' are defined as vowels.
    public String stringOperations(String A) {
        String b = A.replaceAll("[A-Z]","");
        char[] arr = b.toCharArray();
        String ans = "";
        for(int i=0;i<b.length();i++){
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                arr[i] = '#';
            }
        }
        String c = String.valueOf(arr);
        return c+c;
    }

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

    public static void main(String[] args) {
        System.out.print(longestPalindrome("abb"));
    }

}
