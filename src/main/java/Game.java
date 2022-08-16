import java.util.Scanner;

public class Game extends Board {

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        boolean isOn = true;

        initSnake();

        while (isOn) {
            System.out.println("Score: " + getScore() + " | " + "Snake length: " + getSnakeLen());
            System.out.print("Choose direction with W,S,D,A or X to exit: ");
            String direction = scanner.next().toLowerCase();

            if (direction.equals("w") || direction.equals("s") || direction.equals("a") || direction.equals("d")) {
                sneakMovement(direction);
            } else {
                isOn = false;
            }

        }
    }


}
