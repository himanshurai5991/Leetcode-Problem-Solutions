public class Solution2091 {

    public int minimumDeletions(int[] nums) {
        int size = nums.length;
        int minIndex = -1;
        int maxIndex = -1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < min){
                min = nums[i];
                minIndex = i;
            }

            if(nums[i] > max){
                max = nums[i];
                maxIndex = i;
            }
        }
        int val1 = Math.max(minIndex, maxIndex);
        int val2 = Math.max(size - maxIndex, size - minIndex);
        int val3 = minIndex + size - maxIndex;
        int val4 = maxIndex + size - minIndex;

        return  Math.min(Math.min(val1,val2),Math.min(val3,val4));


    }
}
