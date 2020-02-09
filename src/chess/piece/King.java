package chess.piece;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static chess.main.Main.*;

public class King extends Piece {
    public static King makeKing(int x, int y) {
        King king = null;
        if (y > 2) {
            king = new King("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_klt60.png");
        } else {
            king = new King("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_kdt60.png");
        }
        return king;
    }

    public King(String url) {
        super(url);
        setType(PieceType.KING);
    }
}
