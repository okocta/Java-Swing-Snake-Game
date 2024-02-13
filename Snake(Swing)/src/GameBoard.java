import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class GameBoard {
    private int width;
    private int height;
    private int tileSize = 25;

    GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    int getTileSize() {
        return tileSize;
    }
}