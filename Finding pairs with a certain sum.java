import java.util.*;

class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> freq2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        freq2 = new HashMap<>();
        for (int num : nums2) {
            freq2.put(num, freq2.getOrDefault(num, 0) + 1);
        }
    }
    
    public void add(int index, int val) {
        int oldVal = nums2[index];
        freq2.put(oldVal, freq2.get(oldVal) - 1);
        if (freq2.get(oldVal) == 0) freq2.remove(oldVal);

        nums2[index] += val;
        freq2.put(nums2[index], freq2.getOrDefault(nums2[index], 0) + 1);
    }
    
    public int count(int tot) {
        int res = 0;
        for (int num : nums1) {
            int target = tot - num;
            res += freq2.getOrDefault(target, 0);
        }
        return res;
    }
}
