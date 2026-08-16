import java.util.PriorityQueue

class Solution3433 {

    fun countMentions(numberOfUsers: Int, events: List<List<String>>): IntArray {
        val mentions = IntArray(numberOfUsers)
        if (numberOfUsers == 0) return mentions

        val online = BooleanArray(numberOfUsers) { true }
        val offlineUntil = IntArray(numberOfUsers)
        val reactivationQueue = PriorityQueue(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })

        fun reactivateUsers(currentTime: Int) {
            while (reactivationQueue.isNotEmpty() && reactivationQueue.peek().first <= currentTime) {
                val (reactivationTime, userId) = reactivationQueue.poll()
                if (offlineUntil[userId] == reactivationTime) {
                    online[userId] = true
                }
            }
        }

        for (event in events) {
            if (event.size < 2) continue

            val type = event[0]
            val time = event[1].toInt()

            reactivateUsers(time)

            when (type) {
                "OFFLINE" -> {
                    if (event.size < 3) continue
                    val userId = event[2].removePrefix("id").toInt()
                    val reactivationTime = time + 60
                    offlineUntil[userId] = reactivationTime
                    online[userId] = false
                    reactivationQueue.add(reactivationTime to userId)
                }

                "MESSAGE" -> {
                    if (event.size < 3) continue
                    when (val content = event[2].trim()) {
                        "ALL" -> {
                            for (userId in 0 until numberOfUsers) {
                                mentions[userId]++
                            }
                        }

                        "HERE" -> {
                            for (userId in 0 until numberOfUsers) {
                                if (online[userId]) {
                                    mentions[userId]++
                                }
                            }
                        }

                        else -> {
                            val seen = HashSet<Int>()
                            for (token in content.split(Regex("\\s+"))) {
                                if (token.isBlank()) continue
                                val userId = token.removePrefix("id").toIntOrNull() ?: continue
                                if (userId in 0 until numberOfUsers && seen.add(userId)) {
                                    mentions[userId]++
                                }
                            }
                        }
                    }
                }
            }
        }

        return mentions
    }
}