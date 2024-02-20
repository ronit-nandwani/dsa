package advanced.trees;

import java.util.ArrayList;

public class Trees4 {

    // Morris Inorder Traversal - TC O(N) and SC O(1)

    // Given a binary tree, return the inorder traversal of its nodes' values.
    // NOTE: Using recursion and stack are not allowed.
    // Problem Constraints
    // 1 <= number of nodes <= 105
    // Input Format
    // First and only argument is root node of the binary tree, A.
    // Output Format
    // Return an integer array denoting the inorder traversal of the given binary tree.
    // Example Input
    // Input 1:
    // 1
    //     \
    //     2
    //     /
    // 3
    // Input 2:
    // 1
    // / \
    // 6   2
    //     /
    // 3
    // Example Output
    // Output 1:
    // [1, 3, 2]
    // Output 2:
    // [6, 1, 3, 2]
    // Example Explanation
    // Explanation 1:
    // The Inorder Traversal of the given tree is [1, 3, 2].
    // Explanation 2:
    // The Inorder Traversal of the given tree is [6, 1, 3, 2].

    public ArrayList<Integer> morrisInOrder(TreeNode A) {
        ArrayList<Integer> inArr = new ArrayList<Integer>();
        TreeNode curr = A;
        while(curr != null) {
            if(curr.left == null) {
                inArr.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode pre = curr.left;
                while(pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                if(pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    pre.right = null;
                    inArr.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return inArr;
    }
    // Solution by team
    // public ArrayList<Integer> morrisInOrder(TreeNode A) {
    //     ArrayList < Integer> ans = new ArrayList < Integer >();
    //     TreeNode current, pre;
    //     current = A;
    //     while (current != null) {
    //         if (current.left == null) {
    //             ans.add(current.val);
    //             current = current.right;
    //         }
    //         else {
    //             // Find the inorder predecessor of current
    //             pre = current.left;
    //             while (pre.right != null && pre.right != current)
    //                 pre = pre.right;
     
    //             // Make current as the right child of its inorder predecessor
    //             if (pre.right == null) {
    //                 pre.right = current;
    //                 current = current.left;
    //             }
    //             else {
    //                 pre.right = null;
    //                 ans.add(current.val);
    //                 current = current.right;
    //             } 
    //         }
    //     }     
    //     return ans;
    // }

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
