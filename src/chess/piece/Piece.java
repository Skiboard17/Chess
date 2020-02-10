package chess.piece;

import chess.gameplay.Turn;
import chess.util.Util;
import chess.board.Block;
import chess.gameplay.Clickable;
import chess.gameplay.Game;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import static chess.util.Util.*;

public abstract class Piece extends ImageView implements Clickable {
    private PieceType type;
    private final boolean isWhite;
    private Block position;
    private boolean selected;

    public Piece(Image url, boolean isWhite, Block position) {
        super(url);
        this.isWhite = isWhite;
        this.position = position;
        translate();
        if (isWhite) {
            WhitePieces.getChildren().add(this);
        } else {
            BlackPieces.getChildren().add(this);
        }
        EventHandler<MouseEvent> eventHandler = e -> click();
        setOnMouseClicked(eventHandler);
    }


    public void click() {
        // deselect if selected
        if (Game.start == null && Turn.isWhiteTurn != this.isWhite) {
            return;
        }
        if (selected) {
            this.position.deselect();
            return;
        }
        this.position.setFill(Color.LIGHTBLUE);
        selected = true;
        Game.trackMove(this.position);
    }

    public void translate() {
        int[] coordinate = Util.convert((int) position.getX(), (int) position.getY(), false);
        relocate(coordinate[0] * BLOCK_SIZE + 10, coordinate[1] * BLOCK_SIZE + 10);
    }

    public static void movePiece(Block start, Block end) {
        Piece piece = start.getPiece();
        King king = (King) (start.getPiece().isWhite ? whiteKing : blackKing);
        // move successfully
        // TODO: fix the king's move
        if (piece.canMove(end) && (!king.checked(end))) {
            piece.position = end;
            piece.translate();
            start.deselect();
            start.setPiece(null);
            if (end.getPiece() != null) {
                if (end.getPiece().isWhite) {
                    WhitePieces.getChildren().remove(end.getPiece());
                } else {
                    BlackPieces.getChildren().remove(end.getPiece());
                }
            }
            end.deselect();
            end.setPiece(piece);
            start.restoreColor();
            end.restoreColor();
            Turn.isWhiteTurn = !Turn.isWhiteTurn;
        } else {
            start.deselect();
            end.deselect();
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean canMove(Block end);

    public abstract boolean canReach(Block end);

    public boolean hasSameColor(Block end) {
        if (end.getPiece() == null) {
            return false;
        }
        return end.getPiece().isWhite() == this.isWhite();
    }

    public Block getBlock() {
        return position;
    }
}
