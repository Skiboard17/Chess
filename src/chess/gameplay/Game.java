package chess.gameplay;

import chess.board.Block;
import chess.piece.Piece;

public class Game {
    public static Block start;
    public static Block end;

    public static void trackBlock(Block selected) {
        if (start == null) {
            start = selected;
        } else if (end == null) {
            end = selected;
            Piece.movePiece(start, end);
        }
    }
}
