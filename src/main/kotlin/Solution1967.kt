class Solution1967 {

    fun numOfStrings(patterns: Array<String>, word: String): Int {

        var result = 0
        for(i in patterns.indices) {
            if(word.contains(patterns[i])){
                result++
            }
        }
        return result

    }
}