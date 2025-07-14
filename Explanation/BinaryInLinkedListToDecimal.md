### Problem Overview

Given a singly-linked list where each node contains a value `0` or `1`, representing a binary number (with the most significant bit at the head), return the decimal (base-10) value of the number.

**Example:**

- Input: `head = `
- Output: `5`
- Explanation: Binary `101` equals decimal `5`.


### Solution Approach

#### 1. Traverse the Linked List

- Start from the head node.
- For each node, shift the current result left by 1 (multiply by 2), then add the node's value.
- Continue until the end of the list.


#### 2. Why This Works

- This method simulates reading binary digits from left to right, just like converting a binary string to an integer.


### Java Implementation

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            result = (result << 1) | head.val;
            head = head.next;
        }
        return result;
    }
}
```


### Example Walkthrough

For `head = `:

- Start with `result = 0`
- First node (`1`): `result = (0 << 1) | 1 = 1`
- Second node (`0`): `result = (1 << 1) | 0 = 2`
- Third node (`1`): `result = (2 << 1) | 1 = 5`
- Final result: `5`


### Complexity Analysis

- **Time Complexity:** O(N), where N is the number of nodes in the list.
- **Space Complexity:** O(1), as only a few variables are used.


### Key Points

- Bitwise operations make the solution concise and efficient.
- No need to store the entire binary string; process each node as you traverse.

This approach is optimal and works for all valid inputs as per the problem's constraints.

