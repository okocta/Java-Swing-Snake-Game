import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {

    @Test
    public void testCheckCollisionWithItself_NoCollision() {
        Snake snake = new Snake();
        assertFalse(snake.checkCollisionWithItself());
    }

    @Test
    public void testCheckCollisionWithItself_Collision() {
        Snake snake = new Snake();
        snake.grow(); // Add a body part
        assertTrue(snake.checkCollisionWithItself());
    }

    @Test
    public void testSnakeGrow_Failing() {
        Snake snake = new Snake();
        int initialSize = snake.getBodySize();

        // Call the grow method
        snake.grow();

        // Intentionally assert an incorrect condition
        // This will cause the test to fail
        assertEquals("Expected the snake size to be different after calling grow", initialSize, snake.getBodySize());
    }

    @Test
    public void testSnakeGrow_Passing() {
        Snake snake = new Snake();
        int initialSize = snake.getBodySize();

        // Call the grow method
        snake.grow();

        // Correct assertion
        assertEquals("Expected the snake size to increase after calling grow", initialSize + 1, snake.getBodySize());
    }
}
