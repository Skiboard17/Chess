package chess.piece;

import chess.UI.Block;
import javafx.scene.image.Image;

public class Castle extends Piece {

    private boolean hasMoved;

    public static Castle makeCastle(int x, int y) {
        boolean isWhite = y <= 4;
        String url = isWhite ? "file:img/Chess_rlt60.png" : "file:img/Chess_rdt60.png";
        return new Castle(new Image(url), isWhite, Block.findBlock(x, y));
    }

    public Castle(Image url, boolean isWhite, Block position) {
        super(url, isWhite, position);
    }

    @Override
    public boolean canMove(Block end) {
        if (this.hasSameColor(end)) {
            return false;
        }
        return this.canReach(end);
    }

    @Override
    public boolean canReach(Block end) {
        return CastleReachCheck(this.getPosition(), end);
    }

    public static boolean CastleMoveCheck(Block start, Block end) {
        if (start.getPiece().hasSameColor(end)) {
            return false;
        }
        return CastleReachCheck(start, end);
    }

    public static boolean CastleReachCheck(Block start, Block end) {
        int startX = start.getPiece().getPosition().getPosition()[0];
        int startY = start.getPiece().getPosition().getPosition()[1];
        int endX = end.getPosition()[0];
        int endY = end.getPosition()[1];
        if (startX == endX || startY == endY) {
            int smallerX = Math.min(startX, endX);
            int biggerX = Math.max(startX, endX);
            int smallerY = Math.min(startY, endY);
            int biggerY = Math.max(startY, endY);
            for (int i = smallerX + 1; i < biggerX; i++) {
                if (Block.findBlock(i, startY).getPiece() != null) {
                    return false;
                }
            }
            for (int i = smallerY + 1; i < biggerY; i++) {
                if (Block.findBlock(startX, i).getPiece() != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setMoved() {
        if (!hasMoved) {
            hasMoved = true;
        }
    }
}
