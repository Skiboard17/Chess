package chess.piece;

import javafx.scene.image.Image;
import chess.board.Block;

public class Pond extends Piece {

    public static Pond makePond(int x, int y) {
        Pond pond = null;
        if (y <= 2) {
            pond = new Pond(new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_plt60.png"), true, Block.findBlock(x, y));
        } else {
            pond = new Pond(new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_pdt60.png"), false, Block.findBlock(x, y));
        }
        return pond;
    }

    public Pond(Image url, boolean isWhite, Block position) {
        super(url, isWhite, position);
        setType(PieceType.POND);
    }

    @Override
    public boolean canMove(Block end) {
        if (this.hasSameColor(end)) {
            return false;
        }
        int startX = this.getBlock().getPosition()[0];
        int startY = this.getBlock().getPosition()[1];
        int endX = end.getPosition()[0];
        int endY = end.getPosition()[1];
        if (startX == endX && end.getPiece() == null) {
            if (isWhite() && startY + 1 == endY) {
                return true;
            } else if (!isWhite() && startY - 1 == endY) {
                return true;
            } else if (isWhite() && startY == 2 && startY + 2 == endY && Block.findBlock(startX, startY + 1).getPiece() == null) {
                return true;
            } else if (!isWhite() && startY == 7 && startY - 2 == endY && Block.findBlock(startX, startY - 1).getPiece() == null) {
                return true;
            }
        }
        if (end.getPiece() != null && Math.abs(startX - endX) == 1) {
            if (isWhite() && (startY + 1 == endY)) {
                return true;
            } else if (!isWhite() && startY - 1 == endY) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canReach(Block end) {
        int startX = this.getBlock().getPosition()[0];
        int startY = this.getBlock().getPosition()[1];
        int endX = end.getPosition()[0];
        int endY = end.getPosition()[1];
        if (Math.abs(startX - endX) == 1) {
            if (isWhite() && (startY + 1 == endY)) {
                return true;
            } else if (!isWhite() && startY - 1 == endY) {
                return true;
            }
        }
        return false;
    }
}
