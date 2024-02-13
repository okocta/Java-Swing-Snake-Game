import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class Food {
    private Point position;
    private Random random;
    private int boardWidth;
    private int boardHeight;
    Food(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        position = new Point();
        random = new Random();
        placeFood();
    }

    void draw(Graphics g, int tileSize) {
        Font font = new Font("Segoe UI Emoji", Font.PLAIN, tileSize*3/4); // Use a font supporting emojis
        g.setFont(font);
        g.setColor(Color.RED);
        // Calculate the position to center the emoji in the tile
        int x = position.x * tileSize ; // Adjust the x-coordinate for centering
        int y = position.y * tileSize + tileSize * 3 / 4;
        g.drawString("🍎", x, y); // Unicode for apple emoji
    }


    void placeFood(ArrayList<Point> snakeBody) {
        boolean validPosition = false;

        while (!validPosition) {
            // Generate random position for the food
            position.x = random.nextInt(boardWidth);
            position.y = random.nextInt(boardHeight);

            // Check if the food position overlaps with any part of the snake's body
            boolean collision = false;

            // Check if snakeBody is not null and not empty
            if (snakeBody != null && !snakeBody.isEmpty()) {
                for (Point bodyPart : snakeBody) {
                    if (bodyPart != null && position != null && position.equals(bodyPart)) {
                        collision = true;
                        break;
                    }
                }
            } else {
                // No snake body, so any position is valid
                validPosition = true;
            }

            if (!collision) {
                validPosition = true; // Found a valid position for the food
            }
        }
    }

    void placeFood() {
        placeFood(null); // Invoke the placeFood method without snake's body
    }


    Point getPosition() {
        return position;
    }
}
