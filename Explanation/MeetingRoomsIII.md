<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" class="logo" width="120"/>

## LeetCode 2402: Meeting Rooms III

### Problem Overview

You have `n` rooms (numbered `0` to `n-1`) and a list of meetings, each with a unique start time and an end time (half-open interval `[start, end)`).

- Each meeting is assigned to the **lowest-numbered available room**.
- If all rooms are busy, the meeting is **delayed** until a room is free, but keeps its original duration.
- When multiple meetings are waiting, the one with the **earliest original start time** is scheduled first.
- Return the room that hosted the **most meetings** (if a tie, return the lowest number).


### Solution Approach

#### 1. Sort Meetings by Start Time

- This ensures meetings are processed in chronological order.


#### 2. Use Two Priority Queues

- **Available Rooms:**
Min-heap of room numbers (rooms that are free).
- **Busy Rooms:**
Min-heap of `[endTime, roomNumber]` (rooms that are currently hosting a meeting, sorted by when they become free).


#### 3. Process Each Meeting

- Before scheduling a meeting, **free up any rooms** whose meetings have ended by the current meeting's start time.
- If a room is available, assign the meeting to the **lowest-numbered available room**.
- If no room is available, **delay the meeting** until the earliest room is free. The meeting will start at that room's free time and last for its original duration.


#### 4. Track Meeting Counts

- Maintain a count of meetings held by each room.
- At the end, return the room with the highest count (lowest number in case of a tie).


### Java Code

```java
class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> available = new PriorityQueue<>();
        for (int i = 0; i < n; i++) available.offer(i);

        // [endTime, roomNumber]
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> 
            a[0] != b[0] ? Long.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

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
```


### Example

**Input:**
`n = 2, meetings = [,,,]`
**Output:**
`0`
**Explanation:**
Both rooms 0 and 1 hold 2 meetings, but room 0 is the lowest number.

### Complexity

- **Time Complexity:** O(m log n), where m = number of meetings, n = number of rooms.
- **Space Complexity:** O(n + m) for the heaps and counts.


### Key Points

- **Always assign to the lowest-numbered available room.**
- **If all are busy, delay the meeting to the earliest free time.**
- **Track the number of meetings per room and return the room with the highest count (lowest number in case of tie).**

This approach is efficient and handles large input sizes smoothly.

