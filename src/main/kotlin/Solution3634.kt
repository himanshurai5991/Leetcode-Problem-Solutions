class Solution3634 {

    fun minRemoval(nums: IntArray, k: Int): Int {
        nums.sort()
        var left = 0
        var right = 1
        var maxLength = Int.MAX_VALUE

        while (right < nums.size) {
            while (nums[right].toLong() > (k.toLong()*nums[left].toLong())) {
                maxLength = minOf(maxLength, nums.size -(right - left))
                left++
            }
            right++
        }
        maxLength = minOf(maxLength, nums.size -(right - left ))
        if (maxLength == Int.MAX_VALUE) {
            return 0
        }

        return maxLength

    }
}