import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game extends Board {

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        boolean isOn = true;

        initSnake();

        while (isOn) {
            Character [] accChar = new Character[]{'a', 'd', 'w', 's'};
            String direction = getUserInput(scanner);


            if (direction.equals("w") || direction.equals("s") || direction.equals("a") || direction.equals("d")) {
                sneakMovement(direction);
            } else {
                printBoard();
                System.out.println("Wrong letter, please use any of direction letters");
            }

        }
    }

    private String getUserInput(Scanner scanner) {
        System.out.println("Score: " + getScore() + " | " + "Snake length: " + getSnakeLen());
        System.out.print("Choose direction with W,S,D,A or X to exit: ");
        String direction = scanner.next().toLowerCase();
        return direction;
    }


}
