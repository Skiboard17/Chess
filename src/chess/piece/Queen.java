package chess.piece;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Queen extends Piece {

    public Queen(int x, int y) {
        super(x, y);
        setType(PieceType.QUEEN);
        Image queen = null;
        if (isWhite()) {
            queen = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_qlt60.png");
        } else {
            queen = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_qdt60.png");
        }
        ImageView imageView = new ImageView(queen);
        getChildren().add(imageView);
    }
}
