import java.util.*;

class MeetingRoomsIII {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Min-heap for available room numbers
        PriorityQueue<Integer> available = new PriorityQueue<>();
        for (int i = 0; i < n; i++) available.offer(i);
        
        // Min-heap for busy rooms: [endTime, roomNumber]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Integer.compare((int)a[1], (int)b[1])
        );
        
        int[] count = new int[n];
        
        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];
            // Free up rooms whose meetings have ended
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                available.offer((int)busy.poll()[1]);
            }
            if (!available.isEmpty()) {
                int room = available.poll();
                busy.offer(new long[]{end, room});
                count[room]++;
            } else {
                long[] next = busy.poll();
                long newEnd = next[0] + (end - start);
                busy.offer(new long[]{newEnd, next[1]});
                count[(int)next[1]]++;
            }
        }
        
        int max = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > max) {
                max = count[i];
                ans = i;
            }
        }
        return ans;
    }
}
