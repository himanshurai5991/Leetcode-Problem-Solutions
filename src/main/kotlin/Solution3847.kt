import java.util.Stack

class Solution3847 {

    fun scoreDifference(nums: IntArray): Int {
        var stack = Stack<Int>()
        stack.push(nums[0])
        var score1 = 0
        var score2 = 0
        var currentPlayer = true

        for (i in nums.indices) {
            if(((i+1)%6 ==0)) {
                currentPlayer = !currentPlayer
            }
            if(nums[i]%2 !=0) {
                currentPlayer = !currentPlayer
            }
            if(currentPlayer) {
                score1 += nums[i]
            } else {
                score2 += nums[i]
            }
        }

        return (score1 - score2)
    }

}