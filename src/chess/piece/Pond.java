package chess.piece;

import chess.board.Block;

public class Pond extends Piece {

    public static Pond makePond(int x, int y) {
        Pond pond = null;
        if (y <= 2) {
            pond = new Pond("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_plt60.png", true);
        } else {
            pond = new Pond("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_pdt60.png", false);
        }
        return pond;
    }

    public Pond(String url, boolean isWhite) {
        super(url, isWhite);
        setType(PieceType.POND);
    }

    @Override
    public boolean canMove(Block start, Block end) {
        if (this.hasSameColor(end)) {
            return false;
        }
        int startX = start.getPosition()[0];
        int startY = start.getPosition()[1];
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
}
