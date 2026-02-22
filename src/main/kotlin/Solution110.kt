class Solution110 {

    fun isBalanced(root: TreeNode?): Boolean {

        return checkBalance(root) != -1
    }

    private fun checkBalance(node: TreeNode?): Int {
        if (node == null) return 0

        val leftHeight = checkBalance(node.left)
        if (leftHeight == -1) return -1

        val rightHeight = checkBalance(node.right)
        if (rightHeight == -1) return -1

        if (kotlin.math.abs(leftHeight - rightHeight) > 1) return -1

        return kotlin.math.max(leftHeight, rightHeight) + 1
    }
}

class TreeNode(var `val`: Int) {
var left: TreeNode? = null
var right: TreeNode? = null
}