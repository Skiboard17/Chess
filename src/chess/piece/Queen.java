package chess.piece;

public class Queen extends Piece {

    public static Queen makeQueen(int x, int y) {
        Queen queen = null;
        if (y > 2) {
            queen = new Queen("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_qlt60.png");
        } else {
            queen = new Queen("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_qdt60.png");
        }
        return queen;
    }

    public Queen(String url) {
        super(url);
        setType(PieceType.QUEEN);
    }
}
