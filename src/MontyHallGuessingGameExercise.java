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

    /**
     * Switches the player's door choice to the remaining unopened door.
     * @param playerChoice the door initially chosen by the player
     * @param montysChoice the door opened by Monty
     * @return the number of the remaining door
     */
    private static int switchDoor(int playerChoice, int montysChoice) {
        for (int i = 0; i < 3; i++) {
            if (i != playerChoice && i != montysChoice) {
                return i;
            }
        }
        return playerChoice;
    }

    /**
     * Asks the player if they want to switch doors.
     * @param scan the Scanner object to read user input
     * @return true if the player wants to switch, false otherwise
     */
    private static boolean playerWantsToSwitchDoor(Scanner scan) {
        String choice = scan.nextLine().trim().toLowerCase();
        return switch (choice) {
            case "yes", "y" -> true;
            default -> false;
        };
    }

    /**
     * Monty selects a door with a goat that is not the player's choice.
     * @param doors the array representing the doors
     * @param playerChoice the door chosen by the player
     * @return the door number Monty opens
     */
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

    /**
     * Determines if the player won or lost based on their final door choice.
     * @param doors the array representing the doors
     * @param playerChoice the player's final door choice
     */
    private static void determineWinner(int[] doors, int playerChoice) {
        switch (doors[playerChoice]) {
            case 1 -> System.out.println("You won!");
            default -> System.out.println("You got the goat! :(");
        }
    }


    /**
     * Randomly places a prize behind one of the doors.
     * @param doors the array representing the doors
     */
    public static void randomlyPlaceAPrize(int[] doors) {
        Random random = new Random();
        int randomNumber = random.nextInt(doors.length);
        doors[randomNumber] = 1;
    }

    /**
     * Opens a door and displays what is behind it.
     * @param n the door number to open
     * @param doors the array representing the doors
     */
    public static void openDoor(int n, int[] doors) {
        String contains = switch (doors[n]) {
            case 0 -> "goat";
            default -> "prize";
        };
        System.out.println("Monty opens door " + n + " and shows you a " + contains);
    }
}
