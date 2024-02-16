package advanced.trees;

class TreeNode3 {
    int val;
    TreeNode3 left;
    TreeNode3 right;

    TreeNode3(int x) {
        val = x;
        left = null;
        right = null;
    }
}

class MinMaxPair {
    int min;
    int max;
    public MinMaxPair(int x, int y) {
        this.min = x;
        this.max = y;
    }
}

public class Trees3 {
    // Valid Binary Search Tree
    // You are given a binary tree represented by root A. You need to check if it is a Binary Search Tree or not.
    // Assume a BST is defined as follows:
    // 1) The left subtree of a node contains only nodes with keys less than the node's key.
    // 2) The right subtree of a node contains only nodes with keys greater than the node's key.
    // 3) Both the left and right subtrees must also be binary search trees.
    // Problem Constraints
    // 1 <= Number of nodes in binary tree <= 105
    // 0 <= node values <= 232-1
    // Input Format
    // First and only argument is head of the binary tree A.
    // Output Format
    // Return 0 if false and 1 if true.
    // Example Input
    // Input 1:
    // 1
    // /  \
    // 2    3
    // Input 2:
    // 2
    // / \
    // 1   3
    // Example Output
    // Output 1:
    // 0
    // Output 2:
    // 1
    // Example Explanation
    // Explanation 1:
    // 2 is not less than 1 but is in left subtree of 1.
    // Explanation 2:
    // Satisfies all conditions.
    int isBST = 1;
    public  MinMaxPair validBST(TreeNode root) {
        if(root == null) return new MinMaxPair(Integer.MAX_VALUE,Integer.MIN_VALUE);

        MinMaxPair pl = validBST(root.left);
        MinMaxPair pr = validBST(root.right);

        if(root.val <= pl.max || root.val > pr.min) {
            isBST = 0;
        }

        return new MinMaxPair(Math.min(Math.min(pl.min, pr.min), root.val),Math.max(Math.max(pl.max, pr.max), root.val));
    }
    public int isValidBST(TreeNode A) {
        validBST(A);
        return isBST;
    }
    // Solution by them
    // public int isValidBST(TreeNode A) {
    //     if (isValid(A, Long.MIN_VALUE, Long.MAX_VALUE))
    //         return 1;
    //     return 0;
    // }
    // public static boolean isValid(TreeNode A, long l, long r) {
    //     if (A == null)
    //         return true;
    //     if (A.val > l && A.val < r) {
    //         boolean left = isValid(A.left, l, A.val);
    //         boolean right = isValid(A.right, A.val, r);
    //         return (left && right);
    //     } else
    //         return false;
    // }

    // Sorted Array to Balanced BST
    // Given an array where elements are sorted in ascending order, convert it to a height Balanced Binary Search Tree (BBST).
    // Balanced tree : a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
    // Problem Constraints
    // 1 <= length of array <= 100000
    // Input Format
    // First argument is an integer array A.
    // Output Format
    // Return a root node of the Binary Search Tree.
    // Example Input
    // Input 1:
    // A : [1, 2, 3]
    // Input 2:
    // A : [1, 2, 3, 5, 10]
    // Example Output
    // Output 1:
    //     2
    //     /   \
    // 1     3
    // Output 2:
    //     3
    //     /   \
    // 2     5
    // /       \
    // 1         10
    // Example Explanation
    // Explanation 1:
    // You need to return the root node of the Binary Tree.
    public TreeNode3 sortedArrayToBinarySearchTree(int[] A, int l, int r) {
        if(l > r) return null;
        int mid = (l+r)/2;
        TreeNode3 root = new TreeNode3(A[mid]);
        root.left = sortedArrayToBinarySearchTree(A, l, mid-1);
        root.right = sortedArrayToBinarySearchTree(A, mid+1, r);
        return root;
    }

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
    public int searchInBST(TreeNode3 root, int B) {
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
