package chess.UI;

import chess.piece.*;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static chess.util.Util.*;

public class Promotion extends HBox {
    public Promotion(Group group) {
        // TODO: add centering and background and clickable
        super(5);
        this.getChildren().addAll(group.getChildren());
        this.prefHeight(IMAGE_SIZE);
        this.prefWidth(IMAGE_SIZE * 4);
        this.relocate(WINDOW_WIDTH / 2 - IMAGE_SIZE * 2, WINDOW_HEIGHT / 2 - IMAGE_SIZE / 2);
    }

    public Piece promote() {
        return null;
    }

    public static Promotion makePromotion(Pond pond) {
        if (pond.isWhite()) {
            Piece castle = Castle.makeCastle(3, 4);
            Piece knight = Knight.makeKnight(4, 4);
            Piece bishop = Bishop.makeBishop(5, 4);
            Piece queen = Queen.makeQueen(6, 4);
            Group group = new Group(castle, knight, bishop, queen);
            for (int i = 0; i < 4; i++) {
                Piece piece = (Piece) (group.getChildren().get(i));
                System.out.println(piece);

                // TODO: implement this without concurrent modification
            }
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
