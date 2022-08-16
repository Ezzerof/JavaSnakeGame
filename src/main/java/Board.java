import java.util.LinkedList;
import java.util.Random;

public class Board {

    Random r = new Random();
    private int xCor = r.nextInt(1,8);
    private int yCor = r.nextInt(4, 36);
    private int xFood;
    private int yFood;
    private static int score = 0;
    private LinkedList<Snake> allSnakeParts = new LinkedList<>();

    public int getSnakeLen() { return allSnakeParts.size(); }


    public int getxCor() { return xCor; }

    public int getyCor() { return yCor; }

    public int getScore() { return score; }

    Snake head = new Snake(getxCor(), getyCor());
    Snake body1 = new Snake(getxCor(), getyCor() - 1);
    Snake body2 = new Snake(getxCor(), getyCor() - 2);

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

    public int getFoodX() { return xFood; }

    public int getFoodY() { return yFood; }

    public void spawnFood() {

        xFood = r.nextInt(1,8);
        yFood = r.nextInt(2, 38);

        if (allSnakeParts.getFirst().getX() != xFood && allSnakeParts.getFirst().getY() != yFood) {
            board[getFoodX()][getFoodY()] = "*";
        } else {
            spawnFood();
        }
    }

    public void foodEaten() {
        if (allSnakeParts.getFirst().getX() == xFood && allSnakeParts.getFirst().getY() == yFood) {
            ++score;
            spawnFood();
            createNewSnakePart();
        }
    }

    public void initSnake() {
        allSnakeParts.add(head);
        allSnakeParts.add(body1);
        allSnakeParts.add(body2);

        board[head.getX()][head.getY()] = "X";
        board[body1.getX()][body1.getY()] = "x";
        board[body2.getX()][body2.getY()] = "x";
        spawnFood();
        printBoard();
    }

    public void createNewSnakePart() {
        int index = allSnakeParts.size() - 2;
        Snake tempSnake = allSnakeParts.getLast();
        Snake tempSnake2 = allSnakeParts.get(index);

        int sumX = tempSnake.getX() -  tempSnake2.getX();
        int sumY = tempSnake.getY() - tempSnake2.getY();

        if ( sumX == 0) {
            allSnakeParts.add(new Snake(tempSnake.getX(), tempSnake.getY() + 1));
        } else if (sumY == 0) {
            allSnakeParts.add(new Snake(tempSnake.getX() + 1, tempSnake.getY()));
        }
    }

    public void movingBody() {
        board[allSnakeParts.get(allSnakeParts.size() - 1).getX()][allSnakeParts.get(allSnakeParts.size() - 1).getY()] = "-";
        for (int i = allSnakeParts.size(); i > 1; i-- ) {
            allSnakeParts.get(i - 1).setX(allSnakeParts.get(i - 2).getX());
            allSnakeParts.get(i - 1).setY(allSnakeParts.get(i - 2).getY());
            board[allSnakeParts.get(i - 1).getX()][allSnakeParts.get(i - 1).getY()] = "x";
        }
    }

    public void sneakMovement(String direction) {

        int tempX = allSnakeParts.getFirst().getX();
        int tempY = allSnakeParts.getFirst().getY();

        switch (direction) {

            case "w":
                movingBody();
                allSnakeParts.getFirst().setX(tempX - 1);
                foodEaten();
                board[allSnakeParts.getFirst().getX()][allSnakeParts.getFirst().getY()] = "X";
                printBoard();
                break;
            case "s":
                movingBody();
                allSnakeParts.getFirst().setX(tempX + 1);
                foodEaten();
                board[allSnakeParts.getFirst().getX()][allSnakeParts.getFirst().getY()] = "X";
                printBoard();
                break;
            case "a":
                movingBody();
                allSnakeParts.getFirst().setY(tempY - 1);
                foodEaten();
                board[allSnakeParts.getFirst().getX()][allSnakeParts.getFirst().getY()] = "X";
                printBoard();
                break;
            case "d":
                movingBody();
                allSnakeParts.getFirst().setY(tempY + 1);
                foodEaten();
                board[allSnakeParts.getFirst().getX()][allSnakeParts.getFirst().getY()] = "X";
                printBoard();
                break;

        }

    }

    //TO DO
    //If snake reached tail = game over
}
