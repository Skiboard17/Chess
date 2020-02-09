package chess.piece;

public class Castle extends Piece {

    public static Castle makeCastle(int x, int y) {
        Castle castle = null;
        if (y > 2) {
            castle = new Castle("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_rlt60.png");
        } else {
            castle = new Castle("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_rdt60.png");
        }
        return castle;
    }

    public Castle(String url) {
        super(url);
        setType(PieceType.CASTLE);
    }

//    public boolean move(int x, int y){
//        // TODO
//        if (hasSameColor){
//            return false;
//        }
//        if (x == this.)
//        return true;
//    }
}
