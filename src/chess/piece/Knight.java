package chess.piece;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static chess.main.Main.*;

public class Knight extends Piece {

    public static Knight makeKnight(int x, int y) {
        Knight knight = null;
        if (y > 2) {
            knight = new Knight("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_nlt60.png");
        } else {
            knight = new Knight("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_ndt60.png");
        }
        return knight;
    }

    public Knight(String url) {
        super(url);
        setType(PieceType.KNIGHT);
    }
}
