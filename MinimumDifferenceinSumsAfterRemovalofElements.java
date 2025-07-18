import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumDifferenceinSumsAfterRemovalofElements {
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        int len = nums.length;

        long[] left = new long[len];
        long[] right = new long[len];

        // LEFT: Min sum of n elements from [0..i] using max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        long leftSum = 0;
        for (int i = 0; i < len; ++i) {
            maxHeap.offer(nums[i]);
            leftSum += nums[i];
            if (maxHeap.size() > n)
                leftSum -= maxHeap.poll();
            if (i >= n - 1)
                left[i] = leftSum;
        }

        // RIGHT: Max sum of n elements from [i..end] using min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long rightSum = 0;
        for (int i = len - 1; i >= 0; --i) {
            minHeap.offer(nums[i]);
            rightSum += nums[i];
            if (minHeap.size() > n)
                rightSum -= minHeap.poll();
            if (i <= len - n)
                right[i] = rightSum;
        }

        // Find minimum (left[i] - right[i+1])
        long ans = Long.MAX_VALUE;
        for (int i = n - 1; i < len - n; i++) {
            ans = Math.min(ans, left[i] - right[i + 1]);
        }

        return ans;
    }
}
