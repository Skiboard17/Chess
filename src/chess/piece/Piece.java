package chess.piece;

import chess.UI.Block;
import chess.UI.Promotion;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import static chess.gameplay.Game.*;
import static chess.gameplay.Turn.isWhiteTurn;
import static chess.util.Util.*;

public abstract class Piece extends Group implements Clickable {
    private final boolean isWhite;
    private Block position;
    private boolean selected;
    public ImageView pieceImage;

    public Piece(Image image, boolean isWhite, Block position) {
        super();
        this.pieceImage = new ImageView(image);
        this.getChildren().add(pieceImage);
        this.isWhite = isWhite;
        this.position = position;
        if (position != null) {
            translate();
            if (isWhite) {
                WhitePieces.getChildren().add(this);
            } else {
                BlackPieces.getChildren().add(this);
            }
        }
        EventHandler<MouseEvent> eventHandler = e -> click();
        setOnMouseClicked(eventHandler);
    }

    /***
     *
     * @return true if works as intended, false if no action happens after
     */
    public boolean click() {
        if (!gameOn) {
            return false;
        }
        if (waitingForPromotion) {
            if (position == null) {
                Promotion.promote(this);
                return true;
            }
            return false;
        }
        // start piece color doesn't match
        if (start == null && isWhiteTurn != this.isWhite) {
            return false;
        }
        if (selected) {
            this.position.deselect();
            return false;
        }
        colorize(this.getPosition(), Color.LIGHTBLUE);
        selected = true;
        trackMove(this.position);
        return true;
    }

    public void translate() {
        int[] coordinate = convert((int) position.getX(), (int) position.getY(), false);
        // plus 10 to make image centered in the block.
        int offset = (BLOCK_SIZE - IMAGE_SIZE) / 2;
        relocate(coordinate[0] * BLOCK_SIZE + offset, coordinate[1] * BLOCK_SIZE + offset);
    }

    // caller of moving a piece from start to end
    public static void movePiece(Block start, Block end) {
        Piece piece = start.getPiece();
        // move successfully
        if (piece.canMove(end)) {
            movePieceHelper(start, end);
            if (piece instanceof Pond) {
                int endLine = piece.isWhite() ? 8 : 1;
                if (end.getPosition()[1] == endLine) {
                    Pond pond = (Pond) piece;
                    Promotion.makePromotion(pond);
                }
            }
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
                    if (castleStart != null) {
                        Piece castle = castleStart.getPiece();
                        int castleEndX = endPos[0] == 7 ? 6 : 4;
                        Block castleEnd = Block.findBlock(castleEndX, castleStartY);
                        validityHelper(castleStart, castleEnd);
                        castle.translate();
                    }
                }
                ((King) startPiece).setMoved();
            } else if (startPiece instanceof Castle) {
                ((Castle) startPiece).setMoved();
            } else if (startPiece instanceof Pond) {
                // check for en passant
                int[] startPos = start.getPosition();
                int[] endPos = end.getPosition();
                if (Math.abs(startPos[0] - endPos[0]) == 1
                        && Math.abs(startPos[1] - endPos[1]) == 1
                        && endPiece == null) {
                    int offset = startPiece.isWhite ? -1 : 1;
                    Block block = Block.findBlock(endPos[0], endPos[1] + offset);
                    if (block == null) return;
                    Group opponentGroup = startPiece.isWhite ? BlackPieces : WhitePieces;
                    opponentGroup.getChildren().remove(block.getPiece());
                    block.setPiece(null);
                }
            }
            lastMove = new Block[]{start, end};
            startPiece.translate();
            isWhiteTurn = !isWhiteTurn;
        }
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
        if (start == null || end == null) {
            return false;
        }
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
        return king.notChecked(king.getPosition());
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

    public Block getPosition() {
        return position;
    }

    public void setPosition(Block position) {
        this.position = position;
    }

    @Override
    public String toString() {
        String color = isWhite() ? "White " : "Black ";
        return color + this.getClass().getSimpleName() + " at " + position;
    }
}
