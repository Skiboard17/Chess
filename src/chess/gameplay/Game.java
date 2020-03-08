package chess.gameplay;

import chess.UI.Block;
import chess.UI.Main;
import chess.UI.Promotion;
import chess.piece.King;
import chess.piece.Piece;
import chess.piece.Pond;
import chess.util.Util;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import static chess.piece.Piece.validMove;
import static chess.util.Util.*;

public class Game {
    public static boolean gameOn;
    public static Block start;
    public static Block end;
    public static Block[] lastMove;

    public static void trackMove(Block selected) {
        if (start == null) {
            start = selected;
            for (Node node : Blocks.getChildren()) {
                Block block = (Block) node;
                if (start.getPiece().canMove(block) && validMove(start, block)) {
                    colorize(block, Color.LIGHTGREEN);
                }
            }
            colorize(start, Color.LIGHTBLUE);
        } else if (end == null) {
            Piece piece = start.getPiece();
            end = selected;
            Piece.movePiece(start, end);
            if (piece instanceof Pond && (selected.getPosition()[1] == 8 || selected.getPosition()[1] == 0)) {
                Pond pond = (Pond) piece;
                Promotion.makePromotion(pond);
            }
            King king = (King) (Turn.isWhiteTurn ? whiteKing : blackKing);
            decolorize();
            if (!king.haveMove()) {
                king.getPosition().setFill(Color.RED);
                gameOn = false;
                // TODO: add a restart button that calls startGame()
            }
        }
    }

    public static void startGame() {
        gameOn = true;
        Parent root = Main.setup();
        Scene scene = new Scene(root);
        Util.window.setScene(scene);
    }
}
