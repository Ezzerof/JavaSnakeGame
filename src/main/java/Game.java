import java.util.Scanner;

public class Game extends Board {

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        boolean isOn = true;

        spawnFood();
        init();
        setSnake();

        while (isOn) {
            System.out.println("Score: " + getScore());
            System.out.print("Choose direction with W,S,D,A or X to exit: ");
            String direction = scanner.next().toLowerCase();

            switch (direction) {
                case "w" :
                    Up();
                    ifEaten();
                    break;
                case "s":
                    Down();
                    ifEaten();
                    break;
                case "d":
                    Rigth();
                    ifEaten();
                    break;
                case "a":
                    Left();
                    ifEaten();
                    break;
                default:
                    isOn = false;
            }
        }
    }

}
