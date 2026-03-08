class Solution1461 {

    fun hasAllCodes(s: String, k: Int): Boolean {
        val seen = mutableSetOf<String>()
        for (i in 0..s.length - k) {
            val substring = s.substring(i, i + k)
            seen.add(substring)
        }
        return seen.size == (1 shl k)
    }

}