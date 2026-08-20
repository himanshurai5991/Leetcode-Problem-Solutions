import java.util.SortedSet
import java.util.TreeMap
import java.util.TreeSet

class Solution1356 {

    fun sortByBits(arr: IntArray): IntArray {
        val result = IntArray(arr.size)
        var start = 0
        val map = TreeMap<Int, MutableList<Int>>()
        for (i in arr.indices) {
            var count = 0
            val key = arr[i].toString(2)
            for (j in 0 until key.length) {
                if(key[j] == '1') {
                    count++
                }
            }
            map[count] = map.getOrDefault(count, mutableListOf()).apply { add(arr[i]) }
        }
        for (i in map.keys) {
            val list = map[i]!!
            list.sort()
            for (j in list.indices) {
                result[start++] = list[j]
            }
        }

        return result

    }
}