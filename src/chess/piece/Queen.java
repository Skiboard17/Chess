package chess.piece;

import chess.board.Block;

public class Queen extends Piece {

    public static Queen makeQueen(int x, int y) {
        Queen queen = null;
        if (y < 2) {
            queen = new Queen("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_qlt60.png", true);
        } else {
            queen = new Queen("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_qdt60.png", false);
        }
        return queen;
    }

    public Queen(String url, boolean isWhite) {
        super(url, isWhite);
        setType(PieceType.QUEEN);
    }

    @Override
    public boolean canMove(Block start, Block end) {
        // TODO
        return false;
    }
}
