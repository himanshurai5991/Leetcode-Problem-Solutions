class Solution3718 {

    fun missingMultiple(nums: IntArray, k: Int): Int {

        val list = mutableListOf<Int>()
        for(i in nums.indices) {
            if(nums[i] % k == 0) list.add(nums[i]/k)
        }

        val arr = list.toTypedArray()
        for(i in arr.indices) {
            if(arr[i] == -1) {
                continue
            }
            var index = arr[i]
            while(index >=0 && index <= arr.size && arr[index-1] != -1 ) {
                val temp = arr[index-1]
                arr[index-1] = -1
                index = temp

            }
        }
        for (i in arr.indices) {

            if(arr[i] != -1) {
                return k*(i+1)
            }
        }
        return k*(arr.size+1)

    }
}