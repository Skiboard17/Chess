package chess.piece;

import chess.UI.Block;
import javafx.scene.image.Image;

public class Knight extends Piece {

    public static Knight makeKnight(int x, int y) {
        boolean isWhite = y <= 4;
        String url = isWhite ? "file:img/Chess_nlt60.png" : "file:img/Chess_ndt60.png";
        return new Knight(new Image(url), isWhite, Block.findBlock(x, y));
    }

    public Knight(Image url, boolean isWhite, Block position) {
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
        return Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1 || Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2;
    }

    @Override
    public boolean canReach(Block end) {
        return canMove(end);
    }
}
