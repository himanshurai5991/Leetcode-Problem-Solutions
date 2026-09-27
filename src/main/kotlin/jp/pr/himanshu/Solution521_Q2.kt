package jp.pr.himanshu

class Solution521_Q2 {

    fun maxEqualAdjacentPairs(nums: IntArray): Int {
        var result = Int.MIN_VALUE
        val map = mutableMapOf<String,Int>()
        var i = 0
        var num1 = -1
        var num2 = -1
        while (i < nums.size-1) {
            if(nums[i]> nums[i + 1]) {
                val key = "${nums[i+1]}-${nums[i]}"
                map[key] = map.getOrDefault(key,0)+1
            } else if(nums[i]< nums[i + 1]) {
                val key = "${nums[i]}-${nums[i+1]}"
                map[key] = map.getOrDefault(key,0)+1
            } else {
                val key = "${nums[i]}-${nums[i+1]}"
                map[key] = map.getOrDefault(key,0)+1
            }
            i++
        }
        for ((key,value) in map.entries) {
            val parts = key.split("-")
            val first = parts[0].toInt()
            val sec = parts[1].toInt()
            if(value > result && first != sec ) {
                result = value

                num1 = first
                num2 = sec
            }
        }
        //println(num1)
        //println(num2)
        var count = 0

        for(i in 0 until nums.size-1) {
            if(nums[i] == num1 && nums[i+1] == num2) {
                count++
            } else if(nums[i] == num2 && nums[i+1] == num1) {
                count++
            } else if(nums[i] == nums[i+1]){
                count++
            }
        }
        return count


    }
}