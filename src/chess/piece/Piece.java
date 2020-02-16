package chess.piece;

import chess.gameplay.Turn;

import chess.board.Block;
import chess.gameplay.Clickable;
import chess.gameplay.Game;
import javafx.event.EventHandler;
import javafx.scene.Group;
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
        int[] coordinate = convert((int) position.getX(), (int) position.getY(), false);
        relocate(coordinate[0] * BLOCK_SIZE + 10, coordinate[1] * BLOCK_SIZE + 10);
    }

    public static void movePiece(Block start, Block end) {
        Piece piece = start.getPiece();
        King king = (King) (start.getPiece().isWhite ? whiteKing : blackKing);
        // TODO: shorten the code by checking the king after the move.
        // move successfully
        if (piece.canMove(end)) {
            movePieceHelper(start, end);
        }
        start.deselect();
        end.deselect();
    }

    public static void movePieceHelper(Block start, Block end) {
        Piece startPiece = start.getPiece();
        Piece endPiece = end.getPiece();
        boolean isValid = validityHelper(start, end);
        if (!isValid) {
            startPiece.position = start;
            start.setPiece(startPiece);
            end.setPiece(endPiece);
            if (endPiece != null) {
                Group group = endPiece.isWhite ? WhitePieces : BlackPieces;
                group.getChildren().add(endPiece);
            }
        }
        startPiece.translate();
        Turn.isWhiteTurn = !Turn.isWhiteTurn;
    }

    public static boolean ValidMove(Block start, Block end) {
        Piece startPiece = start.getPiece();
        Piece endPiece = end.getPiece();
        boolean result = validityHelper(start, end);
        // restore anyway
        startPiece.position = start;
        start.setPiece(startPiece);
        end.setPiece(endPiece);
        if (endPiece != null) {
            Group group = endPiece.isWhite ? WhitePieces : BlackPieces;
            group.getChildren().add(endPiece);
        }
        return result;
    }

    private static boolean validityHelper(Block start, Block end) {
        King king = (King) (start.getPiece().isWhite ? whiteKing : blackKing);
        Piece startPiece = start.getPiece();
        Piece endPiece = end.getPiece();
        // start the actual method
        startPiece.position = end;
        // pretend it is successful
        start.setPiece(null);
        if (endPiece != null) {
            Group group = endPiece.isWhite ? WhitePieces : BlackPieces;
            group.getChildren().remove(endPiece);
        }
        end.setPiece(startPiece);
        start.restoreColor();
        end.restoreColor();
        // check if it is a valid move
        return king.notChecked(null, king.getBlock());
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

    public PieceType getType() {
        return type;
    }
}
