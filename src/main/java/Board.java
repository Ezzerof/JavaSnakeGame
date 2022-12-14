import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
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

public String[][] board;

    public void printBoard() {
        for(String[] b : board){
            System.out.println(Arrays.deepToString(b) + "");
        }
    }

    public void initBard() {

        int rows = 10;
        int columns = 40;
        board = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = "-";
            }
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
        initBard();
        allSnakeParts.add(head);
        allSnakeParts.add(body1);
        allSnakeParts.add(body2);
        spawnFood();

        board[head.getX()][head.getY()] = "X";
        board[body1.getX()][body1.getY()] = "x";
        board[body2.getX()][body2.getY()] = "x";
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

            //Moving every part of body after head
    public void movingBody() {
        board[allSnakeParts.get(allSnakeParts.size() - 1).getX()][allSnakeParts.get(allSnakeParts.size() - 1).getY()] = "-";
        for (int i = allSnakeParts.size(); i > 1; i-- ) {
            allSnakeParts.get(i - 1).setX(allSnakeParts.get(i - 2).getX());
            allSnakeParts.get(i - 1).setY(allSnakeParts.get(i - 2).getY());
            board[allSnakeParts.get(i - 1).getX()][allSnakeParts.get(i - 1).getY()] = "x";
        }
    }
            // if head hits any part of body or borders = Game Over
    public boolean wrongMovement() {
        for (int i = 1; i < allSnakeParts.size(); i++) {
            if (allSnakeParts.getFirst().getX() == allSnakeParts.get(i).getX() && allSnakeParts.getFirst().getY() == allSnakeParts.get(i).getY()) {
                System.out.println("Game Over.");
                return false;
            }
        }

        try {
            int x = allSnakeParts.getFirst().getX();
            int y = allSnakeParts.getFirst().getY();

            if (x == 0 || y == 0 || x == 9 || y == 39) {
                System.out.println("Game Over, you hit the wall");
                return false;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
        }

        return true;
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
