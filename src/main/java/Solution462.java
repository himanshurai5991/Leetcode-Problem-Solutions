import java.util.Arrays;

public class Solution462 {

    public int minMoves2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int min = 0;
        int mid = nums[nums.length / 2];
        for (int i = 0; i < n; i++) {
            min += Math.abs(nums[i] - mid);
        }
        return min;

    }
}
