package advanced.trees;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class Trees3 {
    // Search in a Binary Search Tree
    // Given a Binary Search Tree A. Check whether there exists a node with value B
    // in the BST.
    // Problem Constraints
    // 1 <= Number of nodes in binary tree <= 105
    // 0 <= B <= 106
    // Input Format
    // First argument is a root node of the binary tree, A.
    // Second argument is an integer B.
    // Output Format
    // Return 1 if such a node exist and 0 otherwise
    // Example Input
    // Input 1:
    // 15
    // / \
    // 12 20
    // / \ / \
    // 10 14 16 27
    // /
    // 8
    // B = 16
    // Input 2:
    // 8
    // / \
    // 6 21
    // / \
    // 1 7
    // B = 9
    // Example Output
    // Output 1:
    // 1
    // Output 2:
    // 0
    // Example Explanation
    // Explanation 1:
    // Node with value 16 is present.
    // Explanation 2:
    // There is no node with value 9.
    public int searchInBST(TreeNode root, int B) {
        if (root == null)
            return 0;
        if (root.val == B)
            return 1;
        if (root.val > B) {
            return searchInBST(root.left, B);
        } else {
            return searchInBST(root.right, B);
        }
    }
}
