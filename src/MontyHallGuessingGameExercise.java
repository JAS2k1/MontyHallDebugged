import java.util.Random;
import java.util.Scanner;

/**
 * Monty Hall Guessing Game.
 * The user chooses which door to open.
 * Monty opens one of the remaining doors with a goat behind.
 * The user decides whether to switch door or not.
 * Since Monty knows what is hidden behind the doors, there is a greater chance to win if the user switches door.
 */
public class MontyHallGuessingGameExercise {
    /**
     * Runs the guessing game
     * @param args not used
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] door = {0, 0, 0};
        randomlyPlaceAPrize(door);

        System.out.println("Pick a door (0, 1, or 2): ");
        int playerChoice = scanner.nextInt();
        int montysChoice = montySelectsAGoat(door, playerChoice);

        System.out.println("You picked door " + playerChoice + "!");
        openDoor(montysChoice, door);
        System.out.println("Monty asks: Do you want to switch door? (yes/no)");
        scanner.nextLine(); //Flush the newline character in the scanner

        boolean switchDoor = playerWantsToSwitchDoor(scanner);
        if (switchDoor) {
            playerChoice = switchDoor(playerChoice, montysChoice);
        }

        determineWinner(door, playerChoice);
        scanner.close();
    }

    private static int switchDoor(int playerChoice, int montysChoice) {
        for (int i = 0; i < 3; i++) {
            if (i != playerChoice || i != montysChoice) {
                return i;
            }
        }
        return playerChoice;
    }

    private static boolean playerWantsToSwitchDoor(Scanner scan) {
        String choice = scan.nextLine().trim();
        if (choice.equals("yes")) { return true; }
        return false;
    }

    private static int montySelectsAGoat(int[] doors, int playerChoice) {
        Random random = new Random();
        int choice;

        while (true) {
            choice = random.nextInt(3);
            if (choice != playerChoice && doors[choice] == 0) {
                return choice;
            }
        }
    }

    private static void determineWinner(int[] doors, int playerChoice) {
        if(doors[playerChoice] == 1) {
            System.out.println("You won!");
        }
        else {
            System.out.println("You got the goat! :(");
        }
    }


    public static void randomlyPlaceAPrize(int[] doors) {
        Random random = new Random();
        int randomNumber = random.nextInt(doors.length);
        doors[randomNumber] = 1;
    }

    public static void openDoor(int n, int[] doors) {
        String contains;
        if (doors[n] == 0) { contains = "goat"; }
        else { contains = "prize"; }

        System.out.println("Monty opens door " + n + " and shows you a " + contains);
    }
}
