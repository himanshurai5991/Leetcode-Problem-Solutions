class Solution3069 {

    fun resultArray(nums: IntArray): IntArray {
        val arr1 = mutableListOf<Int>()
        val arr2 = mutableListOf<Int>()
        arr1.add(nums[0])
        arr2.add(nums[1])
        for (i in 2 until nums.size) {
            if(arr1[arr1.size-1] > arr2[arr2.size-1]) {
                arr1.add(nums[i])
            } else {
                arr2.add(nums[i])
            }
        }

        return (arr1 + arr2).toIntArray()
    }
}