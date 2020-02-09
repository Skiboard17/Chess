package chess;

import chess.gameplay.Game;
import javafx.scene.Group;

public class Constants {
    public static final int BLOCK_SIZE = 80;
    public static final int HEIGHT = 8;
    public static final int WIDTH = 8;

    public static Group Blocks = new Group();
    public static Group Pieces = new Group();
    public static Game moving = new Game();
}
