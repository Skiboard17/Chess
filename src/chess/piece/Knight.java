package chess.piece;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Piece {

    public Knight(int x, int y) {
        super(x, y);
        setType(PieceType.KNIGHT);
        Image knight = null;
        if (isWhite()) {
            knight = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_nlt60.png");
        } else {
            knight = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_ndt60.png");
        }
        ImageView imageView = new ImageView(knight);
        getChildren().add(imageView);
    }
}
