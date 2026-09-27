package jp.pr.himanshu

import java.util.PriorityQueue

class Solution521_Q1 {

    fun rearrangeArray(nums: IntArray): IntArray {
        val map = mutableMapOf<Int,Int>()
        for(i in nums.indices) {
            map[nums[i]] = map.getOrDefault(nums[i],0)+1
        }
        //println(map)

        val list = MutableList(nums.size+1) { PriorityQueue<Int>() }

        for(i in 1 until nums.size+1) {
            val current = list[i]
            for((key,value) in map.entries) {
                if(value >= i) {
                    current.add(key)
                }
            }
            list[i] = current
        }
        //println(list)

        val result = mutableListOf<Int>()

        for(i in 0 until nums.size+1) {
            val current = list[i]
            if(current.isNotEmpty()) {
                while(current.isNotEmpty()) {
                    result.add(current.remove())
                }
            }
        }
        return result.toIntArray()
    }
}