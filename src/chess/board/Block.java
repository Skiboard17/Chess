package chess.board;

import chess.piece.Clickable;
import chess.gameplay.Game;
import chess.piece.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static chess.util.Util.*;

public class Block extends Rectangle implements Clickable {
    private Piece piece;
    private boolean isLight;

    public Block(boolean isLight, int x, int y) {
        this.isLight = isLight;
        this.setWidth(BLOCK_SIZE);
        this.setHeight(BLOCK_SIZE);
        this.setFill(isLight ? Color.valueOf("#E6CCAB") : Color.valueOf("#9D571B"));
        this.setWidth(BLOCK_SIZE);
        this.setHeight(BLOCK_SIZE);
        this.setX(x + 1);
        this.setY(8 - y);
        this.relocate(x * getWidth(), y * getHeight());
        EventHandler<MouseEvent> eventEventHandler = e -> click();
        this.setOnMouseClicked(eventEventHandler);
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
    }

    public int[] getPosition() {
        return new int[]{(int) this.getX(), (int) this.getY()};
    }

    public boolean isLight() {
        return isLight;
    }

    public void deselect() {
        if (this.getPiece() != null) {
            this.getPiece().setSelected(false);
        }
        this.restoreColor();
        if (Game.start == this) {
            Game.start = null;
        } else if (Game.end == this) {
            Game.end = null;
        }
        decolorize();
    }

    public void restoreColor() {
        this.setFill(this.isLight() ? Color.valueOf("#E6CCAB") : Color.valueOf("#9D571B"));
    }

    @Override
    public void click() {
        if (this.getPiece() != null) {
            this.getPiece().click();
        } else if (Game.start != null) {
            Game.trackMove(this);
        }
    }
}
