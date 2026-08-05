
public class SetMatrixZeroes {

    public static void main(String[] args) {

    }

    // Without Extra Space
    public static void setMatrixZeroes(int[][] mat) {
        int r = mat.length;
        int c = mat[0].length;

        boolean isFirstRowZero = false;
        boolean isFirstColZero = false;

        for(int i=0;i<c;i++){
            if(mat[0][i] == 0){
                isFirstRowZero = true;
                break;
            }
        }

        for(int i=0;i<r;i++){
            if(mat[i][0] == 0){
                isFirstColZero = true;
                break;
            }
        }

        for(int i=1;i<r;i++){
            for(int j=1;j<c;j++){
                if(mat[i][j] == 0){
                    mat[0][j] = 0;
                    mat[i][0] = 0;
                }
            }
        }

        for(int i=1;i<r;i++){
            if(mat[i][0] == 0){
                for(int j=1;j<c;j++){
                    mat[i][j] = 0;
                }
            }
        }

        for(int i=1;i<c;i++){
            if(mat[0][i] == 0){
                for(int j=1;j<r;j++){
                    mat[j][i] = 0;
                }
            }
        }

        if(isFirstRowZero){
            for(int i=0;i<c;i++){
                mat[0][i] = 0;
            }
        }
        if(isFirstColZero){
            for(int i=0;i<r;i++){
                mat[i][0] = 0;
            }
        }
    }


    // With Extra Space
    public void setZeroes(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        boolean[] row = new boolean[r];
        boolean[] col = new boolean[c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < r; i++) {
            if (row[i]) {
                markColZero(matrix, i);
            }
        }
        for (int i = 0; i < c; i++) {
            if (col[i]) {
                markRowZero(matrix, i);
            }
        }
    }

    public void markColZero(int[][] mat, int r) {
        for (int i = 0; i < mat[0].length; i++) {
            mat[r][i] = 0;
        }
    }

    public void markRowZero(int[][] mat, int c) {
        for (int i = 0; i < mat.length; i++) {
            mat[i][c] = 0;
        }
    }
}
