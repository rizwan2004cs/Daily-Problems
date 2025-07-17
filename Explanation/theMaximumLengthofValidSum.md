<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" class="logo" width="120"/>

## LeetCode 3202: Find the Maximum Length of Valid Subsequence II

### Problem Overview

You are given an integer array `nums` and a positive integer `k`.

A subsequence `sub` of `nums` with length `x` is called **valid** if the modulo of the sum of every adjacent pair by `k` is the same:

```
(sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k
```

**Goal:** Return the length of the longest valid subsequence of `nums`.

### Examples

**Example 1:**

```
Input: nums = [1,2,3,4,5], k = 2
Output: 5

Explanation:
The longest valid subsequence is the entire array [1, 2, 3, 4, 5],
because the sum modulo 2 of every adjacent pair is consistent.
```

**Example 2:**

```
Input: nums = [1,4,2,3,1,4], k = 3
Output: 4

Explanation:
One longest valid subsequence is [1, 4, 1, 4].

Its adjacent sums modulo 3 are all equal (e.g., (1+4)%3 = 2).
```


### Constraints

- `2 <= nums.length <= 10^3`
- `1 <= nums[i] <= 10^7`
- `1 <= k <= 10^3`


### Approach

This problem generalizes the previous problem where `k = 2` dealing with parity to any modulus `k`.

1. Define `dp[r]` as the length of the longest valid subsequence that ends with a pair that has sum modulo `k == r`.
2. We iterate through the array for each candidate pair and update `dp`.
3. For each new element, try to extend existing subsequences depending on the modulo values.
4. The maximum value in `dp` after processing all elements is the answer.

### Detailed Explanation

- We check pairs `(a, b)` in the subsequence; for every adjacent pair, `(a + b) % k = same_mod`.
- The key observation is the value of `same_mod`.
- We maintain for each `mod` the length of valid subsequence ending with sum modulo `mod`.
- For each current number, try to connect it with prior numbers to update `dp`.


### Java Code

```java
class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k];
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1); // each element alone can start subsequence of length 1

            for (int j = 0; j < i; j++) {
                int modulo = (nums[j] + nums[i]) % k;
                if (dp[j][modulo] + 1 > dp[i][modulo]) {
                    dp[i][modulo] = dp[j][modulo] + 1;
                    maxLen = Math.max(maxLen, dp[i][modulo]);
                }
            }
        }

        return maxLen;
    }
}
```


### Complexity Analysis

| Complexity | Value |
| :-- | :-- |
| Time | O(n² * k) |
| Space | O(n * k) |

### Key Points

- This helps generalize the parity problem (`k=2`) to any modulus `k`.
- Use DP to keep track of subsequence lengths per modulo class.
- For each pair `(j, i)` update dp for `i`.
- Works well within constraints (`n`, `k` up to 1000).


### Summary

- Iterate through all possible pairs.
- Use a DP table to store max subsequence lengths for each modulo `k`.
- Return maximum subsequence length formed.

If you want, I can also provide a Python version, or explain with a worked example.

