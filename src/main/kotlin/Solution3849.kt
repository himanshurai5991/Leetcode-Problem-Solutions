class Solution3849 {

    fun maximumXor(s: String, t: String): String {
        val n = s.length
        val result = StringBuilder()
        val tMap = mutableMapOf<Char, Int>()
        for (i in 0 until n) {
            tMap[t[i]] = tMap.getOrDefault(t[i], 0) + 1
        }

        for (i in 0 until n) {
            val sBit = s[i]
            if (sBit == '1') {
                var xorBit = '1'
                if (tMap.containsKey('0') && tMap['0']!! > 0) {
                    tMap['0'] = tMap['0']!! - 1
                    xorBit = '1'
                } else if (tMap.containsKey('1') && tMap['1']!! > 0) {
                    tMap['1'] = tMap['1']!! - 1
                    xorBit = '0'
                }
                result.append(xorBit)
            } else {
                var xorBit = '0'
                if (tMap.containsKey('1') && tMap['1']!! > 0) {
                    xorBit = '1'
                    tMap['1'] = tMap['1']!! - 1
                } else if (tMap.containsKey('0') && tMap['0']!! > 0) {
                    tMap['0'] = tMap['0']!! - 1
                    xorBit = '0'
                }
                result.append(xorBit)
            }
        }
        return result.toString()
    }
}