import java.util.Arrays;

public class numOfIslandsByDSU {

  public static void main(String[] args) {

    int n = 3;

    int[][] landPositions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}, {1, 1}};
    int[][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    DSU dsu = new DSU(n * n);

    int numOfIslands = 0;

    for (int[] pos : landPositions) {
      int r = pos[0];
      int c = pos[1];

      int node = r * n + c;

      // make this cell land
      dsu.parent[node] = node;
      dsu.size[node] = 1;
      numOfIslands++;

      for (int k = 0; k < 4; k++) {
        int nr = r + move[k][0];
        int nc = c + move[k][1];

        if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
          continue;
        }

        int neighbour = nr * n + nc;

        // Neighbour is in water
        if (dsu.parent[neighbour] == -1) {
          continue;
        }

        int nodeParent = dsu.findUltimateParent(node);
        int neighbourParent = dsu.findUltimateParent(neighbour);

        if (nodeParent != neighbourParent) {
          dsu.unionBySize(node, neighbour);
          numOfIslands--;
        }
      }
    }

    System.out.println("Number of Islands are - " + numOfIslands);
  }
}

class DSU {

  int[] parent;
  int[] size;

  public DSU(int n) {

    parent = new int[n];
    size = new int[n];

    Arrays.fill(parent, -1);
  }

  public int findUltimateParent(int node) {

    if (parent[node] == node) {
      return parent[node];
    }

    parent[node] = findUltimateParent(parent[node]);

    return parent[node];
  }

  public void unionBySize(int node1, int node2) {

    int root1 = findUltimateParent(node1);
    int root2 = findUltimateParent(node2);

    if (root1 == root2)
      return;

    if (size[root1] > size[root2]) {

      parent[root2] = root1;
      size[root1] += size[root2];

    } else {

      parent[root1] = root2;
      size[root2] += size[root1];
    }
  }
}