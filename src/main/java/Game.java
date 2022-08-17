import java.util.Scanner;

public class Game extends Board {
    public boolean isOn = true;
    private String[] tempDirections = new String[2];
    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        initSnake();

        while (isOn) {
            Character [] accChar = new Character[]{'a', 'd', 'w', 's', 'x'};
            String direction = getUserInput(scanner);


            if (direction.equals("w") || direction.equals("s") || direction.equals("a") || direction.equals("d")) {
                sneakMovement(direction);
                isOn = wrongMovement();
            } else if (direction.equals("x")) {
                isOn = false;
            }else {
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
