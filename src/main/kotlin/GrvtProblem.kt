import java.util.Currency

class GrvtProblem {


    // [1,2,3]

    // [[1,2,3], [1,3,2], [2,3,1], [2,1,3], [3,1,2], [3,2,1]]

    // [1] [1,2,3]
    // [1,2]
    // [1,2,3]
    // [1,3]
    // [1,3,2]

    val result = mutableListOf<IntArray>()
    fun solveProblem(array: IntArray): List<IntArray> {
        // Implement the logic to solve the problem based on the input
        for (i in array.indices) {
            val current = mutableListOf<Int>()
            current.add(array[i])
            runRecursive(array, current)
        }
//        val current = mutableListOf<Int>()
//        runRecursive(array, current,0)

        return result
    }

    fun runRecursive(array: IntArray, current: MutableList<Int>) {

        if(current.size == array.size) {
            val arr = current.toTypedArray().toIntArray()
            result.add(arr)
            return
        }

        for(i in 0 until array.size) {
            if(current.contains(array[i])) {
                continue
            }
            current.add(array[i]) // [1,2]
            runRecursive(array, current)
            current.removeAt(current.size-1) // [1,2,3]

            // println(startIndex)
        }
        return
        // [1,3]

    }



    // [1,2,3]
}

fun main() {
    val grvtProblem = GrvtProblem()
    val returned = grvtProblem.solveProblem(intArrayOf(1,2,3))
    for(i in returned.indices) {
        print(returned[i].contentToString())
    }
}

// 2power n
// n - no of eleme

// 1 - n - n
// 2 - n
// n*n*n --- n times
// npowern
// n

// [1,2]
// 0, [1 n ] , [2,n]  --- [n-1, n]
// n*n

// n*n!