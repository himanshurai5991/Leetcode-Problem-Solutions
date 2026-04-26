class Solution1559 {

    fun containsCycle(grid: Array<CharArray>): Boolean {
        val m = grid.size
        val n = grid[0].size
        val visited = Array(m) { BooleanArray(n) }

        fun dfs(x: Int, y: Int, fromX: Int, fromY: Int, char: Char): Boolean {
            if (visited[x][y]) return true
            visited[x][y] = true

            val directions = arrayOf(
                intArrayOf(0, 1),  // right
                intArrayOf(1, 0),  // down
                intArrayOf(0, -1), // left
                intArrayOf(-1, 0)  // up
            )

            for (dir in directions) {
                val newX = x + dir[0]
                val newY = y + dir[1]

                if (newX in 0 until m && newY in 0 until n && grid[newX][newY] == char) {
                    if ((newX != fromX || newY != fromY) && dfs(newX, newY, x, y, char)) {
                        return true
                    }
                }
            }
            return false
        }

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (!visited[i][j]) {
                    if (dfs(i, j, -1, -1, grid[i][j])) {
                        return true
                    }
                }
            }
        }

        return false
    }



}