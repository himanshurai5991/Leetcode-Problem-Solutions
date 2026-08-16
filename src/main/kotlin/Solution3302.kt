class Solution3302 {

    fun validSequence(word1: String, word2: String): IntArray {
        val result = IntArray(word2.length)
        if(word2.length == 1) {
            result[0] = 0
            return result
        }
        val map1  = mutableMapOf<Int, Int>()
        val map2 = mutableMapOf<Int, Int>()
        for(i in 0 until word1.length) {
            map1[word1[i]-'a'] = map1.getOrDefault(word1[i]-'a', 0) + 1
        }
        for(i in 0 until word2.length) {
            map2[word2[i]-'a'] = map2.getOrDefault(word2[i]-'a', 0) + 1
        }

        val keys = map2.keys
        var count = 0
        for(i in keys) {
            if(!map1.containsKey(i)) {
                if(map2[i]!! > 1) {
                    return IntArray(0)
                } else {
                    count++
                }
            }
        }
        if(count > 1) {
            return IntArray(0)
        }

        //  println(map1)
        //  println(map2)

        for(i in 0 until word1.length) {
            if(word1[i] != word2[0]) {
                val current = checkIsValid(word1, word2, i+1, true)
                if(current.isNotEmpty()) {
                    return current
                }
            } else {
                val current = checkIsValid(word1, word2, i+1, false)
                if(current.isNotEmpty()) {
                    return current
                }
            }
        }
        return IntArray(0)
    }

    // abcdadbeedc dabc
    // [3,4,5,10]
    // [3,4,6,10]
    // [0,4,6,10]

    fun checkIsValid(word1: String, word2: String, startIndex: Int, isUsed: Boolean): IntArray {
        var j = startIndex
        var used = isUsed
        val result = IntArray(word2.length)
        result[0] = startIndex-1
        println(used)
        println(j)
        for (i in 1 until word2.length) {
            if(j == word1.length) {
                return IntArray(0)
            }
            if(word1[j] == word2[i]) {
                result[i] = j
                j++
                continue
            } else {
                while (j < word1.length && word1[j] != word2[i]) {
                    j++
                }
                if(j == word1.length) {
                    if(used) {
                        return IntArray(0)
                    } else {
                        used = true
                    }
                }
                result[i] = j
                j++
            }
        }
        return result
    }
}