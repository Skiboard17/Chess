package chess.piece;

import chess.UI.Block;
import javafx.scene.image.Image;

public class Queen extends Piece {

    public static Queen makeQueen(int x, int y) {
        boolean isWhite = y <= 4;
        String url = isWhite ? "file:img/Chess_qlt60.png" : "file:img/Chess_qdt60.png";
        return new Queen(new Image(url), isWhite, Block.findBlock(x, y));
    }

    public Queen(Image url, boolean isWhite, Block position) {
        super(url, isWhite, position);
    }

    @Override
    public boolean canMove(Block end) {
        return Castle.CastleMoveCheck(this.getBlock(), end) || Bishop.BishopMoveCheck(this.getBlock(), end);
    }

    @Override
    public boolean canReach(Block end) {
        return Castle.CastleReachCheck(this.getBlock(), end) || Bishop.BishopReachCheck(this.getBlock(), end);
    }

}
