## Problem Statement

Given a positive integer `k`, determine the k-th character in a recursively defined string generated as follows:

- **Initial String**: `S₁ = "a"`
- **Recursive Rule**: For each subsequent string, `Sₙ = Sₙ₋₁ + shift(Sₙ₋₁, 1)`, where `shift(str, x)` increments each character in `str` by `x` (modulo 26, so 'z' wraps to 'a').

### Example
- `S₁ = "a"`
- `S₂ = "ab"` (concatenate "a" with "a" shifted by 1)
- `S₃ = "abbc"` (concatenate "ab" with "ab" shifted by 1)
- `S₄ = "abbcbccd"` (concatenate "abbc" with "abbc" shifted by 1)
- And so on...

**Task**: Find the k-th character in this infinitely growing string.

## Solution Approach

### Observations
- Each new string doubles in length: `|Sₙ| = 2ⁿ`.
- The **left half** of `Sₙ` is identical to `Sₙ₋₁`.
- The **right half** of `Sₙ` is `Sₙ₋₁` with each character incremented by 1 (modulo 26).
- The k-th character's value depends on how many times we traverse into the right half during recursive decomposition.

### Efficient Algorithm
To find the k-th character efficiently:
1. Determine the smallest power of 2 greater than or equal to `k` (call it `len`), representing the length of the current string segment.
2. Trace back from `k` to the first character, counting how many times we move into the right half. Each right-half move increments a `shift` counter.
3. The final character is `'a' + (shift % 26)`.

This approach runs in **O(log k)** time and avoids constructing the string.

## Implementation (C++)

```cpp
class Solution {
public:
    char kthCharacter(int k) {
        int len = 1;
        while (len < k) {
            len *= 2;
        }
        int shift = 0;
        while (len > 1) {
            if (k > len / 2) {
                shift++;
                k -= len / 2;
            }
            len /= 2;
        }
        return 'a' + (shift % 26);
    }
};
```

### Code Explanation
- **Initialization**: Start with `len = 1` and double it until `len ≥ k` to find the smallest power of 2 that can contain the k-th character.
- **Tracing Back**:
  - If `k > len / 2`, the k-th character is in the right half. Increment `shift` and adjust `k` by subtracting `len / 2`.
  - Halve `len` and repeat until `len = 1`.
- **Result**: The character is `'a'` plus `shift` modulo 26 (to handle wrap-around from 'z' to 'a').

## Example Walkthrough
Suppose `k = 5`:

1. **Find Length**: 
   - `len = 1, 2, 4, 8` (since `8 ≥ 5`).
   - Use `len = 8`.

2. **Trace Back**:
   - `k = 5 > len / 2 = 4` → Right half: `shift = 1`, `k = 5 - 4 = 1`, `len = 4`.
   - `k = 1 ≤ len / 2 = 2` → Left half: `shift = 1`, `k = 1`, `len = 2`.
   - `k = 1 ≤ len / 2 = 1` → Left half: `shift = 1`, `k = 1`, `len = 1`.

3. **Result**: `'a' + (shift % 26) = 'a' + 1 = 'b'`.

4. **Verification**: In `S₄ = "abbcbccd"`, the 5th character is `'b'`.

## Time and Space Complexity
- **Time Complexity**: O(log k), as we divide `len` by 2 until we reach 1.
- **Space Complexity**: O(1), as we only use a few variables.