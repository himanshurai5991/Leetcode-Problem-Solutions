class Solution3471 {

    fun largestInteger(nums: IntArray, k: Int): Int {
        var first = nums[0]
        for(i in 1 until k) {
            if(nums[i] == first) {
                first = -1
            }
        }
        // [1,2,3,4,5]  , k = 3

        var second = nums[nums.size - 1]
        for(i in nums.size - 2 downTo nums.size - k) {
            if(nums[i] == second) {
                second = -1
            }
        }
        if(second == -1 && first == -1) {
            return -1
        }

        return Math.max(first, second)

    }
}