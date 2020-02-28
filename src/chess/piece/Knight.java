package chess.piece;

import chess.UI.Block;
import javafx.scene.image.Image;

public class Knight extends Piece {

    public static Knight makeKnight(int x, int y) {
        Knight knight;
        if (y <= 4) {
            knight = new Knight(new Image("file:img/Chess_nlt60.png"), true, Block.findBlock(x, y));
        } else {
            knight = new Knight(new Image("file:img/Chess_ndt60.png"), false, Block.findBlock(x, y));
        }
        return knight;
    }

    public Knight(Image url, boolean isWhite, Block position) {
        super(url, isWhite, position);
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
        return Math.abs(startX - endX) == 2 && Math.abs(startY - endY) == 1 || Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 2;
    }

    @Override
    public boolean canReach(Block end) {
        return canMove(end);
    }
}
