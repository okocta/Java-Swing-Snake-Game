import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Point> body;
    private int velocityX;
    private int velocityY;

    Snake() {
        body = new ArrayList<>();
        body.add(new Point(5, 5)); // Default starting place
        velocityX = 0;
        velocityY = 0;
    }

    void draw(Graphics g, int tileSize) {
        // Draw the snake body
        for (int i = 0; i < body.size(); i++) {
            g.setColor(Color.GREEN);
            if (i == 0) {
                // Draw the snake's head without tongue
                g.fillRect(body.get(i).x * tileSize, body.get(i).y * tileSize, tileSize, tileSize);


                // Draw the tongue based on the snake's movement direction
                g.setColor(Color.RED);
                int tongueOffset = tileSize / 4; // Adjust the tongue's position

                if (velocityX == 1) {
                    g.fillOval(body.get(i).x * tileSize + tileSize + 5 - tongueOffset, body.get(i).y * tileSize + tileSize / 2 - tileSize / 8, tileSize / 3+7, tileSize / 3-2);
                } else if (velocityX == -1) {
                    g.fillOval(body.get(i).x * tileSize - tileSize / 3 - tongueOffset, body.get(i).y * tileSize + tileSize / 2 - tileSize / 8, tileSize / 3+7, tileSize / 3-2);
                } else if (velocityY == 1) {
                    g.fillOval(body.get(i).x * tileSize + tileSize / 2 - tileSize / 8, body.get(i).y * tileSize + tileSize + 6 - tongueOffset, tileSize / 3-2, tileSize / 3+6);
                } else if (velocityY == -1) {
                    g.fillOval(body.get(i).x * tileSize + tileSize / 2 - tileSize / 8, body.get(i).y * tileSize - tileSize / 3 - tongueOffset, tileSize / 3-2, tileSize / 3+6);
                }
            } else {
                // Draw the snake's body
                g.fillRect(body.get(i).x * tileSize, body.get(i).y * tileSize, tileSize, tileSize);
            }
        }
    }

    int getBodySize() {
        return body.size();
    }

    void move() {
        Point newHead = new Point(body.get(0).x + velocityX, body.get(0).y + velocityY);
        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    void setDirection(int newVelocityX, int newVelocityY) {
        // Avoid opposite direction change (prevent turning back on itself)
        if (velocityX != -newVelocityX || velocityY != -newVelocityY) {
            velocityX = newVelocityX;
            velocityY = newVelocityY;
        }
    }
    void reset() {
        // Clear the snake's body and reset its state
        body.clear();
        body.add(new Point(5, 5)); // Reset starting place
        velocityX = 0;
        velocityY = 0;
    }

    boolean checkCollisionWithItself() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
    public boolean checkCollisionWithBoundary(int boardWidth, int boardHeight) {
        Point head = body.get(0);
        return head.x < 0 || head.x >= boardWidth || head.y < 0 || head.y >= boardHeight;
    }
    boolean checkCollisionWithFood(Point foodPosition) {
        Point head = body.get(0);
        return head.equals(foodPosition);
    }

    void grow() {
        // Add a new body part at the current tail position
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail));
    }

    boolean checkGameOver(int boardWidth, int boardHeight, Point foodPosition) {
        return checkCollisionWithBoundary(boardWidth, boardHeight)
                || checkCollisionWithItself()
                ;
    }
    int getVelocityX() {
        return velocityX;
    }

    int getVelocityY() {
        return velocityY;
    }
}
