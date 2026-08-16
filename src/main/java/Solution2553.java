import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Solution2553 {

    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int x = nums[i];
            while (x > 0) {
                list.add(x % 10);
                x /= 10;
            }
        }

        Collections.reverse(list);
        return list.stream().mapToInt(i -> i).toArray();
    }
}
