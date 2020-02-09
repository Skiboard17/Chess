package chess.board;

import chess.piece.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static chess.main.Main.BLOCK_SIZE;
import static chess.main.Main.Blocks;

public class Block extends Rectangle {
    private boolean isLight;
    private Piece piece;
    private boolean borderOn;

    public Block(boolean isLight, int x, int y) {
        this.isLight = isLight;
        setX(x);
        setY(y);
        setWidth(BLOCK_SIZE);
        setHeight(BLOCK_SIZE);
        relocate(x * getWidth(), y * getHeight());
        EventHandler<MouseEvent> eventHandler = e -> changeBorder();
        setOnMouseClicked(eventHandler);
        setFill(isLight ? Color.valueOf("#E6CCAB") : Color.valueOf("#9D571B"));
    }

    public int[] getPosition() {
        return new int[]{(int) getX(), (int) getY()};
    }

    public boolean isLight() {
        return isLight;
    }

    public boolean hasPiece() {
        return piece == null;
    }

    public Piece getPiece() {
        return piece;
    }

    public static Block findBlock(int x, int y) {
        int index = y * 8 + x;
        return (Block) Blocks.getChildren().get(index);
    }

    public void changeBorder() {
        if (borderOn) {
            this.setStroke(null);
        } else {
            this.setStroke(Color.BLUE);
        }
        borderOn = !borderOn;
        System.out.println("border");
    }
}
