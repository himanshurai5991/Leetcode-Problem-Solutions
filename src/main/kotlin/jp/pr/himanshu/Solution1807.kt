package jp.pr.himanshu


// s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]] - "bobistwoyearsold"

// s = "hi(name)", knowledge = [["a","b"]]  - hi?

// s = "(a)(a)(a)aaa", knowledge = [["a","yes"]] - "yesyesyesaaa"
class Solution1807 {

    fun evaluate(s: String, knowledge: List<List<String>>): String {

        val map = mutableMapOf<String, String>()
        val result = StringBuilder()
        for (i in knowledge.indices) {
            map[knowledge[i][0]] = knowledge[i][1]
        }

        var i = 0

        while (i < s.length) {
            if(s[i] == '('){
                var start = i+1
                val current = StringBuilder("")
                while (start < s.length && s[start] != ')') {
                    current.append(s[start])
                    start += 1
                }
                val currentString = current.toString()
                if(map.containsKey(currentString)) {
                    result.append(map[currentString])
                } else {
                    result.append("?")
                }
                start++
                i = start
            } else {
                result.append(s[i])
                i++
            }
        }
        return result.toString()




    }
}