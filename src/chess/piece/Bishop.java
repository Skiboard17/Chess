package chess.piece;

public class Bishop extends Piece {

    public static Bishop makeBishop(int x, int y) {
        Bishop bishop = null;
        if (y > 2) {
            bishop = new Bishop("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_blt60.png");
        } else {
            bishop = new Bishop("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_bdt60.png");
        }
        return bishop;
    }

    public Bishop(String url) {
        super(url);
        setType(PieceType.BISHOP);
    }
}
