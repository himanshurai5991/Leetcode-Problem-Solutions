import java.util.*;

public class Soltuion2770 {

    Soltuion2770(List<String> stops, Map<Integer, String > busLocation) {

        ArrayList<String> valueList = new ArrayList<>(busLocation.values());
        Collections.sort(valueList);


    }

    private Map<Integer, String> map = new HashMap<>();

    
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = -1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] != -1 && Math.abs(nums[i] - nums[j]) <= target) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }



}
