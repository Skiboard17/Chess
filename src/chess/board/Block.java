package chess.board;

import chess.piece.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static chess.main.Main.BLOCK_SIZE;
import static chess.main.Main.Blocks;

public class Block extends StackPane {
    private Rectangle tile;
    private Piece piece;
    private boolean borderOn;

    public Block(boolean isLight, int x, int y) {
        this.setWidth(BLOCK_SIZE);
        this.setHeight(BLOCK_SIZE);
        Rectangle tile = new Rectangle();
        this.tile = tile;
        tile.setFill(isLight ? Color.valueOf("#E6CCAB") : Color.valueOf("#9D571B"));
        tile.setWidth(BLOCK_SIZE);
        tile.setHeight(BLOCK_SIZE);
        getChildren().add(tile);
        this.relocate(x * getWidth(), y * getHeight());
        EventHandler<MouseEvent> eventHandler = e -> changeBorder();
        setOnMouseClicked(eventHandler);
    }

    public static Block findBlock(int x, int y) {
        int index = y * 8 + x;
        return (Block) Blocks.getChildren().get(index);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            this.getChildren().add(piece);
        }
    }

    public void changeBorder() {
        if (borderOn) {
            this.tile.setStroke(null);
        } else {
            this.tile.setStroke(Color.BLUE);
        }
        borderOn = !borderOn;
        System.out.println("border");
    }
}
