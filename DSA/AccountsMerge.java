class DisjointSet {
    int[] parent;
    int[] size;

    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int getUltiParent(int node) {
        if (node == parent[node]) {
            return node;
        }

        parent[node] = getUltiParent(parent[node]);
        return parent[node];
    }

    public void unionBySize(int node1, int node2) {
        int root1 = getUltiParent(node1);
        int root2 = getUltiParent(node2);

        if (root1 == root2) {
            return;
        }

        int size1 = size[root1];
        int size2 = size[root2];

        if (size1 > size2) {
            parent[root2] = root1;
            size[root1] += size2;
        } else {
            parent[root1] = root2;
            size[root2] += size1;
        }
    }
}

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet dsu = new DisjointSet(n);

        HashMap<String, Integer> map = new HashMap<>(); // Email - parentListIdx

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                
                String email = accounts.get(i).get(j);
                if (!map.containsKey(email)) {
                    map.put(email, i);
                } else {
                    dsu.unionBySize(i, map.get(email));
                }
            }
        }

        List<List<String>> groupByOwners = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            groupByOwners.add(new ArrayList<>());
        }

        for (String email : map.keySet()) {
            int parent = dsu.getUltiParent(map.get(email));
            groupByOwners.get(parent).add(email);
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (groupByOwners.get(i).size() == 0) {
                continue;
            }

            String name = accounts.get(i).get(0);
            List<String> inner = new ArrayList<>();
            inner.add(name);
            Collections.sort(groupByOwners.get(i));
            for (String email : groupByOwners.get(i)) {
                inner.add(email);
            }
            ans.add(inner);
        }
        return ans;
    }
}