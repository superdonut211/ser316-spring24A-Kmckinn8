import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class guessTest {
    Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game("Player");
        game.initGame("horse", "Player"); // Initialize the game with a fixed answer for consistency
    }

    @Test
    public void testCorrectSingleLetterOccursOnce() {
        double response = game.makeGuess("h");
        assertEquals(1.1, response, 0.0);
    }

    @Test
    public void testCorrectSingleLetterOccursMultipleTimes() {
        // Adjusting the setup for this specific test
        game.initGame("loop", "Player");
        double response = game.makeGuess("o");
        assertEquals(1.2, response, 0.0);
    }

    @Test
    public void testIncorrectSingleLetter() {
        double response = game.makeGuess("x");
        assertEquals(1.0, response, 0.0);
    }

    @Test
    public void testExactCorrectWord() {
        double response = game.makeGuess("horse");
        assertEquals(0.0, response, 0.0);
        assertEquals(15, game.getPoints()); // Adjust the expected points based on your game logic
        assertEquals(1, game.getGameStatus());
    }

    @Test
    public void testIncorrectWordCorrectLength() {
        double response = game.makeGuess("house");
        assertEquals(2.0, response, 0.0);
    }

    @Test
    public void testIncorrectWordPartiallyMatching() {
        double response = game.makeGuess("hose");
        assertEquals(3.0, response, 0.0);
    }

    @Test
    public void testIncorrectWordTooLong() {
        double response = game.makeGuess("horseshoe");
        assertEquals(2.1, response, 0.0);
    }

    @Test
    public void testIncorrectWordTooShort() {
        double response = game.makeGuess("hos");
        assertEquals(2.2, response, 0.0);
    }

    @Test
    public void testGuessWithSymbolsNumbers() {
        double response = game.makeGuess("hor$e");
        assertEquals(4.1, response, 0.0);
    }

    @Test
    public void testEmptyGuess() {
        double response = game.makeGuess("");
        assertEquals(4.1, response, 0.0);
    }

    @Test
    public void testRepeatedGuess() {
        game.makeGuess("h");
        double response = game.makeGuess("h");
        assertEquals(4.0, response, 0.0);
    }

    @Test
    public void testGuessAfterGameOver() {
        for (int i = 0; i < 10; i++) {
            game.makeGuess("x"); // Simulate incorrect guesses to end the game
        }
        double response = game.makeGuess("y");
        assertEquals(5.0, response, 0.0); // Check for game over status
        response = game.makeGuess("z");
        assertEquals(5.1, response, 0.0); // Check for attempts to guess after game over
    }
}
