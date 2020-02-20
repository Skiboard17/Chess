package chess.gameplay;

import chess.board.Block;
import chess.piece.King;
import chess.piece.Piece;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import static chess.piece.Piece.validMove;
import static chess.util.Util.*;

public class Game {
    public static Block start;
    public static Block end;
    public static Block[] lastMove;

    public static void trackMove(Block selected) {
        if (start == null) {
            start = selected;
            for (Node node : Blocks.getChildren()) {
                Block block = (Block) node;
                if (start.getPiece().canMove(block) && validMove(start, block)) {
                    colorize(block);
                }
            }
        } else if (end == null) {
            end = selected;
            Piece.movePiece(start, end);
            King king = (King) (Turn.isWhiteTurn ? whiteKing : blackKing);
            decolorize();
            if (!king.haveMove()) {
                king.getBlock().setFill(Color.RED);
            }
        }
    }
}
