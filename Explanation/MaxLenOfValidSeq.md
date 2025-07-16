### 🔍 Problem Summary

You are given an integer array `nums`.

A subsequence `sub` of `nums` is **valid** if the parity (even/odd) of every adjacent pair sum is the same, i.e.:

```
(sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2
```


### ✅ Goal

Return the length of the **longest valid subsequence** of `nums`.

> 🔹 Note: A subsequence can skip elements but must maintain the relative order.

### 🧠 Key Observations

Let’s examine what `(a + b) % 2` looks like:

- Even + Even = Even → (0 + 0) % 2 = 0
- Odd + Odd   = Even → (1 + 1) % 2 = 0
- Even + Odd  = Odd  → (0 + 1) % 2 = 1
- Odd + Even  = Odd  → (1 + 0) % 2 = 1

So, to keep `(a + b) % 2` constant in a subsequence:

- Either keep the **same parity** between adjacent elements (both even or both odd)
- Or **alternate** between even and odd consistently

You are to choose either of the two patterns and build the longest such subsequence.

### 💡 Solution Overview

#### ✔️ Dynamic Programming Approach

We maintain a DP table to track the max length of subsequences while keeping track of the parity of the **last two elements**.

Let:

- `dp[prev][curr]` = length of the longest valid subsequence ending in parity `prev, curr`

Parities:

- `0` = even
- `1` = odd

🔁 Update the DP table on each number:

- For each number `x`, with parity `a = x % 2`
- Try extending existing sequences:
    - `dp[b][a] = max(dp[b][a], dp[a][b] + 1)` for both `b = 0` and `1`
- Also allow starting a new subsequence:
    - `dp[a][a] = max(dp[a][a], 1)`


### ✅ Java Code

```java
class Solution {
    public int maximumLength(int[] nums) {
        int[][] dp = new int[2][2];
        for (int x : nums) {
            int a = x % 2;
            for (int b = 0; b < 2; b++) {
                dp[b][a] = Math.max(dp[b][a], dp[a][b] + 1);
            }
            dp[a][a] = Math.max(dp[a][a], 1);
        }

        int ans = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                ans = Math.max(ans, dp[i][j]);

        return ans;
    }
}
```


### 🧪 Example

#### **Input:**

```java
nums = [1,2,1,1,2,1,2]
```


#### Valid Sequence:

```text
[1, 2, 1, 2, 1, 2] → All (odd + even) % 2 = 1
```


#### ✅ Output: `6`

### ⏱️ Complexity

| Type | Value |
| :-- | :-- |
| Time | O(n) |
| Space | O(1) (4 cells) |

### Key Takeaways

- Simulating the parity of adjacent pairs ensures all constraints are handled correctly.
- This is a classic example of **state-based dynamic programming** over two parities (even, odd).
- Efficient and scalable for `nums.length ≤ 2 × 10⁵`.

Let me know if you'd like a Python version, variation for printing the sequence, or documentation in PDF style!

