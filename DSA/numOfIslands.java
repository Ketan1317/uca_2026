public class numOfIslands{
    public static void main(String[] args) {
        
    }
}

class Solution {
    int[][] move = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    public void dfs(char[][] grid, int r, int c) {
        grid[r][c] = '0';

        for (int k = 0; k < 4; k++) {
            int nr = r + move[k][0];
            int nc = c + move[k][1];
            if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || grid[nr][nc] == '0') {
                continue;
            }

            dfs(grid, nr, nc);
        }
    }

    public int numIslands(char[][] grid) {
        int rowCount = grid.length;
        int colCount = grid[0].length;

        int numOfIslands = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (grid[i][j] == '1') {
                    numOfIslands++;
                    dfs(grid, i, j);
                }
            }
        }
        return numOfIslands;
    }
}