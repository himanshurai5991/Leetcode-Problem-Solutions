class Solution3116 {

    fun findKthSmallest(coins: IntArray, k: Int): Long {
        coins.sort()
        var min = Long.MAX_VALUE
        var max = Long.MIN_VALUE

        for (i in 0 until coins.size) {
            min = min.coerceAtMost(coins[i].toLong())
            max = max.coerceAtLeast((min * k).toLong())
        }
        while (min <= max) {
            val mid = ((max+min))/2
            println(mid)
            var count = 0L
            val map = mutableMapOf<Int, Int>()
            for (j in 0 until coins.size) {
                var curr = mid/coins[j]
                if (map.containsKey(j)) {
                    continue
                }
                for (k in j+1 until coins.size) {
                    var multiply = coins[j] * coins[k]
                    multiply /= gcd(coins[k], coins[j])
                    if(coins[k]%coins[j] != 0) {
                        curr -= (mid/multiply)
                    } else {
                        map[k] = 1
                    }
                }
                count += curr
            }
            println(count)
            // if(count == k.toLong()){
            //     return mid
            // }

            if (count < k) {
                min = mid+1
            } else  {
                max = mid-1
            }

        }
        return max+1

        // 3,6,9,12,15,18 - 6
        // 6,12,18 - 3 == 0
        // 9,18 - 2 == 0

        // 2,4,6,8,10,12,14,16 - 8
        // 5,10,15 - 3 == 2


    }

    fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b

        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }

        return x
    }
}