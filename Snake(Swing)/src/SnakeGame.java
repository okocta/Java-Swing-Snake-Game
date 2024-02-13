import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private final int boardWidth;
    private final int boardHeight;
    private final int tileSize = 25;

    private final Snake snake;
    private final Food food;
    private final Timer gameLoop;
    private boolean gameover = false;

    private boolean showGameOverScreen = false;
    private JButton restartButton;
    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snake = new Snake();
        food = new Food(24, 24);
        food.placeFood();

        gameLoop = new Timer(150, this);
        gameLoop.start();

        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        add(restartButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g, tileSize);
        food.draw(g, tileSize);


        if (gameover) {
            g.clearRect(0,0,boardWidth,boardHeight);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(Color.RED);
            g.drawString("GAME OVER" , boardWidth / 2 -150, boardHeight / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 22));
            g.drawString("Score:"  + String.valueOf(snake.getBodySize()-1), boardWidth / 2-40, boardHeight / 2+30);
            restartButton.setVisible(true);

        } else {
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Score: " + String.valueOf(snake.getBodySize()-1), tileSize - 16, tileSize);
            restartButton.setVisible(false);
        }
    }
    private void restartGame() {
        gameover = false;
        showGameOverScreen = false;
        snake.reset();
        food.placeFood();
        gameLoop.start();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameover) {
            snake.move();

            if (snake.checkGameOver(24, 24, food.getPosition())) {
                gameover = true;
                showGameOverScreen = true;
                gameLoop.stop();


            }

            if (snake.checkCollisionWithFood(food.getPosition())) {
                snake.grow();
                food.placeFood();
            }

            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int velocityX = snake.getVelocityX();
        int velocityY = snake.getVelocityY();

        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            snake.setDirection(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            snake.setDirection(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            snake.setDirection(-1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            snake.setDirection(1, 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
