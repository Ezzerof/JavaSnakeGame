import java.util.ArrayList;
import java.util.Random;

public class Board {

    Random r = new Random();
    private int xCor = r.nextInt(1,8);
    private int yCor = r.nextInt(4, 36);
    private int xF = r.nextInt(1, 8); //Food X
    private int yF = r.nextInt(2, 38); // Food Y
    private static int score = 0;

    public static ArrayList<Snake> parts = new ArrayList<>();



    public int getxCor() { return xCor; }
    public int getyCor() { return yCor; }
    public int getFoodX() { return xF; }
    public int getFoodY() { return yF; }
    public void setFoodX(int x) { this.xF = x; }
    public void setFoodY(int y) { this.yF = y; }
    public int getScore() { return score; }

    public String[][] board = {
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, //40
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}
            //10
    };

    private final int ARR_X_LENGTH = board.length;
    private final int ARR_Y_LENGTH = board[0].length;

    public void printBoard() {

        for (int i = 0; i < ARR_X_LENGTH; i++) {
            for (int j = 0; j < ARR_Y_LENGTH; j++) {

                System.out.print(board[i][j]);
            }
            System.out.println();
        }

    }

    Snake head = new Snake(getxCor(), getyCor());
    Snake body1 = new Snake(getxCor(), getyCor() + 1);  //Creating 3 parts of the snake
    Snake body2 = new Snake(getxCor(), getyCor() + 2);

    public void spawnFood() {
        board[getFoodX()][getFoodY()] = "*";
    }

    public void addPart(Snake part) {
        int x = part.getX();
        int y = part.getY();
        board[x][y] = "x";

        parts.add(new Snake(x, y - 1));
    }


    public void init() {
        parts.add(head);
        parts.add(body1); // Adding parts to the Snake Array
        parts.add(body2);
    }

    public void setSnake() {
        if (head.getY() != getFoodY()) {

            board[parts.get(0).getX()][parts.get(0).getY()] = "X";

            for (int i = 1; i < parts.size(); i++) {
                int x = parts.get(i).getX();
                int y = parts.get(i).getY();
                board[x][y] = "x";
            }

            printBoard();
        }
    }

    public void ifEaten() {
        if (parts.get(0).getX() == getFoodX() && parts.get(0).getY() == getFoodY()) {
            score++;
            setFoodX(r.nextInt(1, 8));
            setFoodY(r.nextInt(2, 38));

            spawnFood();
        }
    }

    public void creatingNewPart(boolean isEaten) {
        if (true)
            addPart(parts.get(parts.size() - 1));
    }

    public void movement(int head) {
        if (parts.get(0).getX() == 1) {
            parts.get(0).setX(9);
            board[parts.get(0).getX()][parts.get(0).getY()] = "X";
            board[parts.get(0).getX()][parts.get(0).getY()] = "-";
        }
        int tempX = parts.get(parts.size() - 1).getX();
        int tempY = parts.get(parts.size() - 1).getY();

        for (int i = parts.size() - 1; i > 0; i--) {
            parts.get(i).setX(parts.get(i-1).getX());
            parts.get(i).setY(parts.get(i-1).getY());
            board[parts.get(i).getX()][parts.get(i).getY()] = "x";
        }
        parts.get(0).setX(head);
        board[parts.get(0).getX()][parts.get(0).getY()] = "X";
        board[tempX][tempY] = "-";

        printBoard();
    }


    public void Up() {

        int headX = parts.get(0).getX() - 1;
        ifEaten();

        if (parts.get(0).getX() == 1) {
            parts.get(0).setX(9);
            board[parts.get(0).getX()][parts.get(0).getY()] = "X";
            board[parts.get(0).getX()][parts.get(0).getY()] = "-";
        }
        int tempX = parts.get(parts.size() - 1).getX();
        int tempY = parts.get(parts.size() - 1).getY();

        for (int i = parts.size() - 1; i > 0; i--) {
            parts.get(i).setX(parts.get(i-1).getX());
            parts.get(i).setY(parts.get(i-1).getY());
            board[parts.get(i).getX()][parts.get(i).getY()] = "x";
        }
            parts.get(0).setX(headX);
            board[parts.get(0).getX()][parts.get(0).getY()] = "X";
            board[tempX][tempY] = "-";

        printBoard();
    }

    public void Down() {
        int headX = parts.get(0).getX() + 1;
        ifEaten();

        if (parts.get(0).getX() == 9) {
            parts.get(0).setX(0);
            board[parts.get(0).getX()][parts.get(0).getY()] = "X";
            board[parts.get(0).getX()][parts.get(0).getY()] = "-";
        }
        int tempX = parts.get(parts.size() - 1).getX();
        int tempY = parts.get(parts.size() - 1).getY();

        for (int i = parts.size() - 1; i > 0; i--) {
            parts.get(i).setX(parts.get(i-1).getX());
            parts.get(i).setY(parts.get(i-1).getY());
            board[parts.get(i).getX()][parts.get(i).getY()] = "x";
        }
        parts.get(0).setX(headX);
        board[parts.get(0).getX()][parts.get(0).getY()] = "X";
        board[tempX][tempY] = "-";

        printBoard();

    }

    public void Left() {

        int headX = parts.get(0).getY() - 1;

        if (parts.get(0).getX() == 1) {
            parts.get(0).setX(9);
            board[parts.get(0).getX()][parts.get(0).getY()] = "X";
            board[parts.get(0).getX()][parts.get(0).getY()] = "-";
        }

        int tempX = parts.get(parts.size() - 1).getX();
        int tempY = parts.get(parts.size() - 1).getY();

        for (int i = parts.size() - 1; i > 0; i--) {
            parts.get(i).setX(parts.get(i-1).getX());
            parts.get(i).setY(parts.get(i-1).getY());
            board[parts.get(i).getX()][parts.get(i).getY()] = "x";
        }
        parts.get(0).setY(headX);
        board[parts.get(0).getX()][parts.get(0).getY()] = "X";
        board[tempX][tempY] = "-";

        printBoard();

    }

    public void Rigth() {

        int headX = parts.get(0).getY() + 1;

        if (parts.get(0).getX() == 1) {
            parts.get(0).setX(9);
            board[parts.get(0).getX()][parts.get(0).getY()] = "X";
            board[parts.get(0).getX()][parts.get(0).getY()] = "-";
        }
        int tempX = parts.get(parts.size() - 1).getX();
        int tempY = parts.get(parts.size() - 1).getY();

        for (int i = parts.size() - 1; i > 0; i--) {
            parts.get(i).setX(parts.get(i-1).getX());
            parts.get(i).setY(parts.get(i-1).getY());
            board[parts.get(i).getX()][parts.get(i).getY()] = "x";
        }
        parts.get(0).setY(headX);
        board[parts.get(0).getX()][parts.get(0).getY()] = "X";
        board[tempX][tempY] = "-";

        printBoard();

    }
}
