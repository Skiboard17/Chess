package chess.piece;

import chess.board.Block;

public class King extends Piece {
    public static King makeKing(int x, int y) {
        King king = null;
        if (y < 2) {
            king = new King("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_klt60.png", true);
        } else {
            king = new King("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_kdt60.png", false);
        }
        return king;
    }

    public King(String url, boolean isWhite) {
        super(url, isWhite);
        setType(PieceType.KING);
    }

    @Override
    public boolean canMove(Block start, Block end) {
        // TODO
        return false;
    }
}
