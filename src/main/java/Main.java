import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Main {
	static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name()); //SER316 TASK 2 SPOT-BUGS FIX needed to change
    public static void main(String[] args) {
        // just some calls
        System.out.println("Getting started");
        Game game = new Game("Student");
        System.out.println("Current word: " + game.answer);
        System.out.println(game.makeGuess("a"));
        System.out.println("Automatic guess a");


        // Rough game play
        Game newgame = new Game("Dr. M.");
        System.out.println("Make a guess: ");
        while (newgame.getGameStatus() == 0) {
            String message = scanner.nextLine();
            System.out.println(newgame.makeGuess(message));
            System.out.println("Score: " + newgame.points);
            newgame.countCorrectLetters();
        }
    }
}
