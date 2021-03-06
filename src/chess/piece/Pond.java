package chess.piece;

import chess.UI.Block;
import chess.gameplay.Game;
import javafx.scene.image.Image;

public class Pond extends Piece {

    public static Pond makePond(int x, int y) {
        boolean isWhite = y <= 4;
        String url = isWhite ? "file:img/Chess_plt60.png" : "file:img/Chess_pdt60.png";
        return new Pond(new Image(url), isWhite, Block.findBlock(x, y));
    }

    public Pond(Image url, boolean isWhite, Block position) {
        super(url, isWhite, position);
    }

    @Override
    public boolean canMove(Block end) {
        if (this.hasSameColor(end)) {
            return false;
        }
        int startX = this.getPosition().getPosition()[0];
        int startY = this.getPosition().getPosition()[1];
        int endX = end.getPosition()[0];
        int endY = end.getPosition()[1];
        // regular forward movement
        if (startX == endX && end.getPiece() == null) {
            if (isWhite() && startY + 1 == endY) {
                return true;
            } else if (!isWhite() && startY - 1 == endY) {
                return true;
                // First move
            } else if (isWhite() && startY == 2 && startY + 2 == endY && Block.findBlock(startX, startY + 1).getPiece() == null) {
                return true;
            } else if (!isWhite() && startY == 7 && startY - 2 == endY && Block.findBlock(startX, startY - 1).getPiece() == null) {
                return true;
            }
        }
        // Eating diagonally
        if (end.getPiece() != null && Math.abs(startX - endX) == 1) {
            if (isWhite() && (startY + 1 == endY)) {
                return true;
            } else if (!isWhite() && startY - 1 == endY) {
                return true;
            }
        }
        // En passant check
        if (Game.lastMove != null) {
            Piece piece = Game.lastMove[1].getPiece();
            if (piece instanceof Pond) {
                int oppositeStartingLine = piece.isWhite() ? 2 : 7;
                int oppositeEndingLine = piece.isWhite() ? 4 : 5;
                int offset = piece.isWhite() ? -1 : 1;
                // check the lines
                if (Game.lastMove[0].getPosition()[1] == oppositeStartingLine && Game.lastMove[1].getPosition()[1] == oppositeEndingLine) {
                    return startY == oppositeEndingLine && endX == Game.lastMove[0].getPosition()[0] && startY + offset == endY;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canReach(Block end) {
        int startX = this.getPosition().getPosition()[0];
        int startY = this.getPosition().getPosition()[1];
        int endX = end.getPosition()[0];
        int endY = end.getPosition()[1];
        if (Math.abs(startX - endX) == 1) {
            return isWhite() && (startY + 1 == endY) || !isWhite() && startY - 1 == endY;
        }
        return false;
    }
}
