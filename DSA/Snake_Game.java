import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Snake_Game {
    public static void main(String[] args) {
        int[][] food = { { 1, 2 }, { 0, 1 } };
        SnakeGame snakeGame = new SnakeGame(3, 2, food);

        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("U"));
    }
}

class SnakeGame {
    private int width;
    private int height;
    private int[][] food;
    private Deque<int[]> dq;
    private Set<Integer> set;

    private int score;
    private int foodPointer;

    public SnakeGame(int width, int height, int[][] food) {
        this.height = height;
        this.width = width;
        this.food = food;

        this.score = 0;
        this.foodPointer = 0;
        set = new HashSet<>();

        dq = new ArrayDeque<>();
        dq.addLast(new int[] { 0, 0 });
    }

    public int move(String direction) {
        int currRow = dq.peekLast()[0];
        int currCol = dq.peekLast()[1];

        switch (direction) {
            case "U":
                currRow--;
                break;
            case "D":
                currRow++;
                break;
            case "L":
                currCol--;
                break;
            case "R":
                currCol++;
                break;
            default:
                break;
        }

        if (currRow < 0 || currCol < 0 || currRow >= height || currCol >= width) {
            return -1;
        }
        int[] curr = { currRow, currCol };
        int currPosition = currRow * width + currCol;

        boolean canEat = foodPointer < food.length && food[foodPointer][0] == currRow
                && food[foodPointer][1] == currCol;

        if (!canEat) {
            int[] removed = dq.removeFirst();
            int removedPostion = removed[0] * width + removed[1];
            set.remove(removedPostion);
        }

        if (dq.contains(curr)) {
            return -1;
        }
        dq.addLast(curr);
        set.add(currPosition);

        if (canEat) {
            score++;
            foodPointer++;
        }
        return score;
    }

}
