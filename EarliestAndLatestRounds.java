import java.util.*;

class EarliestAndLatestRounds {
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        int[] res = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        dfs(buildList(n), firstPlayer, secondPlayer, 1, res, new HashMap<>());
        return res;
    }

    private List<Integer> buildList(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) list.add(i);
        return list;
    }

    private void dfs(List<Integer> players, int a, int b, int round, int[] res, Map<String, Boolean> memo) {
        int n = players.size();
        int posA = players.indexOf(a), posB = players.indexOf(b);
        if (posA > posB) { int t = posA; posA = posB; posB = t; }
        String key = players.toString();
        if (memo.containsKey(key + "|" + round)) return;
        memo.put(key + "|" + round, true);

        int pairs = n / 2;
        for (int i = 0; i < pairs; i++) {
            int left = i, right = n - 1 - i;
            if ((left == posA && right == posB) || (left == posB && right == posA)) {
                res[0] = Math.min(res[0], round);
                res[1] = Math.max(res[1], round);
                return;
            }
        }

        List<List<Integer>> nextRounds = new ArrayList<>();
        dfsHelper(players, 0, n - 1, a, b, new ArrayList<>(), nextRounds);

        for (List<Integer> next : nextRounds) {
            dfs(next, a, b, round + 1, res, memo);
        }
    }

    private void dfsHelper(List<Integer> players, int l, int r, int a, int b, List<Integer> curr, List<List<Integer>> nextRounds) {
        if (l > r) {
            List<Integer> next = new ArrayList<>(curr);
            Collections.sort(next);
            nextRounds.add(next);
            return;
        }
        if (l == r) {
            curr.add(players.get(l));
            dfsHelper(players, l + 1, r, a, b, curr, nextRounds);
            curr.remove(curr.size() - 1);
            return;
        }
        int left = players.get(l), right = players.get(r);
        if (left == a || right == a) {
            curr.add(a);
            dfsHelper(players, l + 1, r - 1, a, b, curr, nextRounds);
            curr.remove(curr.size() - 1);
        } else if (left == b || right == b) {
            curr.add(b);
            dfsHelper(players, l + 1, r - 1, a, b, curr, nextRounds);
            curr.remove(curr.size() - 1);
        } else {
            curr.add(left);
            dfsHelper(players, l + 1, r - 1, a, b, curr, nextRounds);
            curr.remove(curr.size() - 1);
            curr.add(right);
            dfsHelper(players, l + 1, r - 1, a, b, curr, nextRounds);
            curr.remove(curr.size() - 1);
        }
    }
}
