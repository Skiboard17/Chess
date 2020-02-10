package chess;

import chess.gameplay.Game;
import javafx.scene.Group;

public class Util {
    public static final int BLOCK_SIZE = 80;
    public static final int HEIGHT = 8;
    public static final int WIDTH = 8;

    public static Group WhitePieces = new Group();
    public static Group BlackPieces = new Group();
    public static Group Blocks = new Group();
    public static Game moving = new Game();

    public static int[] convert(int x, int y, boolean comp) {
        if (comp) {
            x = x + 1;
        } else {
            x = x - 1;
        }
        return new int[]{x, 8 - y};
    }
}
