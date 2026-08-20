class Solution986 {

    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {

        // firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
        // [[1,5],[8,12],[15,24],[25,26]].  [[0,2],[5,10],[13,23],[24,25]
        val result = mutableListOf<IntArray>()
        var i = 0
        var j = 0
        while (i < firstList.size && j < secondList.size) {
            val start1 = firstList[i][0]
            val end1 = firstList[i][1]
            val start2 = secondList[j][0]
            val end2 = secondList[j][1]
            //println("$start1 $end1 $start2 $end2")
            if(end2 >= start1 && start2 <= end1) {
                val start = Math.max(start1, start2)
                val end = Math.min(end1, end2)
                result.add(intArrayOf(start, end))
                if(end1 <= end2) {
                    i++
                } else {
                    j++
                }
            } else {
                if(end1 < start2) {
                    i++
                } else {
                    j++
                }
            }
        }

        return result.toTypedArray()
    }
}