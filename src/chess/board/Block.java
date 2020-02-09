package chess.board;

import chess.gameplay.Game;
import chess.piece.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static chess.Constants.*;

public class Block extends StackPane {
    private Rectangle tile;
    private Piece piece;
    private boolean isLight;
    private boolean selected;

    public Block(boolean isLight, int x, int y) {
        this.isLight = isLight;
        this.setWidth(BLOCK_SIZE);
        this.setHeight(BLOCK_SIZE);
        Rectangle tile = new Rectangle();
        this.tile = tile;
        tile.setFill(isLight ? Color.valueOf("#E6CCAB") : Color.valueOf("#9D571B"));
        tile.setWidth(BLOCK_SIZE);
        tile.setHeight(BLOCK_SIZE);
        tile.setX(x + 1);
        tile.setY(8 - y);
        getChildren().add(tile);
        this.relocate(x * getWidth(), y * getHeight());
        EventHandler<MouseEvent> eventHandler = e -> click();
        setOnMouseClicked(eventHandler);
    }

    public static Block findBlock(int x, int y) {
        int index = (8 - y) * 8 + x - 1;
        return (Block) Blocks.getChildren().get(index);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            if (this.getChildren().size() == 2) {
                this.getChildren().set(1, piece);
                return;
            }
            this.getChildren().add(piece);
        }
    }

    public int[] getPosition() {
        return new int[]{(int) tile.getX(), (int) tile.getY()};
    }

    public void click() {
        // deselect if selected
        if (selected) {
            deselect();
            return;
        }
        // select for the first time
        tile.setFill(Color.LIGHTBLUE);
        selected = true;
        Game.trackBlock(this);
    }

    public void deselect() {
        selected = false;
        restoreColor();
        if (Game.start == this) {
            Game.start = null;
        } else if (Game.end == this) {
            Game.end = null;
        }
    }

    public void restoreColor() {
        tile.setFill(isLight ? Color.valueOf("#E6CCAB") : Color.valueOf("#9D571B"));
    }


}
