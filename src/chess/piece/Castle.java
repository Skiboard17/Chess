package chess.piece;

import javafx.scene.image.Image;
import chess.board.Block;

public class Castle extends Piece {

    public static Castle makeCastle(int x, int y) {
        Castle castle = null;
        if (y < 2) {
            castle = new Castle(new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_rlt60.png"), true, Block.findBlock(x, y));
        } else {
            castle = new Castle(new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_rdt60.png"), false, Block.findBlock(x, y));
        }
        return castle;
    }

    public Castle(Image url, boolean isWhite, Block position) {
        super(url, isWhite, position);
        setType(PieceType.CASTLE);
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
        return CastleReachCheck(this.getBlock(), end);
    }

    public static boolean CastleMoveCheck(Block start, Block end) {
        if (start.getPiece().hasSameColor(end)) {
            return false;
        }
        return CastleReachCheck(start, end);
    }

    public static boolean CastleReachCheck(Block start, Block end) {
        int startX = start.getPiece().getBlock().getPosition()[0];
        int startY = start.getPiece().getBlock().getPosition()[1];
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
}
