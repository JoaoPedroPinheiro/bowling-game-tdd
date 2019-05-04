import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BowlingGameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void canCreateGame() {
        assertNotNull(game);
    }

    @Test
    void afterTwoRolls_FrameIncreases() {
        assertEquals(0, game.getFrame());
        game.roll(0);
        assertEquals(0, game.getFrame());
        game.roll(0);
        assertEquals(1, game.getFrame());
    }

    @Test
    void afterStrike_FrameIncreases() {
        game.roll(10);
        assertEquals(1, game.getFrame());
    }

    @Test
    void afterInitializing_ScoreIsZero() {
        assertEquals(0, game.getScore());
    }

    @Test
    void afterRollXTwice_ScoreIs2X() {
        game.roll(1);
        game.roll(1);
        assertEquals(2, game.getScore());
    }

    @Test()
    void rollMoreThan10_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> game.roll(11));
    }

    @Test
    void twoRollsCombinedMoreThan10_throwsException() {
        game.roll(5);
        assertThrows(IllegalArgumentException.class, () -> game.roll(9));
    }

    @Test
    void afterTwoFramesWithRollX_scoreIsTwoX() {
        game.roll(4);
        game.roll(4);
        game.roll(4);
        game.roll(4);
        assertEquals(16, game.getScore());
    }

    @Test
    void afterSpare_SpareBonusIsApplied() {
        game.roll(5);
        game.roll(5);
        game.roll(1);
        game.roll(1);
        assertEquals(14, game.getScore());
    }

    @Test
    void afterStrike_StrikeBonusIsApplied() {
        game.roll(10);
        game.roll(1);
        game.roll(1);
        assertEquals(14, game.getScore());
        game.roll(1);
        game.roll(1);
        assertEquals(18, game.getScore());

    }

    @Test
    void afterInitialization_gameIsNotOver() {
        assertFalse(game.isOver());
    }

    @Test
    void gameEndsAfter10Frames() {
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }

        assertTrue(game.isOver());
    }
}
