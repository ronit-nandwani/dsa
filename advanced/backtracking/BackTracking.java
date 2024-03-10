package advanced.backtracking;

import java.util.ArrayList;
import java.util.Collections;

public class BackTracking {
    // Generate all parenthesis 2

    // Given an integer A pairs of parentheses, write a function to generate all combinations of well-formed parentheses of length 2*A.
    // Problem Constraints
    // 1 <= A <= 10
    // Input Format
    // First and only argument is integer A.
    // Output Format
    // Return a sorted list of all possible parenthesis.
    // Example Input
    // Input 1:
    // A = 3
    // Input 2:
    // A = 1
    // Example Output
    // Output 1:
    // [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
    // Output 2:
    // [ "()" ]
    // Example Explanation
    // Explanation 1:
    // All paranthesis are given in the output list.
    // Explanation 2:
    // All paranthesis are given in the output list.
    ArrayList<String> list=new ArrayList<>();
    public ArrayList<String> generateParenthesis(int A) {
        generate(new StringBuilder(""),0,0,A);
        return list;
    }
    void generate(StringBuilder curr,int countOpen, int countClose,int n){
        if(countClose == countOpen && countOpen==n){
            list.add(curr.toString());
            return;
        }
        if(countOpen>n && countClose>countOpen){
            return;
        }
        if(countOpen<n){
            curr.append("(");
            generate(curr,countOpen+1,countClose,n);
            curr.deleteCharAt(curr.length()-1);
        }
        if(countClose<countOpen){
            curr.append(")");
            generate(curr,countOpen,countClose+1,n);
            curr.deleteCharAt(curr.length()-1);
        }
    }
    // Solution by team
    // public class Solution {
    //     ArrayList < String > ans;
    //     // cnt denotes remaining open brackets (
    //     // dif denotes the difference between open and closed brackets
    //     void solve(int cnt, int dif, int n, String s) {
    //         if (n == 0) {
    //             ans.add(s);
    //             return;
    //         }
    //         // Now we have 2 options. We can either add '(' to the string or add ')' to the string. 
    //         // Option 1. Add '(' 
    //         if (cnt > 0)
    //             solve(cnt - 1, dif + 1, n - 1, s + '(');
    //         // Option 2. Add ')'
    //         if (dif > 0)
    //             solve(cnt, dif - 1, n - 1, s + ')');
    //     }
    //     public ArrayList < String > generateParenthesis(int A) {
    //         ans = new ArrayList < > ();
    //         solve(A, 0, 2 * A, "");
    //         return ans;
    //     }
    // }

    // Helper function for subsets
    public static void findAllSubsets(ArrayList<Integer> A, int i, ArrayList<Integer> currSet, ArrayList<ArrayList<Integer>> ans) {
        if(i==A.size()) {
            ans.add(new ArrayList<Integer>(currSet));
            return;
        }
        findAllSubsets(A,i+1,currSet,ans);
        currSet.add(A.get(i));
        findAllSubsets(A,i+1,currSet,ans);
        currSet.remove(currSet.size()-1);
    }

    // Given a set of distinct integers A, return all possible subsets.
    //NOTE:
    //Elements in a subset must be in non-descending order.
    //The solution set must not contain duplicate subsets.
    //Also, the subsets should be sorted in ascending ( lexicographic ) order.
    //The initial list is not necessarily sorted.
    //Problem Constraints
    //1 <= |A| <= 16
    //INTMIN <= A[i] <= INTMAX
    //Input Format
    //First and only argument of input contains a single integer array A.
    //Output Format
    //Return a vector of vectors denoting the answer.
    //Example Input
    //Input 1:
    //A = [1]
    //Input 2:
    //A = [1, 2, 3]
    //Example Output
    //Output 1:
    //
    //[
    //    []
    //    [1]
    //]
    //Output 2:
    //
    //[
    // []
    // [1]
    // [1, 2]
    // [1, 2, 3]
    // [1, 3]
    // [2]
    // [2, 3]
    // [3]
    //]
    //Example Explanation
    //Explanation 1:
    // You can see that these are all possible subsets.
    //Explanation 2:
    public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> initial = new ArrayList<Integer>();
        Collections.sort(A);
        findAllSubsets(A,0,initial,ans);
        // Custom Comparator for lexological sorting
        Collections.sort(ans, (ArrayList < Integer > first, ArrayList < Integer > second) -> {
            for (int i = 0; i < first.size() && i < second.size(); i++) {
                if (first.get(i) < second.get(i))
                    return -1;
                if (first.get(i) > second.get(i))
                    return 1;
            }
            if (first.size() > second.size())
                return 1;
            return -1;
        });
        // My Custom Comparator
        // Collections.sort(ans, new Comparator<ArrayList<Integer>> () {
        //            @Override
        //            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
        //                int min = Math.min(o1.size(),o2.size());
        //                for(int i=0;i<min;i++)
        //                {
        //                     if(o1.get(i)!=o2.get(i))
        //                     {
        //                         return o1.get(i).compareTo(o2.get(i));
        //                     }
        //                }
        //                return (o1.size()<=o2.size())? -1:1;
        //            }
        //        });
        return ans;
    }

    public static void main(String[] args) {
        // Expected output: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(1);arr.add(2);arr.add(3);
        System.out.println(subsets(arr));
    }
}
