package chess.gameplay;

import chess.board.Block;
import chess.piece.King;
import chess.piece.Piece;
import javafx.scene.Node;
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
                    colorize(block, Color.LIGHTBLUE);
                }
            }
        } else if (end == null) {
            end = selected;
            // TODO: fix turning logic (wrong when checking)
            Piece.movePiece(start, end);
            King king = (King) (Turn.isWhiteTurn ? whiteKing : blackKing);
            decolorize();
            if (!king.haveMove()) {
                king.getBlock().setFill(Color.RED);
                gameOn = false;
            }
        }
    }

    public static void startGame(){
        gameOn = true;
        Scene scene = new Scene(Main.setup());
        Main.primaryStage.setScene(scene);
    }
}
