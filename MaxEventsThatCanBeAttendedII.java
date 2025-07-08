import java.util.Arrays;

public class MaxEventsThatCanBeAttendedII {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int n = events.length;
        int[][] memo = new int[n][k + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        return dfs(events, 0, k, memo);
    }
    
    private int dfs(int[][] events, int i, int k, int[][] memo) {
        if (i == events.length || k == 0) return 0;
        if (memo[i][k] != -1) return memo[i][k];
        int skip = dfs(events, i + 1, k, memo);
        int next = binarySearch(events, events[i][1]);
        int take = events[i][2] + dfs(events, next, k - 1, memo);
        memo[i][k] = Math.max(skip, take);
        return memo[i][k];
    }
    
    private int binarySearch(int[][] events, int prevEndDay) {
        int left = 0, right = events.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] > prevEndDay) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}


