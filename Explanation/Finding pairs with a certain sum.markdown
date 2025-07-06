# Finding Pairs With a Certain Sum

## Problem Statement

Given two integer arrays `nums1` and `nums2`, design a class `FindSumPairs` that supports two operations:

1. **add(index, val)**: Add the value `val` to the element at position `index` in `nums2`.
2. **count(tot)**: Return the number of pairs `(i, j)` such that `nums1[i] + nums2[j] == tot`, where `i` is any index in `nums1` and `j` is any index in `nums2`.

### Example
**Input**:
- `nums1 = [1,2,3]`
- `nums2 = [3,4]`

**Operations**:
- `count(5)` → Returns `2` (pairs: `(1,4)` and `(2,3)`).
- `add(0, 1)` → Updates `nums2` to `[4,4]`.
- `count(6)` → Returns `2` (pairs: `(2,4)` and `(3,3)`, if `3` were still in `nums2`).

## Solution Approach

### Observations
- `nums1` is static, but `nums2` can change via `add` operations.
- To efficiently count pairs summing to `tot`, we need quick access to the frequency of values in `nums2`.
- Checking every possible pair `(i, j)` would be O(nums1.length * nums2.length), which is too slow for frequent `count` operations.

### Efficient Algorithm
Use a frequency map for `nums2` to optimize operations:
1. **Initialization**:
   - Store `nums1` and `nums2` as arrays.
   - Create a hash map (`freq2`) to track the frequency of each value in `nums2`.
2. **add(index, val)**:
   - Decrease the frequency of the old value at `nums2[index]` in `freq2`.
   - Update `nums2[index] += val`.
   - Increase the frequency of the new value in `freq2`.
3. **count(tot)**:
   - For each value `num` in `nums1`, compute `target = tot - num`.
   - Use `freq2` to find how many times `target` appears in `nums2`.
   - Sum these counts to get the total number of valid pairs.

### Why is this efficient?
- **add**: O(1) time (constant-time hash map operations).
- **count**: O(nums1.length) time (single pass through `nums1`, with O(1) hash map lookups).
- Avoids brute-force pair checking, making it suitable for large arrays and frequent operations.

## Implementation (Java)

```java
import java.util.*;

class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> freq2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        freq2 = new HashMap<>();
        for (int num : nums2) {
            freq2.put(num, freq2.getOrDefault(num, 0) + 1);
        }
    }
    
    public void add(int index, int val) {
        int oldVal = nums2[index];
        freq2.put(oldVal, freq2.get(oldVal) - 1);
        if (freq2.get(oldVal) == 0) freq2.remove(oldVal);

        nums2[index] += val;
        freq2.put(nums2[index], freq2.getOrDefault(nums2[index], 0) + 1);
    }
    
    public int count(int tot) {
        int res = 0;
        for (int num : nums1) {
            int target = tot - num;
            res += freq2.getOrDefault(target, 0);
        }
        return res;
    }
}
```

### Code Explanation
- **Constructor**:
  - Stores `nums1` and `nums2`.
  - Initializes `freq2` by counting the frequency of each value in `nums2`.
- **add(index, val)**:
  - Decrements the frequency of the old value (`nums2[index]`) in `freq2`.
  - Removes the old value from `freq2` if its frequency becomes 0.
  - Updates `nums2[index]` by adding `val`.
  - Increments the frequency of the new value in `freq2`.
- **count(tot)**:
  - Iterates through `nums1`. For each `num`, computes `target = tot - num`.
  - Adds the frequency of `target` from `freq2` to the result.
  - Returns the total count of valid pairs.

## Example Walkthrough
**Input**:
- `nums1 = [1,2,3]`
- `nums2 = [3,4]`

### Initial State
- `freq2 = {3:1, 4:1}`

### count(5)
- For `num=1`: `target = 5 - 1 = 4` → `freq2[4] = 1` → Add 1.
- For `num=2`: `target = 5 - 2 = 3` → `freq2[3] = 1` → Add 1.
- For `num=3`: `target = 5 - 3 = 2` → `freq2[2] = 0` → Add 0.
- **Result**: `1 + 1 + 0 = 2` (pairs: `(1,4)`, `(2,3)`).

### add(0, 1)
- Old value: `nums2[0] = 3`.
- Decrease `freq2[3]` from 1 to 0 → Remove `3` from `freq2`.
- Update `nums2[0] = 3 + 1 = 4`.
- Increase `freq2[4]` from 1 to 2.
- New state: `nums2 = [4,4]`, `freq2 = {4:2}`.

### count(6)
- For `num=1`: `target = 6 - 1 = 5` → `freq2[5] = 0` → Add 0.
- For `num=2`: `target = 6 - 2 = 4` → `freq2[4] = 2` → Add 2.
- For `num=3`: `target = 6 - 3 = 3` → `freq2[3] = 0` → Add 0.
- **Result**: `0 + 2 + 0 = 2` (pairs: `(2,4)` with both `4` values in `nums2`).

## Key Points
- **Frequency Map**: Using `freq2` allows fast lookups for `count` and efficient updates for `add`.
- **No Brute Force**: Avoids checking all pairs, making operations scalable.
- **Edge Cases Handled**:
  - Empty `freq2` entries are managed with `getOrDefault`.
  - Zero frequencies are removed to keep the map clean.

## Time and Space Complexity
- **Constructor**: O(nums2.length) to build `freq2`.
- **add**: O(1) for hash map operations.
- **count**: O(nums1.length) for iterating `nums1` with O(1) lookups.
- **Space Complexity**: O(nums2.length) for `freq2`.