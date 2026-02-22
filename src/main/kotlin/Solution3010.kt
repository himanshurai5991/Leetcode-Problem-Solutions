class Solution3010 {

    fun minimumCost(nums: IntArray): Int {
        var first = Int.MAX_VALUE
        var second = Int.MAX_VALUE
        for (i in 1 until nums.size) {
            if (nums[i] <= first) {
                second = first
                first = nums[i]
            } else if (nums[i] < second) {
                second = nums[i]
            }
        }
        return first + second + nums[0]
    }


}