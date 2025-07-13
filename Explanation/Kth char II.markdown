## Problem Statement

Given:
- An integer `k` (1-indexed).
- A vector `operations` of size `n`, where each element is either `0` or `1`.

A string is built recursively as follows:
1. Start with the string `"a"`.
2. For each `i` from `0` to `n-1`:
   - Double the string to make it twice as long.
   - If `operations[i] == 1`, increment every character in the right half by 1 (modulo 26, so 'z' wraps to 'a').
   - If `operations[i] == 0`, the right half is an exact copy of the left half.

**Goal**: Find the k-th character in the final string after all operations.

## Solution Approach

### Observations
- Each operation doubles the string’s length: after `n` operations, the length is `2ⁿ`.
- The right half is either:
  - An exact copy of the left half (if `operations[i] == 0`).
  - The left half with each character incremented by 1 (modulo 26) (if `operations[i] == 1`).
- The k-th character’s value depends on how many times we land in a right half where `operations[i] == 1` during recursive decomposition.

### Efficient Algorithm
Instead of constructing the exponentially large string, trace backward from position `k`:
1. Convert `k` to 0-based indexing (`current_k = k - 1`).
2. Iterate through `operations` from last to first (`i = n-1` down to `0`):
   - Compute the length of the string at step `i`: `len = 2ⁱ`.
   - If `current_k >= len`, the position is in the right half:
     - Move to the corresponding left-half position: `current_k -= len`.
     - If `operations[i] == 1`, increment the `shifts` counter.
   - Otherwise, stay in the left half (no change to `shifts`).
3. The final character is `'a' + (shifts % 26)`.

### Why is this efficient?
- Each iteration reduces the problem size by half, resulting in **O(n)** time complexity.
- No string is constructed, keeping space complexity at **O(1)**.
- Handles large `k` (up to `2⁶³`) and large `n` efficiently.

## Example Walkthrough
Suppose `operations = [1, 0, 1]` and `k = 5`:

### String Evolution
- **Step 0**: `"a"` (initial string).
- **Step 1** (`operations[0] = 1`): `"ab"` (right half incremented).
- **Step 2** (`operations[1] = 0`): `"abab"` (right half copied).
- **Step 3** (`operations[2] = 1`): `"ababcdbc"` (right half incremented).

**Goal**: Find the 5th character (0-based index 4).

### Trace Back
- **Step 2** (`i=2`, `len=2²=4`):
  - `current_k = 4 >= 4` → Right half.
  - `operations[2] = 1` → `shifts = 1`.
  - Update `current_k = 4 - 4 = 0`.
- **Step 1** (`i=1`, `len=2¹=2`):
  - `current_k = 0 < 2` → Left half, no change to `shifts`.
- **Step 0** (`i=0`, `len=2⁰=1`):
  - `current_k = 0 < 1` → Left half, no change to `shifts`.

### Result
- Final `shifts = 1`.
- Answer: `'a' + (shifts % 26) = 'a' + 1 = 'b'`.
- **Verification**: In `"ababcdbc"`, the 5th character is `'b'`.

## Implementation (C++)

```cpp
class Solution {
public:
    char kthCharacter(long long k, vector<int>& operations) {
        int shifts = 0;
        long long current_k = k - 1; // Convert to 0-based index
        for (int i = operations.size() - 1; i >= 0; i--) {
            if (i >= 63) {
                continue; // Prevent overflow for large i
            }
            long long len = 1LL << i; // Length at step i (2^i)
            if (current_k >= len) {
                current_k -= len; // Move to left half
                if (operations[i] == 1) {
                    shifts++; // Increment shift if right half was incremented
                }
            }
        }
        return 'a' + (shifts % 26);
    }
};
```

### Code Explanation
- **Initialization**: Convert `k` to 0-based indexing (`current_k = k - 1`).
- **Backward Traversal**:
  - For each `i` from `n-1` to `0`, compute `len = 2ⁱ` using bit shift (`1LL << i`).
  - If `current_k >= len`, the position is in the right half:
    - Subtract `len` to map to the left half.
    - If `operations[i] == 1`, increment `shifts`.
- **Overflow Protection**: Skip iterations where `i >= 63` to avoid overflow in `1LL << i`.
- **Result**: Return `'a' + (shifts % 26)` to get the final character.

## Key Points
- **No String Construction**: The algorithm traces the path backward, making it highly efficient.
- **Handles Large Inputs**: Supports large `k` (up to `2⁶³`) and `n` with O(n) time and O(1) space.
- **Generalization**: Extends the original "Kth Character in String Game" by allowing custom operations (`0` or `1`) at each step.

## Time and Space Complexity
- **Time Complexity**: O(n), where `n` is the size of the `operations` vector.
- **Space Complexity**: O(1), as only a few variables are used.