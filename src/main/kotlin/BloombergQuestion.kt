class BloombergQuestion(val stops: List<String>, val busLocation: Map<Int, String>) {




    fun getNearestBus(stop: String) : Int {
        val sortedBusByLocation = mutableMapOf<String, Int>()
        val busId = busLocation.keys
        for(i in busId) {
            sortedBusByLocation[busLocation[i]!!] = i
        }

        sortedBusByLocation.toSortedMap()

        val locations = sortedBusByLocation.keys.toList()
        var start = 0
        var end = locations.size - 1
        while(start <= end) {
            val middle = (start + end) / 2
            if(locations[middle] == stop) {
                return sortedBusByLocation[locations[middle]]!!
            } else if (locations[middle] < stop) {
                start = middle + 1
            } else {
                end = middle - 1
            }
        }

        return -1

    }


}