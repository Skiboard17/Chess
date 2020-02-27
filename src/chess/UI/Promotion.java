package chess.UI;

import chess.piece.Piece;
import chess.piece.Pond;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static chess.util.Util.BLOCK_SIZE;

public class Promotion extends HBox {
    public Promotion(Group group) {
        // TODO: add centering and background and clickable
        super(8);
        this.getChildren().addAll(group.getChildren());
        this.prefHeight(BLOCK_SIZE);
        this.prefWidth(BLOCK_SIZE * 4);
    }

    public Piece promote() {
        return null;
    }

    public static Promotion makePromotion(Pond pond) {
        if (pond.isWhite()) {
            ImageView castle = new ImageView("file:img/Chess_rlt60.png");
            ImageView knight = new ImageView("file:img/Chess_nlt60.png");
            ImageView bishop = new ImageView("file:img/Chess_blt60.png");
            ImageView queen = new ImageView("file:img/Chess_qlt60.png");
            Group group = new Group(castle, knight, bishop, queen);
            return new Promotion(group);
        } else {
            ImageView castle = new ImageView("file:img/Chess_rdt60.png");
            ImageView knight = new ImageView("file:img/Chess_ndt60.png");
            ImageView bishop = new ImageView("file:img/Chess_bdt60.png");
            ImageView queen = new ImageView("file:img/Chess_qdt60.png");
            Group group = new Group(castle, knight, bishop, queen);
            return new Promotion(group);
        }
    }
}
