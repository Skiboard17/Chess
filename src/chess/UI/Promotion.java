package chess.UI;

import chess.piece.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static chess.gameplay.Game.checkKingAlive;
import static chess.util.Util.*;

public class Promotion extends BorderPane {

    private static Pond promoting;
    private static Promotion promotion;

    public Promotion(Group group) {
        // TODO: add centering and background and clickable
        super();
        this.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95);");
        this.prefHeight(IMAGE_SIZE * 1.5);
        this.prefWidth(IMAGE_SIZE * 4);
        this.relocate(WINDOW_WIDTH / 2 - IMAGE_SIZE * 2, WINDOW_HEIGHT / 2 - IMAGE_SIZE / 2);
        Label label = new Label("Please Select Your Promotion");
        label.setPrefHeight(IMAGE_SIZE / 2);
        label.setFont(new Font("Rockwell", 16));
        label.setTextAlignment(TextAlignment.CENTER);
        this.setTop(label);
        BorderPane.setAlignment(label, Pos.TOP_CENTER);
        HBox hBox = new HBox(0);
        hBox.getChildren().addAll(group.getChildren());
        hBox.setPrefHeight(IMAGE_SIZE);
        hBox.setPrefWidth(IMAGE_SIZE * 4);
        this.setCenter(hBox);
        // finishing up
        promotion = this;
        pane.getChildren().add(this);
        waitingForPromotion = true;
    }

    public static void promote(Piece piece) {
        // change appearance on board
        Group pieces = promoting.isWhite() ? WhitePieces : BlackPieces;
        pieces.getChildren().remove(promoting);
        Block block = promoting.getPosition();
        piece.setPosition(block);
        block.setPiece(piece);
        pieces.getChildren().add(piece);
        piece.translate();
        // finishing up
        deconstruct();
        waitingForPromotion = false;
        checkKingAlive();
    }

    public static void deconstruct() {
        pane.getChildren().remove(promotion);
    }

    public static void makePromotion(Pond pond) {
        // -1 and 9 represent white and black pond promotion
        promoting = pond;
        int x = pond.isWhite() ? -1 : 9;
        int y = pond.isWhite() ? -1 : 9;
        Piece castle = Castle.makeCastle(x, y);
        Piece knight = Knight.makeKnight(x, y);
        Piece bishop = Bishop.makeBishop(x, y);
        Piece queen = Queen.makeQueen(x, y);
        Group group = new Group(castle, knight, bishop, queen);
        new Promotion(group);
    }
}
