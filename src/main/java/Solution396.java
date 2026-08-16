public class Solution396 {

    public int maxRotateFunction(int[] nums) {
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i< nums.length;i++) {
            sum += nums[i]*i;
        }
        result = Math.max(result, sum);

        for(int i = 1;i< nums.length;i++) {
            int currSum = 0;
            for(int j = 0;j<nums.length;j++) {
                currSum += nums[j] *((i+j)%nums.length);
            }
            result = Math.max(result, currSum);
        }

        return result;
    }
}
