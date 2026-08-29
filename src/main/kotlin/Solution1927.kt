import kotlin.math.abs

class Solution1927 {

    fun sumGame(num: String): Boolean {
        val str1 = num.substring(0, num.length/2)
        val str2 = num.substring(num.length/2)
        var count1 = 0
        var count2 = 0
        var sum1 = 0
        var sum2 = 0
        for (i in str1.indices) {
            if(str1[i] != '?'){
                sum1 += str1[i] - '0'
            } else {
                count1 += 1
            }
        }
        for (i in str2.indices) {
            if(str2[i] != '?'){
                sum2 += str2[i] - '0'
            } else {
                count2 += 1
            }
        }
        if(count1 == 0 && count2 == 0){
            return sum1 != sum2
        }
        val diff = Math.abs(sum1 - sum2)
        println(diff)
        println(count1)
        println(count2) // 9/4
        if(count1+count2 == 1){
            return true
        }
        if(diff/((count1+count2)/2) >=10){
            return true
        } else {
            if(diff < 9) {
                if((count1+count2 )%2  == 0){
                    if(count1 == count2) {
                        return sum1!=sum2
                    } else {
                        val d = abs(count1 - count2)
                        return if((9*(d/2) > diff)) {
                            true
                        } else {
                            9*(d/2) < diff
                        }
                    }
                } else {
                    return (count1+count2)%2 !=0
                }
            } else {
                val d = abs(count1 - count2)
                return if((9*(d/2) > diff)) {
                    true
                } else {
                    if(9*(d/2) < diff) {
                        true
                    } else {
                        if(d%2 == 0){
                            if(sum1>sum2 && count1> count2) {
                                return true
                            }
                            if(sum1<sum2 && count1< count2){
                                return true
                            }
                            false
                        } else {
                            true
                        }
                    }
                }
            }
        }
    }
}