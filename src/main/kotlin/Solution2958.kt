class Solution2958 {

    fun maxSubarrayLength(nums: IntArray, k: Int): Int {
        var result = 0

        val map = mutableMapOf<Int, Pair<Int,Int>>()
        var last = 0

        for(i in nums.indices) {
            if(map.containsKey(nums[i])) {
                val current = map[nums[i]]!!
                var freq = current.first
                var index = current.second
                if(freq == k) {
                    result = result.coerceAtLeast(i - last)
                    while (index < i && nums[index] != nums[i]) {
                        index++
                    }
                    index++
                    map[nums[i]] = Pair(freq, index)
                    last = Math.max(last,index)

                } else {
                    freq++
                    map[nums[i]] = Pair(freq, index)
                }
            } else {
                map[nums[i]] = Pair(1, i)
            }
            // println(map)
            // println(last)
        }
        result = result.coerceAtLeast(nums.size - last)
        // if (result == 0) {
        //     return nums.size
        // }
        return result.coerceAtMost(nums.size)

    }
}