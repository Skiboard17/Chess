package chess.piece;

import chess.board.Block;

public class Bishop extends Piece {

    public static Bishop makeBishop(int x, int y) {
        Bishop bishop = null;
        if (y < 2) {
            bishop = new Bishop("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_blt60.png", true);
        } else {
            bishop = new Bishop("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_bdt60.png", false);
        }
        return bishop;
    }

    public Bishop(String url, boolean isWhite) {
        super(url, isWhite);
        setType(PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Block start, Block end) {
        return false;
        // TODO
    }
}
