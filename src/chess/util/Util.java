package chess.util;

import chess.UI.Block;
import chess.piece.Piece;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static final int BLOCK_SIZE = 80;
    public static final int IMAGE_SIZE = 60;
    public static final int HEIGHT = 8;
    public static final int WIDTH = 8;
    public static double WINDOW_WIDTH;
    public static double WINDOW_HEIGHT;
    public static boolean waitingForPromotion;
    public static Group WhitePieces;
    public static Group BlackPieces;
    public static Piece whiteKing;
    public static Piece blackKing;
    public static Piece ignored;
    private static Group ignoreGroup;

    // UI-Related Constants
    public static Pane mainPage;
    public static Scene mainScene;
    public static Stage window;
    public static Group Blocks;
    public static List<Block> colored = new ArrayList<>(16);

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

    public static void colorize(Block block, Color color) {
        block.setFill(color);
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

    public static void centering(Region child, Region parent) {
        double x = parent.getPrefHeight() / 2 - child.getPrefWidth() / 2;
        double y = parent.getPrefHeight() / 2 - child.getPrefHeight() / 2;
        child.relocate(x, y);
    }
}
