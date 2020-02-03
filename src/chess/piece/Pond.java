package chess.piece;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pond extends Piece {

    public Pond(int x, int y) {
        super(x, y);
        setType(PieceType.POND);
        Image pond = null;
        if (isWhite()) {
            pond = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_plt60.png");
        } else {
            pond = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_pdt60.png");
        }
        ImageView imageView = new ImageView(pond);
        getChildren().add(imageView);
    }
}
