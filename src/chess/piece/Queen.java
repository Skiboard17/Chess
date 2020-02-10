package chess.piece;

import chess.board.Block;
import javafx.scene.image.Image;

import static chess.Util.*;

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
    public boolean canMove(Block start, Block end) {
        if (Castle.CastleMoveCheck(start, end) || Bishop.BishopMoveCheck(start, end)) {
            return true;
        }
        return false;
    }

}
