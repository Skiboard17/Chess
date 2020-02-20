package chess.piece;

import chess.gameplay.Turn;

import chess.board.Block;
import chess.piece.Clickable;

import static chess.gameplay.Game.*;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import static chess.util.Util.*;

public abstract class Piece extends ImageView implements Clickable {
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
        if (start == null && Turn.isWhiteTurn != this.isWhite) {
            return;
        }
        if (selected) {
            this.position.deselect();
            return;
        }
        this.position.setFill(Color.LIGHTBLUE);
        selected = true;
        trackMove(this.position);
    }


    public void translate() {
        int[] coordinate = convert((int) position.getX(), (int) position.getY(), false);
        relocate(coordinate[0] * BLOCK_SIZE + 10, coordinate[1] * BLOCK_SIZE + 10);
    }

    // caller of moving a piece from start to end
    public static void movePiece(Block start, Block end) {
        Piece piece = start.getPiece();
        // move successfully
        if (piece.canMove(end)) {
            movePieceHelper(start, end);
        }
        start.deselect();
        end.deselect();
    }

    // the main logic of moving a piece
    // calling validityHelper() and restore if not valid
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
        } else {
            if (startPiece instanceof King) {
                int[] startPos = start.getPosition();
                int[] endPos = end.getPosition();
                if (Math.abs(startPos[0] - endPos[0]) == 2) {
                    int castleStartX = endPos[0] == 7 ? 8 : 1;
                    int castleStartY = endPos[1];
                    Block castleStart = Block.findBlock(castleStartX, castleStartY);
                    Piece castle = castleStart.getPiece();
                    int castleEndX = endPos[0] == 7 ? 6 : 4;
                    Block castleEnd = Block.findBlock(castleEndX, castleStartY);
                    validityHelper(castleStart, castleEnd);
                    castle.translate();
                }
                ((King) startPiece).setMoved();
            } else if (startPiece instanceof Castle) {
                ((Castle) startPiece).setMoved();
            }
            lastMove = new Block[]{start, end};
        }
        startPiece.translate();
        Turn.isWhiteTurn = !Turn.isWhiteTurn;
    }

    // check if a move is valid by moving, checking, and restoring
    public static boolean validMove(Block start, Block end) {
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

    // a helper that moves a piece from start to end
    // Note: it is not restored to the initial state
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
        return king.notChecked(king.getBlock());
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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
