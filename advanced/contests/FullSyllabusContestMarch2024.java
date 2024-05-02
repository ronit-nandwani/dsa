package advanced.contests;

import java.util.ArrayList;
import java.util.HashMap;

public class FullSyllabusContestMarch2024 {
    // Pangram Check
    
    // Problem Description
    // Given a sentence represented as an array A of strings that contains all lowercase alphabets.
    // Chech if it is a pangram or not.
    // A pangram is a unique sentence in which every letter of the lowercase alphabet is used at least once.


    // Problem Constraints
    // 1 <= |A| <= 105
    // 1 <= |Ai|<= 5


    // Input Format
    // Given an array of strings A.


    // Output Format
    // Return an integer.


    // Example Input
    // Input 1:
    // A = ["the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"]
    // Input 2:

    // A = ["bit", "scale"]



    // Example Output
    // Output 1:
    // 1
    // Output 2:

    // 0


    // Example Explanation
    // Explanation 1:
    // We can check that all english alphabets are present in given sentence.
    // Explanation 2:

    // Not all english alphabets are present.
    
    // Solution by me
    public int pangramCheck(String[] A) {
        int n = A.length;
        int[] arr = new int[26];
        for(int i = 0;i<n;i++) {
            for(int j=0;j<A[i].length();j++) {
                arr[A[i].charAt(j)-'a']++;
            }
        }
        for(int i=0;i<26;i++) {
            if(arr[i] == 0) {
                return 0;
            }
        }
        return 1;
    }

    // Solution by team
        public int pangramCheckByteam(ArrayList<String> A) {
            boolean vis[] = new boolean[26];
            for(String x: A){
                for(int i = 0; i<x.length(); ++i){
                    vis[x.charAt(i)-'a'] = true;
                }
            }
            for(int i = 0; i<26; ++i){
                if(!vis[i]){
                    return 0;
                }
            }
            return 1;
        }

    
    // Sort by Colour

    // Problem Description
    // Given an array with N objects colored red, white, or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

    // We will represent the colors as,

    // red -> 0
    // white -> 1
    // blue -> 2

    // Note: Using the library sort function is not allowed.



    // Problem Constraints
    // 1 <= N <= 1000000
    // 0 <= A[i] <= 2


    // Input Format
    // First and only argument of input contains an integer array A.


    // Output Format
    // Return an integer array in asked order


    // Example Input
    // Input 1 :
    //     A = [0, 1, 2, 0, 1, 2]
    // Input 2:

    //     A = [0]


    // Example Output
    // Output 1:
    //     [0, 0, 1, 1, 2, 2]
    // Output 2:

    //     [0]


    // Example Explanation
    // Explanation 1:
    //     [0, 0, 1, 1, 2, 2] is the required order.
    // Explanation 2:
    //     [0] is the required order
    
    // Solution by me

    public int[] sortColors(int[] A) {
        int n = A.length;
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        for(int i=0;i<n;i++) {
            if(hm.containsKey(A[i])) {
                hm.put(A[i],hm.get(A[i])+1);
            } else {
                hm.put(A[i],1);
            }
        }
        int k = 0;
        for(int i=0;i<=2;i++) {
            int freq = hm.getOrDefault(i,0);
            while(freq > 0) {
                A[k++] = i;
                freq--;
            }
        }
        return A;
    }

    // Solution by team
    public class Solution {

        public ArrayList<Integer> sortColors(ArrayList<Integer> A) {
            
            int zero = 0;
            int two = A.size() - 1;
            
            for (int i = 0; i <= two;) {
                if (A.get(i) == 0) {
                    int temp = A.get(zero);
                    A.set(zero, 0);
                    A.set(i, temp);
                    zero++;
                    i++;
                } else if (A.get(i) == 2) {
                    int temp = A.get(two);
                    A.set(two, 2);
                    A.set(i, temp);
                    two--;
                } else {
                    i++;
                }
            }
            return A;
        }
    }
}