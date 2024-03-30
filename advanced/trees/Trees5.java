package advanced.trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class TreeLinkNode {
        int val;
     TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

public class Trees5 {
    // Next Pointer Binary tree

    // Given a binary tree,
    // Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
    // Initially, all next pointers are set to NULL.
    // Assume perfect binary tree.
    // Problem Constraints
    // 1 <= Number of nodes in binary tree <= 100000
    // 0 <= node values <= 10^9
    // Input Format
    // First and only argument is head of the binary tree A.
    // Output Format
    // Return the head of the binary tree after the changes are made.
    // Example Input
    // Input 1:
    //     1
    //     /  \
    // 2    3
    // Input 2:
    //         1
    //     /  \
    //     2    5
    //     / \  / \
    //     3  4  6  7
    // Example Output
    // Output 1:
    //         1 -> NULL
    //     /  \
    //     2 -> 3 -> NULL
    // Output 2:
    //         1 -> NULL
    //     /  \
    //     2 -> 5 -> NULL
    //     / \  / \
    //     3->4->6->7 -> NULL
    // Example Explanation
    // Explanation 1:
    // Next pointers are set as given in the output.
    // Explanation 2:

    // Next pointers are set as given in the output.
    public void connect(TreeLinkNode root) {
        TreeLinkNode temp = root;
        TreeLinkNode last = root;
        TreeLinkNode r = root;

        while(r != null) {
            if(r.left != null) {
                temp.next = r.left;
                temp = temp.next;
            }

            if(r.right != null) {
                temp.next = r.right;
                temp = temp.next;
            }

            if(r != last) {
                r = r.next;
            } else {
                r = last.next;
                last.next = null;
                last = temp;
            }
        }
    }
    // Solution by team
    // public class Solution {
    //     public void connect(TreeLinkNode root) {
    //         if (root == null)
    //             return;

    //         Queue < TreeLinkNode > q = new LinkedList < TreeLinkNode > ();
    //         q.offer(root);

    //         while (q.size() > 0) {
    //             int n = q.size();
    //             for (int i = 0; i < n; i++) {
    //                 TreeLinkNode front = q.poll();
    //                 if (i == n - 1) {
    //                     front.next = null;
    //                 } else {
    //                     front.next = q.peek();
    //                 }
    //                 if (front.left != null)
    //                     q.offer(front.left);
    //                 if (front.right != null)
    //                     q.offer(front.right);
    //             }
    //         }
    //         return;
    //     }
    // }

    // Diameter of binary Tree
    
    // Given a Binary Tree A consisting of N integer nodes, you need to find the diameter of the tree.
    // The diameter of a tree is the number of edges on the longest path between two nodes in the tree.
    // Problem Constraints
    // 0 <= N <= 105
    // Input Format
    // First and only Argument represents the root of binary tree A.
    // Output Format
    // Return an single integer denoting the diameter of the tree.
    // Example Input
    // Input 1:
    //         1
    //         /   \
    //         2     3
    //     / \
    //     4   5
    // Input 2:
    //             1
    //         /   \
    //         2     3
    //         / \     \
    //     4   5     6
    // Example Output
    // Output 1:
    // 3
    // Output 2:
    // 4
    // Example Explanation
    // Explanation 1:
    // Longest Path in the tree is 4 -> 2 -> 1 -> 3 and the number of edges in this path is 3 so diameter is 3.
    // Explanation 2:
    // Longest Path in the tree is 4 -> 2 -> 1 -> 3 -> 6 and the number of edges in this path is 4 so diameter is 4.
    int ans = 0;
    public int height(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int l = height(root.left);
        int r = height(root.right);
        ans = Math.max(ans, l+r+1);
        return Math.max(l,r)+1;
    }
    public int diameterOfBinaryTree(TreeNode A) {
        height(A);
        return ans-1;
    }
    // Solution by team
    // public class Solution {
    //     static int diameter;
    //     public int solve(TreeNode A) {
    //         diameter = 0;
    //         height(A);
    //         return diameter;
    //     }
    //     public static int height(TreeNode root) {
    //         if (root == null)
    //             return -1;
    //         int leftHeight = height(root.left);
    //         int rightHeight = height(root.right);
    //         diameter = Math.max(diameter, 2 + leftHeight + rightHeight);
    //         return 1 + Math.max(leftHeight, rightHeight);
    //     }
    // }


    // Equal tree Partition

    // Given a binary tree A. Check whether it is possible to partition the tree to two trees which have equal sum of values after removing exactly one edge on the original tree.
    // Problem Constraints
    // 1 <= size of tree <= 100000
    // 0 <= value of node <= 109
    // Input Format
    // First and only argument is head of tree A.
    // Output Format
    // Return 1 if the tree can be partitioned into two trees of equal sum else return 0.
    // Example Input
    // Input 1:
    //                 5
    //             /  \
    //             3    7
    //             / \  / \
    //             4  6  5  6
    // Input 2:
    //                 1
    //             / \
    //             2   10
    //                 / \
    //                 20  2
    // Example Output
    // Output 1:
    // 1
    // Output 2:
    // 0
    // Example Explanation
    // Explanation 1:
    // Remove edge between 5(root node) and 7: 
    //         Tree 1 =                                               Tree 2 =
    //                         5                                                     7
    //                     /                                                     / \
    //                     3                                                     5   6    
    //                     / \
    //                     4   6
    //         Sum of Tree 1 = Sum of Tree 2 = 18
    // Explanation 2:
    // The given Tree cannot be partitioned.
    public long treeSum(TreeNode root) {
        if(root == null) return 0;
        return root.val + treeSum(root.left) + treeSum(root.right); // Post order
    }

    public boolean check(TreeNode root, long sum) {
        if(root == null) return false;
        if(check(root.left,sum) || check(root.right,sum)) return true;
        long left = treeSum(root.left);
        long right = treeSum(root.right);
        return ((left+right+root.val)==sum);
    }

    public long solve(TreeNode A) {
        long totalSum = treeSum(A);
        if(totalSum % 2 == 1) {
            return 0;
        }
        boolean f = check(A, totalSum/2);
        if(f == true) {
            return 1;
        }
        return 0;
    }
    // Solution by team
    //     public class Solution {
    //     public int solve(TreeNode a) {
    //         Map < Long, Integer > map = new HashMap < > ();
    //         long tot = populate(a, map);
    //         // since total sum can also be zero
    //         if (tot == 0) 
    //             return map.getOrDefault(tot, 0) > 1 ? 1 : 0;
    //         return tot % 2 == 0 && map.containsKey(tot / 2) ? 1 : 0;
    //     }
    //     public long populate(TreeNode a, Map < Long, Integer > map) {
    //         if (a == null) 
    //             return 0;
    //         long sum = a.val + populate(a.left, map) + populate(a.right, map);
    //         map.put(sum, map.getOrDefault(sum, 0) + 1);
    //         return sum;
    //     }
    // }

    // Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
    //Problem Constraints
    //1 <= number of nodes <= 105
    //-100000 <= B, value of nodes <= 100000
    //Input Format
    //First argument is a root node of the binary tree, A.
    //Second argument is an integer B denoting the sum.
    //Output Format
    //Return 1, if there exist root-to-leaf path such that adding up all the values along the path equals the given sum. Else, return 0.
    //Example Input
    //Input 1:
    // Tree:    5
    //         / \
    //        4   8
    //       /   / \
    //      11  13  4
    //     /  \      \
    //    7    2      1
    // B = 22
    //Input 2:
    // Tree:    5
    //         / \
    //        4   8
    //       /   / \
    //     -11 -13  4
    // B = -1
    //Example Output
    //Output 1:
    // 1
    //Output 2:
    // 0
    //Example Explanation
    //Explanation 1:
    // There exist a root-to-leaf path 5 -> 4 -> 11 -> 2 which has sum 22. So, return 1.
    //Explanation 2:
    // There is no path which has sum -1.
    public int pathSum(TreeNode root, int B) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            if(root.val == B) {
                return 1;
            } else {
                return 0;
            }
        }
        if(pathSum(root.left,B-root.val) == 1 || pathSum(root.right,B-root.val) == 1) {
            return 1;
        }
        return 0;
    }
    // Solution provided for path sum
    // public int hasPathSum(TreeNode A, int B) {
    //        boolean status = sum(A, 0, B);
    //        return status ? 1 : 0;
    //    }
    //    public boolean sum(TreeNode A, int curSum, int reqSum) {
    //        if (A == null) {
    //            return false;
    //        }
    //        if (A.left == null && A.right == null) {
    //            curSum += A.val;
    //            if (curSum == reqSum)
    //                return true;
    //            return false;
    //        }
    //        int sum = curSum + A.val;
    //        return sum(A.left, sum, reqSum) || sum(A.right, sum, reqSum);
    //    }
}
