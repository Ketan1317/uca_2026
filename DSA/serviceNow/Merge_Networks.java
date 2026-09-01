import java.util.*;

public class Merge_Networks {
    public static void main(String[] args) {
        int nodesA = 3;
        int[] fromA = { 1, 1 };
        int[] toA = { 2, 3 };

        int nodesB = 3;
        int[] fromB = { 1, 2 };
        int[] toB = { 2, 3 };

        int d1 = diameter(nodesA, fromA, toA);
        int d2 = diameter(nodesB, fromB, toB);

        int r1 = (d1 + 1) / 2;
        int r2 = (d2 + 1) / 2;
        int ans = Math.max(d1, Math.max(d2, r1 + 1 + r2));
        System.out.println(ans);

    }

    public static int diameter(int n, int[] from, int[] to) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            adj.get(from[i]).add(to[i]);
            adj.get(to[i]).add(from[i]);
        }

        int[] first = bfs(1, adj, n);
        int[] second = bfs(first[0], adj, n);

        return second[1];
    }

    public static int[] bfs(int start, List<List<Integer>> adj, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;

        int furthestNode = start;

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (dist[curr] > dist[furthestNode]) {
                furthestNode = curr;
            }

            for (int x : adj.get(curr)) {
                if (dist[x] == -1) {
                    dist[x] = dist[curr] + 1;
                    q.offer(x);
                }
            }
        }

        return new int[] { furthestNode, dist[furthestNode] };
    }
}
