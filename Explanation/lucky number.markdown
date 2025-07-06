# Lucky Number in an Array

## Problem Statement

Given an array of integers `arr`, a **lucky integer** is defined as an integer whose **value** equals its **frequency** in the array.

**Task**: Return the **largest lucky integer** in the array. If no lucky integer exists, return `-1`.

### Example
**Input**: `arr = [2,2,3,4]`

**Output**: `2`

**Explanation**:
- `2` appears 2 times → lucky.
- `3` appears 1 time → not lucky.
- `4` appears 1 time → not lucky.
- The largest lucky integer is `2`.

## Solution Approach

### Steps
1. **Count Frequencies**:
   - Use a hash map (`unordered_map`) to store the frequency of each number in the array.
2. **Check for Lucky Numbers**:
   - Iterate through the hash map. If a number's value equals its frequency, it is a lucky number.
3. **Return the Largest**:
   - Track the largest lucky number found. If no lucky numbers exist, return `-1`.

### Why is this efficient?
- **Time Complexity**: O(n), where `n` is the length of the array (one pass to build the frequency map, one pass to check for lucky numbers).
- **Space Complexity**: O(n) for the hash map to store frequencies.

## Implementation (C++)

```cpp
class Solution {
public:
    int findLucky(vector<int>& arr) {
        unordered_map<int, int> freq;
        for (int num : arr) {
            freq[num]++;
        }

        int lucky = -1;
        for (const auto& pair : freq) {
            if (pair.first == pair.second) {
                lucky = max(lucky, pair.first);
            }
        }
        
        return lucky;
    }
};
```

### Code Explanation
- **Frequency Counting**:
  - Iterate through `arr` and populate `freq`, where `freq[num]` stores the count of occurrences of `num`.
- **Lucky Number Check**:
  - Iterate through `freq`. For each entry, check if the number (`pair.first`) equals its frequency (`pair.second`).
  - If they match, update `lucky` with the maximum of the current `lucky` and `pair.first`.
- **Return**:
  - Return `lucky`, which is the largest lucky number or `-1` if no lucky numbers were found.

## Example Walkthrough
**Input**: `arr = [2,2,3,4]`

1. **Frequency Map**:
   - `freq = {2:2, 3:1, 4:1}`
2. **Check Lucky Numbers**:
   - For `2`: `pair.first = 2`, `pair.second = 2` → `2 == 2` → lucky, update `lucky = max(-1, 2) = 2`.
   - For `3`: `pair.first = 3`, `pair.second = 1` → `3 != 1` → not lucky.
   - For `4`: `pair.first = 4`, `pair.second = 1` → `4 != 1` → not lucky.
3. **Result**: Return `lucky = 2`.

## Key Points
- **Efficient**: Uses a hash map for O(n) time complexity, avoiding sorting or multiple passes.
- **Simple Logic**: Directly compares value and frequency to identify lucky numbers.
- **Handles Edge Cases**: Returns `-1` when no lucky numbers exist.

## Time and Space Complexity
- **Time Complexity**: O(n), where `n` is the length of `arr`.
- **Space Complexity**: O(n) for the hash map.