import java.util.PriorityQueue;
import java.util.Arrays;
class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int ans = 0, day = 0, i = 0, n = events.length;

        while (i < n || !minHeap.isEmpty()) {
            if (minHeap.isEmpty()) {
                day = events[i][0];
            }
            while (i < n && events[i][0] == day) {
                minHeap.offer(events[i++][1]);
            }
            minHeap.poll();
            ans++;
            day++;
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }
        }
        return ans;
    }
}
