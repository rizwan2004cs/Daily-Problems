### Problem Overview

Given:

- An event window from time `t = 0` to `t = eventTime`.
- `n` non-overlapping meetings, each with a `startTime[i]` and `endTime[i]`.
- You may reschedule at most `k` meetings (by shifting their start time, keeping duration and order, and not overlapping or going out of bounds).

**Goal:**
Maximize the length of the longest continuous free period during the event after optimal rescheduling.

### Key Observations

- **Meetings must remain in order and non-overlapping.**
- **Rescheduling** means you can shift a meeting within the event window, but not change its duration or overlap with others.
- The **free time** is any period not covered by meetings.


### Solution Approach

#### 1. Calculate All Possible Gaps

- The **gaps** are the free times between meetings and at the start/end of the event:
    - Before the first meeting: `startTime - 0`
    - Between meetings: `startTime[i] - endTime[i-1]` for `i = 1 to n-1`
    - After the last meeting: `eventTime - endTime[n-1]`


#### 2. Rescheduling Strategy

- By shifting up to `k` meetings, you can "move" the meetings to make one gap as large as possible.
- The optimal way is to **concentrate the reschedulings to merge adjacent gaps**.


#### 3. Sliding Window on Gaps

- For each possible window of `k` consecutive meetings, compute the sum of the gaps **outside** that window.
- The **maximum possible free time** is the sum of all gaps **except** the `k` smallest ones (since you can "merge" up to `k` gaps by rescheduling).


#### 4. Implementation Outline

- Compute all gaps.
- For each window of `k` gaps, calculate the sum of the remaining gaps.
- The answer is the **maximum** such sum (i.e., the largest possible continuous free period).


### Example

**Input:**
`eventTime = 5, k = 1, startTime = , endTime = `

- Gaps: `[1-0=1]`, `[3-2=1]`, `[5-5=0]` → ``
- By rescheduling one meeting, you can merge two adjacent gaps into one of size `2`.
- **Output:** `2`


### Java Code

```java
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] gaps = new int[n + 1];
        gaps[0] = startTime[0] - 0;
        for (int i = 1; i < n; i++) {
            gaps[i] = startTime[i] - endTime[i - 1];
        }
        gaps[n] = eventTime - endTime[n - 1];

        int maxFree = 0;
        // Sliding window: try merging up to k adjacent gaps
        for (int i = 0; i + k < gaps.length; i++) {
            int merged = 0;
            for (int j = 0; j <= k; j++) {
                merged += gaps[i + j];
            }
            maxFree = Math.max(maxFree, merged);
        }
        return maxFree;
    }
}
```


### Complexity

- **Time Complexity:** \$ O(n) \$ — Single pass to compute gaps and sliding window.
- **Space Complexity:** \$ O(n) \$ — For the gaps array.


### Summary Table

| Step | Description |
| :-- | :-- |
| Compute Gaps | Find all free periods between meetings |
| Sliding Window | Merge up to `k` gaps to maximize free period |
| Return Maximum | Output the largest possible continuous free time |

### Key Points

- **Rescheduling merges adjacent gaps.**
- **Sliding window** over the gaps array gives the optimal answer.
- Efficient for large inputs due to linear complexity.

