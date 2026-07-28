/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int postIdx;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;
        return buildSubTree(inorder, postorder, 0, inorder.length - 1);
    }
    private TreeNode buildSubTree(int[] inorder, int[] postorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) return null;

        TreeNode node = new TreeNode(postorder[postIdx]);
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (postorder[postIdx] == inorder[i]) {
                postIdx--;

                node.right = buildSubTree(inorder, postorder, i + 1, inorderEnd);
                node.left = buildSubTree(inorder, postorder, inorderStart, i - 1);

                break;
            }
        }

        return node;
    }

}