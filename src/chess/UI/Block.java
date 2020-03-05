package chess.UI;

import chess.gameplay.Game;
import chess.piece.Clickable;
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
        this.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
        this.relocate(x * getWidth() + 1, y * getHeight() + 1);
        EventHandler<MouseEvent> eventEventHandler = e -> click();
        this.setOnMouseClicked(eventEventHandler);
    }

    public static Block findBlock(int x, int y) {
        if (x <= 0 || x >= 9 || y <= 0 || y >= 9) {
            return null;
        }
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
    public boolean click() {
        if (!Game.gameOn) {
            return false;
        }
        if (this.getPiece() != null) {
            return this.getPiece().click();
        } else if (Game.start != null) {
            Game.trackMove(this);
            System.out.println(true);
            return true;
        }
        System.out.println(false);
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
}
