import java.util.PriorityQueue


class GraphNode (
    val destination: Int,
    val weight: Int,
)

class PathNode(
    val currentNode: Int,
    val currentWeight: Long,
    val pathList: MutableList<Int> = mutableListOf(),
)


class Solution2203 {


    val pathList = mutableListOf<Int>()
    fun minimumWeight(n: Int, edges: Array<IntArray>, src1: Int, src2: Int, dest: Int): Long {
        val graph = MutableList<MutableList<GraphNode>>(n){mutableListOf()}
        for (edge in edges) {
            graph[edge[0]].add(GraphNode(edge[1], edge[2]))
        }
        val pathList1 = mutableListOf<Int>()
        val pathList2 = mutableListOf<Int>()
        val distance1 = findMinimumDistance(src1, dest, graph)
        pathList1.addAll(pathList)
        pathList.clear()
        val distance2 = findMinimumDistance(src2, dest, graph)
        pathList2.addAll(pathList)
        val distance3 = findMinimumDistance(src1, src2, graph)
        val distance4 = findMinimumDistance(src2, src1, graph)

//        var pathList3 = mutableListOf<Int>()
//        var pathList4 = mutableListOf<Int>()
        println(distance1)
        println(distance2)
        println(distance3)
        println(distance4)
        if(distance1 == -1L && distance2 ==-1L){
            return -1
        }
        if(distance4 == -1L && distance3 == -1L) {
            return if(distance1 == -1L || distance2 == -1L){
                -1
            } else {
                val commonPathList = mutableListOf<Int>()
                for (i in pathList1.indices){
                    for (j in pathList2.indices){
                        if(pathList1[i] == pathList2[j]){
                            commonPathList.add(pathList1[i])
                        }
                    }
                }
                if (commonPathList.isEmpty()){
                    distance1+distance2
                } else {
                    var commonWeight = Int.MAX_VALUE
                    //println(commonPathList)
                    for (i in 0 until commonPathList.size-1) {
                        for (j in graph[commonPathList[i]].indices) {
                            if(graph[commonPathList[i]][j].destination == commonPathList[i+1]){
                                commonWeight = Math.min(commonWeight, graph[commonPathList[i]][j].weight)
                            }
                        }
                    }
                    //println(commonWeight)
                    if(commonWeight == Int.MAX_VALUE) {
                        commonWeight = 0
                    }
                    distance1+distance2 - commonWeight
                }
            }
        }

        if(distance4 == -1L) {
            if (distance1 == -1L) {
                return distance2 + distance3
            }
            if (distance2 == -1L) {
                return distance3 + distance1
            }
            val commonPathList = mutableListOf<Int>()
            println(pathList1)
            println(pathList2)
            for (i in pathList1.indices) {
                for (j in pathList2.indices) {
                    if (pathList1[i] == pathList2[j]) {
                        commonPathList.add(pathList1[i])
                    }
                }
            }
            println(commonPathList)
            if (commonPathList.isEmpty()) {
                distance1 + distance2
            } else {
                var commonWeight = Int.MAX_VALUE

                for (i in 0 until commonPathList.size - 1) {
                    for (j in graph[commonPathList[i]].indices) {
                        if (graph[commonPathList[i]][j].destination == commonPathList[i + 1]) {
                            commonWeight = Math.min(graph[commonPathList[i]][j].weight, commonWeight)
                        }
                    }
                }
                if(commonWeight == Int.MAX_VALUE) {
                    commonWeight = 0
                }

                println(commonWeight)
                return distance1 + distance2 - commonWeight
            }
        }

        if(distance3 == -1L){
            if(distance1 == -1L){
                return distance2 + distance4
            }

            if(distance2 == -1L){
                return distance4 + distance1
            }

            val commonPathList = mutableListOf<Int>()
            for (i in pathList1.indices) {
                for (j in pathList2.indices) {
                    if (pathList1[i] == pathList2[j]) {
                        commonPathList.add(pathList1[i])
                    }
                }
            }
            if (commonPathList.isEmpty()) {
                return distance1 + distance2
            } else {
                var commonWeight = Int.MAX_VALUE
                for (i in 0 until commonPathList.size - 1) {
                    for (j in graph[commonPathList[i]].indices) {
                        if (graph[commonPathList[i]][j].destination == commonPathList[i + 1]) {
                            commonWeight = Math.min(commonWeight, graph[commonPathList[i]][j].weight)
                        }
                    }
                }
                if(commonWeight == Int.MAX_VALUE) {
                    commonWeight = 0
                }
                return distance1 + distance2 - commonWeight
            }

        }


        return Math.min(distance1, distance2)+ Math.min(distance3, distance4)
    }

    fun findMinimumDistance(souce: Int, dest: Int, graph: MutableList<MutableList<GraphNode>>): Long {
        val pathNode1 = PathNode(souce, 0, mutableListOf())
        val visited = mutableSetOf<Int>()
        val queue = PriorityQueue<PathNode> { p0, p1 ->
            (p0.currentWeight - p1.currentWeight).toInt()
        }
        queue.offer(pathNode1)
        while (!queue.isEmpty()) {
            val node = queue.poll()
            val currentWeight = node.currentWeight
            val currentNode = node.currentNode
            val currentNodes = node.pathList
            visited.add(currentNode)
            currentNodes.add(currentNode)
            if (currentNode == dest) {
                pathList.addAll(currentNodes)
                return currentWeight
            }
            for (j in graph[currentNode].indices) {
                val next = graph[currentNode][j]
                val newWeight = next.weight + currentWeight
                if(!visited.contains(next.destination)) {
                    val list = mutableListOf<Int>()
                    list.addAll(currentNodes)
                    queue.add(PathNode(next.destination, newWeight, list))
                }

            }
        }
        return -1L
    }
}