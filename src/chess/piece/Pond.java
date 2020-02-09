package chess.piece;

public class Pond extends Piece {

    public static Pond makePond(int x, int y) {
        Pond pond = null;
        if (y > 2) {
            pond = new Pond("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_plt60.png");
        } else {
            pond = new Pond("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_pdt60.png");
        }
        return pond;
    }

    public Pond(String url) {
        super(url);
        setType(PieceType.POND);
    }
}
