package advanced.trees;

public class Trees4 {
    // Kth Smallest in BST
    // Given a binary search tree represented by root A, write a function to find the Bth smallest element in the tree.
    // Problem Constraints
    // 1 <= Number of nodes in binary tree <= 100000
    // 0 <= node values <= 10^9
    // Input Format
    // First and only argument is head of the binary tree A.
    // Output Format
    // Return an integer, representing the Bth element.
    // Example Input
    // Input 1:    
    //             2
    //           /   \
    //          1    3
    // B = 2
    // Input 2:
    //             3
    //            /
    //           2
    //          /
    //         1
    // B = 1
    // Example Output
    // Output 1:
    //  2
    // Output 2:
    //  1
    // Example Explanation
    // Explanation 1:
    // 2nd element is 2.
    // Explanation 2:
    // 1st element is 1.
    int i = 0;
    public int inOrder(TreeNode A, int k) {
        if(A == null) return -1;
        int left = inOrder(A.left, k);
        if(left != -1) return left;
        if(++i==k) return A.val;
        return inOrder(A.right, k);
    }
    public int kthsmallest(TreeNode A, int B) {
        return inOrder(A,B);
    }
    // Solution by team
    // public class Solution {
    //     static int k = 0;
    //     public int kthsmallest(TreeNode A, int B) {
    //         k = B;
    //         return find(A);
    //     }
    //     public static int find(TreeNode root) {
    //         if (root == null)
    //             return -1;
    //         // We do an inorder traversal here. 
    //         int k1 = find(root.left);
    //         if (k == 0)
    //             return k1; // left subtree has k or more elements.
    //         k--;
    //         if (k == 0)
    //             return root.val; // root is the kth element.
    //         return find(root.right); // answer lies in the right node.
    //     }
    // }
}
