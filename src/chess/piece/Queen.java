package chess.piece;

import chess.board.Block;
import javafx.scene.image.Image;

public class Queen extends Piece {

    public static Queen makeQueen(int x, int y) {
        Queen queen = null;
        if (y < 2) {
            queen = new Queen(new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_qlt60.png"), true, Block.findBlock(x, y));
        } else {
            queen = new Queen(new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_qdt60.png"), false, Block.findBlock(x, y));
        }
        return queen;
    }

    public Queen(Image url, boolean isWhite, Block position) {
        super(url, isWhite, position);
        setType(PieceType.QUEEN);
    }

    @Override
    public boolean canMove(Block end) {
        if (Castle.CastleMoveCheck(this.getBlock(), end) || Bishop.BishopMoveCheck(this.getBlock(), end)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canReach(Block end) {
        if (Castle.CastleReachCheck(this.getBlock(), end) || Bishop.BishopReachCheck(this.getBlock(), end)) {
            return true;
        }
        return false;
    }

}
