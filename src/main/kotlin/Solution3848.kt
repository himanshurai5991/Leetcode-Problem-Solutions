class Solution3848 {
    var factorials = LongArray(10)
    init {
        factorials[0] = 1
        for (i in 1..9) {
            factorials[i] = factorials[i - 1] * i
        }

    }

    fun isDigitorialPermutation(n: Int): Boolean {
        val digits = n.toString().map { it - '0' }
        var sumOfFactorials = 0L
        for (digit in digits) {
            sumOfFactorials += factorials[digit]
        }
        val digitMap = mutableMapOf<Int, Int>()
        for (digit in digits) {
            digitMap[digit] = digitMap.getOrDefault(digit, 0) + 1
        }

        val factorialsMap = mutableMapOf<Int, Int>()
        for ( sumDigit in sumOfFactorials.toString()) {
            val digit = sumDigit - '0'
            factorialsMap[digit] = factorialsMap.getOrDefault(digit, 0) + 1
        }
        if(digitMap.size != factorialsMap.size) {
            return false
        }

        for (digit in digitMap.keys) {
            if (!factorialsMap.containsKey(digit)) {
                return false
            }
            if (factorialsMap[digit] != digitMap[digit]) {
                return false
            }
        }
        return true
    }
}