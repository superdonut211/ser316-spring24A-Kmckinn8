import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {
    private Class<Game> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<Game>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Game0.class},
            {Game1.class},
            {Game2.class},
            {Game3.class},
            {Game4.class}
        };
        return Arrays.asList(classes);
    }

    private Game createGame() throws Exception {
        Constructor<Game> constructor = classUnderTest.getConstructor();
        return constructor.newInstance();
    }

    Game game;

    @org.junit.Before
    public void setUp() throws Exception {
        game = createGame();
    }


    @Test
    public void winning() {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }
    @Test
    public void testCorrectSingleLetterOccursOnce() {
        // Corresponds to TC1
        game.initGame("horse", "Player");
        double response = game.makeGuess("h");
        assertEquals(1.1, response, 0.0);
    }

    @Test
    public void testCorrectSingleLetterOccursMultipleTimes() {
        // Corresponds to TC2
        game.initGame("loop", "Player");
        double response = game.makeGuess("o");
        assertEquals(1.2, response, 0.0);
    }

    @Test
    public void testIncorrectSingleLetter() {
        // Corresponds to TC3
        game.initGame("horse", "Player");
        double response = game.makeGuess("x");
        assertEquals(1.0, response, 0.0);
    }

    @Test
    public void testExactCorrectWord() {
        // Corresponds to TC4
        game.initGame("horse", "Player");
        double response = game.makeGuess("horse");
        assertEquals(0.0, response, 0.0);
        assertEquals(15, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }

    @Test
    public void testIncorrectWordCorrectLength() {
        // Corresponds to TC5
        game.initGame("horse", "Player");
        double response = game.makeGuess("house");
        assertEquals(2.0, response, 0.0);
        assertEquals(11, game.getPoints());
    }

    @Test
    public void testIncorrectWordPartiallyMatching() {
        // Corresponds to TC6
        game.initGame("horse", "Player");
        double response = game.makeGuess("hose");
        assertEquals(3.0, response, 0.0);
        assertEquals(12, game.getPoints());
    }

    @Test
    public void testIncorrectWordTooLong() {
        // Corresponds to TC7
        game.initGame("horse", "Player");
        double response = game.makeGuess("horseshoe");
        assertEquals(2.1, response, 0.0);
    }

    @Test
    public void testIncorrectWordTooShort() {
        // Corresponds to TC8
        game.initGame("horse", "Player");
        double response = game.makeGuess("hos");
        assertEquals(2.2, response, 0.0);
    }

    @Test
    public void testGuessWithSymbolsNumbers() {
        // Corresponds to TC9
        game.initGame("horse", "Player");
        double response = game.makeGuess("hor$e");
        assertEquals(4.1, response, 0.0);
    }

    @Test
    public void testEmptyGuess() {
        // Corresponds to TC10
        game.initGame("horse", "Player");
        double response = game.makeGuess("");
        assertEquals(4.1, response, 0.0);
    }

    @Test
    public void testRepeatedGuess() {
        // Corresponds to TC11
        game.initGame("horse", "Player");
        game.makeGuess("h");
        double response = game.makeGuess("h");
        assertEquals(4.0, response, 0.0);
    }

    @Test
    public void testGuessAfterGameOver() {
        // Corresponds to TC12
        game.initGame("horse", "Player");
        for (int i = 0; i < 10; i++) {
            game.makeGuess("x"); // Incorrect guesses
        }
        double response = game.makeGuess("y");
        assertEquals(5.0, response, 0.0); // Check for game over
        response = game.makeGuess("z");
        assertEquals(5.1, response, 0.0); // Check for subsequent guess after game over
    }

}