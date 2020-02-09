package chess.piece;

import chess.board.Block;

public class Knight extends Piece {

    public static Knight makeKnight(int x, int y) {
        Knight knight = null;
        if (y < 2) {
            knight = new Knight("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_nlt60.png", true);
        } else {
            knight = new Knight("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_ndt60.png", false);
        }
        return knight;
    }

    public Knight(String url, boolean isWhite) {
        super(url, isWhite);
        setType(PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Block start, Block end) {
        // TODO
        return false;
    }
}
