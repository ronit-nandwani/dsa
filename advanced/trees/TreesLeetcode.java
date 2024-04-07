package advanced.trees;

import java.util.ArrayList;
import java.util.List;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class TreesLeetcode {
    // 1382. Balance a Binary Search Tree
    // Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

    // A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

    

    // Example 1:


    // Input: root = [1,null,2,null,3,null,4,null,null]
    // Output: [2,1,3,null,null,null,4]
    // Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
    // Example 2:


    // Input: root = [2,1,3]
    // Output: [2,1,3]
    

    // Constraints:

    // The number of nodes in the tree is in the range [1, 104].
    // 1 <= Node.val <= 105
    public TreeNode balanceBST(TreeNode root) {
        inOrder(root);
        return bst(0, ordered.size() - 1);
    }
    private TreeNode bst(int start, int end) {
        if (start > end) return null;
        else if (start == end) {
            TreeNode ret = ordered.get(start);
            ret.left = null;
            ret.right = null;
            return ret;
        } else {
            int mid = start + (end - start) / 2;
            TreeNode ret = ordered.get(mid);
            ret.left = bst(start, mid - 1);
            ret.right = bst(mid + 1, end);
            return ret;
        }
    }
    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        ordered.add(node);
        inOrder(node.right);
    }
    private List<TreeNode> ordered = new ArrayList<>();


    // 1038. Binary Search Tree to Greater Sum Tree
    // Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
    // As a reminder, a binary search tree is a tree that satisfies these constraints:
    // The left subtree of a node contains only nodes with keys less than the node's key.
    // The right subtree of a node contains only nodes with keys greater than the node's key.
    // Both the left and right subtrees must also be binary search trees.

    // Example 1:

    // Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
    // Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
    // Example 2:

    // Input: root = [0,null,1]
    // Output: [1,null,1]
    
    // Constraints:

    // The number of nodes in the tree is in the range [1, 100].
    // 0 <= Node.val <= 100
    // All the values in the tree are unique.
    
    // Note: This question is the same as 538: https://leetcode.com/problems/convert-bst-to-greater-tree/
    int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        
        // Traverse right subtree
        traverse(node.right);
        
        // Update the current node's value with the running sum
        sum += node.val;
        node.val = sum;
        
        // Traverse left subtree
        traverse(node.left);
    }
}
