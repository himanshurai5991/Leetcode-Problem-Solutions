class Solution825 {

    //age[y] <= 0.5 * age[x] + 7
    //age[y] > age[x]
    //age[y] > 100 && age[x] < 100
    fun numFriendRequests(ages: IntArray): Int {
        var ans = 0
        ages.sort()
        for (i in ages.size - 1 downTo 0) {
            for(j in i-1 downTo 0) {
                if(ages[i] <= ((ages[j]/2 ) + 7)) {
                    break
                }
                val flag1 = checkEligibility(ages[i], ages[j])
                //val flag2 = checkEligibility(ages[j], ages[i])
                //println("$flag1 $flag2")
                if(flag1 ) {
                    if(ages[i] == ages[j]) {
                        ans += 2
                    } else {
                        ans += 1
                    }
                }
            }
        }
        return ans
    }

    fun checkEligibility(x: Int, y: Int): Boolean {
        if( y > 100 && x < 100) {
            return false
        }

        if(y <= ((x/2 ) + 7)) {
            return false
        }
        if(y> x) {
            return false
        }
        return true
    }
}