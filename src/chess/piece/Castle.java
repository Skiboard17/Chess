package chess.piece;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Castle extends Piece {

    public Castle(int x, int y) {
        super(x, y);
        setType(PieceType.CASTLE);
        Image castle = null;
        if (isWhite()) {
            castle = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_rlt60.png");
        } else {
            castle = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_rdt60.png");
        }
        ImageView imageView = new ImageView(castle);
        getChildren().add(imageView);
    }
}
