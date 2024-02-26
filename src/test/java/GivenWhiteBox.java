import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GivenWhiteBox {
    Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game("lion", "Dr. M"); // Initialize with a fixed word for consistency.
    }

    /**
     * Test Sequence for Node Coverage Scenario 1:
     * When guesses is empty, covering Start, Block 1, Block 7, End.
     */
    @Test
    public void testWithEmptyGuesses() {
        assertEquals("Expecting score to be initial value", 10, game.getPoints());
        int result = game.countCorrectLetters();
        assertEquals("Expecting result to be 0 when guesses is empty", 0, result);
    }

    /**
     * Test Sequence for Node Coverage Scenario 2:
     * When guesses is not empty and contains at least one letter from answer, covering all nodes except Block 5.
     */
    @Test
    public void testWithCorrectGuess() {
        game.guesses.add("i"); // "i" is in "lion"
        int result = game.countCorrectLetters();
        // Assuming countCorrectLetters should increment result for correct guesses
        assertEquals("Expecting result to reflect correct guesses", 1, result); // Corrected expectation
    }

    /**
     * Test Sequence for Node Coverage Scenario 3:
     * When guesses is not empty and does not contain any letter from answer, covering all nodes except Block 4.
     */
    @Test
    public void testWithIncorrectGuess() {
        game.guesses.add("z"); // "z" is not in "lion"
        int result = game.countCorrectLetters();
        assertEquals("Expecting result to be 0 for incorrect guesses", 0, result);
    }

    /**
     * Test Sequence for Edge Coverage:
     * Includes tests for direct return path, paths including Block 5 and Block 4.
     */
    // The above test methods already cover these scenarios by addressing node coverage,
    // which inherently covers the edge coverage scenarios as well.

    // Correction in Game.java:
    // Added logic to increment `result` in `countCorrectLetters` method for correct guesses.
}
