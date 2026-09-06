public class Solution3876 {

    public boolean uniformArray(int[] nums1) {

        int countOdd = 0;
        int countEven = 0;
        int leastEven = Integer.MAX_VALUE;
        int leastOdd = Integer.MAX_VALUE;
        for (int k : nums1) {
            if (k % 2 == 0) {
                countEven++;
                leastEven = Math.min(leastEven, k);
            } else {
                countOdd++;
                leastOdd = Math.min(leastOdd, k);
            }
        }

        if(countEven == nums1.length){
            return true;
        }
        if(countOdd == nums1.length){
            return true;
        }
        return leastEven - leastOdd >= 1;


    }
}
