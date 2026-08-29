import java.util.*;
import java.util.stream.Collectors;

public class Solution2948 {

    // [1,7,6,18,1,2]
    // [15,9,6,1]

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        List<Integer> list = Arrays.stream(nums).boxed().sorted(Comparator.naturalOrder()).toList();
        int[] res = new int[list.size()];
        Map<Integer, Queue<Integer>> groupMap = new HashMap<>();
        Map<Integer, Integer> groupdIdMap = new HashMap<>();
        int groupId = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(list.get(0));
        groupdIdMap.put(list.get(0), groupId);
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i) - list.get(i-1) <= limit) {
               queue.add(list.get(i));
               groupdIdMap.put(list.get(i), groupId);
            } else  {
                groupMap.put(groupId++, queue);
                queue = new LinkedList<>();
                queue.add(list.get(i));
                groupdIdMap.put(list.get(i), groupId);
            }
        }
//        System.out.println(groupdIdMap);
//        System.out.println(groupMap);

        for(int i = 0; i < list.size(); i++) {
            int currentGroupId = groupdIdMap.get(nums[i]);
            Queue<Integer> currentGroupMap = groupMap.get(currentGroupId);
            if(currentGroupMap != null && !currentGroupMap.isEmpty()) {
                res[i] = currentGroupMap.poll();
                groupMap.put(currentGroupId, currentGroupMap);
            } else  {
                res[i] = nums[i];
            }
        }
        return res;
    }
}
