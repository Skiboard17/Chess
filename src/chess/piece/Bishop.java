package chess.piece;

import chess.board.Block;
import javafx.scene.image.Image;

import static chess.Util.*;

public class Bishop extends Piece {

    public static Bishop makeBishop(int x, int y) {
        Bishop bishop = null;
        if (y < 2) {
            Image image = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_blt60.png");
            bishop = new Bishop(image, true, Block.findBlock(x, y));
        } else {
            Image image = new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_bdt60.png");
            bishop = new Bishop(image, false, Block.findBlock(x, y));
        }
        return bishop;
    }

    public Bishop(Image url, boolean isWhite, Block position) {
        super(url, isWhite, position);
        setType(PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Block end) {
        return BishopMoveCheck(this.getBlock(), end);
    }

    @Override
    public boolean canReach(Block end) {
        return BishopReachCheck(this.getBlock(), end);
    }

    public static boolean BishopMoveCheck(Block start, Block end) {
        if (start.getPiece().hasSameColor(end)) {
            return false;
        }
        return BishopReachCheck(start, end);
    }

    public static boolean BishopReachCheck(Block start, Block end) {
        int startX = start.getPosition()[0];
        int startY = start.getPosition()[1];
        int endX = end.getPosition()[0];
        int endY = end.getPosition()[1];
        if (startY != endY && Math.abs((double) (startX - endX) / (startY - endY)) == 1) {
            int smallerX = Math.min(startX, endX);
            int biggerX = Math.max(startX, endX);
            int smallerY = Math.min(startY, endY);
            int biggerY = Math.max(startY, endY);
            // bottom-left to top-right
            if ((double) (endX - startX) / (endY - startY) == 1) {
                for (int i = smallerX + 1, j = smallerY + 1; i < biggerX; i++, j++) {
                    if (Block.findBlock(i, j).getPiece() != null) {
                        return false;
                    }
                }
            } else {
                for (int i = smallerX + 1, j = biggerY - 1; i < biggerX; i++, j--) {
                    if (Block.findBlock(i, j).getPiece() != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
