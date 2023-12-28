package advanced.backtracking;

import java.util.ArrayList;
import java.util.Collections;

public class BackTracking {
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
