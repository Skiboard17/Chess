package chess.piece;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static chess.main.Main.*;

public class King extends Piece {
    public King(int x, int y) {
        super(x, y);
        setType(PieceType.KING);
        Image king = null;
        if (isWhite()) {
            king = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_klt60.png");
        } else {
            king = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_kdt60.png");
        }
        ImageView imageView = new ImageView(king);
        this.getChildren().add(imageView);
    }
}
