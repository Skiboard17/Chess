package chess.util;

import chess.board.Block;
import chess.piece.Piece;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static final int BLOCK_SIZE = 80;
    public static final int HEIGHT = 8;
    public static final int WIDTH = 8;

    public static Group WhitePieces = new Group();
    public static Group BlackPieces = new Group();
    public static Group Blocks = new Group();
    public static List<Block> colored = new ArrayList<>(16);
    public static Piece whiteKing;
    public static Piece blackKing;
    public static Piece ignored;
    private static Group ignoreGroup;
    public static Piece whiteChecker;
    public static Piece blackChecker;

    public static int[] convert(int x, int y, boolean comp) {
        if (comp) {
            x = x + 1;
        } else {
            x = x - 1;
        }
        return new int[]{x, 8 - y};
    }

    public static void decolorize() {
        for (Block block : colored) {
            block.restoreColor();
        }
        colored = new ArrayList<>();
    }

    public static void colorize(Block block) {
        block.setFill(Color.LIGHTBLUE);
        colored.add(block);
    }

    // temporarily remove the piece from a group
    public static void ignore(Piece piece, Group group) {
        if (group.getChildren().contains(piece)) {
            ignored = piece;
            ignoreGroup = group;
            group.getChildren().remove(piece);
        }
    }

    public static void undoIgnore() {
        if (ignored != null) {
            ignoreGroup.getChildren().add(ignored);
            ignored = null;
        }
    }
}
