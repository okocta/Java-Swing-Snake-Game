import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int boardWidth = 600;
        int boardHeight = boardWidth;


            JFrame frame = new JFrame("Snake");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);//not resizable

            SnakeGame snakeGame = new SnakeGame(boardWidth, boardHeight);
            frame.add(snakeGame);
            frame.pack();//withouyt the bar with x

            frame.setLocationRelativeTo(null);//positioning the screen
            frame.setVisible(true);
            snakeGame.requestFocus();

    }
}
