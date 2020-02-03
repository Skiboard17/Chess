package chess.piece;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bishop extends Piece {

    public Bishop(int x, int y) {
        super(x, y);
        setType(PieceType.BISHOP);
        Image bishop = null;
        if (isWhite()) {
            bishop = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_blt60.png");
        } else {
            bishop = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_bdt60.png");
        }
        ImageView imageView = new ImageView(bishop);
        getChildren().add(imageView);
    }
}
