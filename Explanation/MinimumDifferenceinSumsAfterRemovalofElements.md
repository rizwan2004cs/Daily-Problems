### 🔍 Problem Overview

You are given an integer array `nums` of length `3 * n`.

You must:

1. Remove exactly `n` elements.
2. Split the remaining elements into:
    - First `n` elements → `sumfirst`
    - Next `n` elements → `sumsecond`
3. Return the **minimum possible value of** `sumfirst - sumsecond`.

### 🧠 Key Observations

- You're allowed to remove any `n` elements from anywhere in the array.
- After removing, the array splits into two equal `n`-size parts:
`[... sumfirst ...][... sumsecond ...]`
- Your goal is to minimize the difference: `sumfirst - sumsecond`

We want:

```
min(sumfirst - sumsecond)
```


### ⚙️ Strategy

#### ✅ Step 1: Track smallest `n` elements for `sumfirst`

- From left to right (`nums` to `nums[2n-1]`)
- Use a **max heap** (to discard the largest when more than `n`)
- Store cumulative sums in `left[i]`


#### ✅ Step 2: Track largest `n` elements for `sumsecond`

- From right to left (`nums[n]` to `nums[3n-1]`)
- Use a **min heap** (to discard smallest when more than `n`)
- Store cumulative sums in `right[i]`


#### ✅ Step 3: Pair sums \& track minimum difference

- For each `i` from `[n-1 to 2n-1]`:
    - Let `sumfirst = left[i]`
    - Let `sumsecond = right[i+1]`
    - Track `min(left[i] - right[i+1])`


### ✅ Java Implementation

```java
class Solution {
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
```


### 🧪 Example Walkthrough

**Input:**

```java
nums = [7, 9, 5, 8, 1, 3]
```

`n = 2`

- Remove elements: `9` and `1`
- Remaining subsequence: ``
- First part: `7 + 5 = 12`
Second part: `8 + 3 = 11`
- Difference: `12 - 11 = 1`

✅ Final Answer: `1`

### ⏱️ Complexity

| Metric | Value |
| :-- | :-- |
| Time | O(n log n) |
| Space | O(n) |

### ✅ Key Takeaways

- Use **heaps** to maintain running min/max of `n`-sized subsets from both ends.
- Track best pair of prefix/suffix for minimum difference.
- Make sure to handle bounds carefully when pairing left and right sums at adjacent indices.

Let me know if you'd like this in Python, visual explanation, or a downloadable PDF format!

