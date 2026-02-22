class Solution868 {

    fun binaryGap(n: Int): Int {

        var maxGap = 0
        var currentGap = 0
        var foundOne = false
        var num = n

        while (num > 0) {
            if (num and 1 == 1) { // Check if the least significant bit is 1
                if (foundOne) {
                    maxGap = maxOf(maxGap, currentGap)
                }
                foundOne = true
                currentGap = 0 // Reset gap count after finding a 1
            } else if (foundOne) {
                currentGap++ // Increment gap count only after finding the first 1
            }
            num = num shr 1 // Right shift to check the next bit
        }

        return maxGap

    }
}