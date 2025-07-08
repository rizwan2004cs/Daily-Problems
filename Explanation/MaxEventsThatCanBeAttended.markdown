# Maximum Events Attended – Problem and Solution Explanation

## Problem Statement

Given:
- An array of events, where each event is represented as `[startDay, endDay]`.
- You can attend an event on any day within its interval (`startDay` to `endDay`, inclusive).
- You can only attend one event per day.

**Goal**: Return the maximum number of events you can attend.

## Solution Approach

### Observations
- Each event can be attended on any day in its interval, but only one event can be attended per day.
- To maximize the number of events attended, prioritize events that end earlier to free up future days for other events.
- Sorting events by start day allows processing in chronological order, while a min-heap can track the end days of available events.

### Efficient Algorithm
1. **Sort Events**: Sort the events by their start day to process them chronologically.
2. **Use a Min-Heap**: Maintain a min-heap of end days for events that can be attended on the current day:
   - Add all events starting on the current day to the heap.
   - Remove events whose end day is before the current day (expired events).
   - Attend the event with the earliest end day by removing it from the heap.
3. **Iterate Over Days**: Process each day, attending one event if available, and move to the next relevant day (either the next event’s start day or the next day if the heap is not empty).
4. **Count Events**: Increment a counter for each event attended.

### Why is this efficient?
- Sorting events takes O(n log n) time, enabling chronological processing.
- The min-heap manages end days with O(log n) operations for insertions and deletions.
- Iterating over days and events is linear in practice, as each event is processed at most once.
- Total time complexity is O(n log n), with space complexity of O(n) for the heap.

## Example Walkthrough
Suppose the events are `[[1,4], [4,5], [5,6]]`:

### Event Processing
- **Sort Events**: The events are already sorted by start day: `[[1,4], [4,5], [5,6]]`.
- **Day 1**:
  - Add event `[1,4]` to the heap (end day 4).
  - Attend the event with the earliest end day (4), i.e., `[1,4]`.
  - Increment count to 1.
- **Day 2**:
  - Heap is empty; move to the next event’s start day (4).
- **Day 4**:
  - Add event `[4,5]` to the heap (end day 5).
  - Attend the event with the earliest end day (5), i.e., `[4,5]`.
  - Increment count to 2.
- **Day 5**:
  - Add event `[5,6]` to the heap (end day 6).
  - Attend the event with the earliest end day (6), i.e., `[5,6]`.
  - Increment count to 3.
- **Day 6**:
  - Heap is empty; stop processing.

### Result
- **Output**: 3.
- **Verification**: Attended event `[1,4]` on day 1, `[4,5]` on day 4, and `[5,6]` on day 5.

## Key Points
- **Greedy Strategy**: Prioritizing events with the earliest end day maximizes the number of events attended by freeing up future days.
- **Heap Efficiency**: The min-heap efficiently manages end days, ensuring quick access to the earliest-ending event.
- **Handles Large Inputs**: The approach scales well for large inputs due to efficient sorting and heap operations.
- **Generalization**: This method applies to scheduling problems where prioritizing earlier deadlines is optimal.

## Time and Space Complexity
- **Time Complexity**: O(n log n), where `n` is the number of events, due to sorting (O(n log n)) and heap operations (O(log n) per event).
- **Space Complexity**: O(n) for the min-heap, which may store up to `n` end days.