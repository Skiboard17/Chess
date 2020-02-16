package chess.piece;

import chess.board.Block;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;

import static chess.util.Util.*;

public class King extends Piece {

    public static King makeKing(int x, int y) {
        King king;
        if (y < 2) {
            king = new King(new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_klt60.png"), true, Block.findBlock(x, y));
            whiteKing = king;
        } else {
            king = new King(new Image("file:/C:/Users/bobby/Desktop/Coding/Chess/img/Chess_kdt60.png"), false, Block.findBlock(x, y));
            blackKing = king;
        }
        return king;
    }

    public King(Image url, boolean isWhite, Block position) {
        super(url, isWhite, position);
    }

    // check if the king is checked at kingPos after a piece moves to pos.
    // pos is used for checking after eating.
    public boolean notChecked(Block pos, Block kingPos) {
        Group opponents = isWhite() ? BlackPieces : WhitePieces;
        if (pos != null && pos.getPiece() != null) {
            ignore(pos.getPiece(), opponents);
        }
        for (Node node : opponents.getChildren()) {
            Piece piece = (Piece) node;
            if (piece.canReach(kingPos)) {
                undoIgnore();
                return false;
            }
        }
        undoIgnore();
        return true;
    }

    public boolean haveMove() {
        Group allies = isWhite() ? WhitePieces : BlackPieces;
        for (Node node1 : allies.getChildren()) {
            Piece piece1 = (Piece) node1;
            for (Node node : Blocks.getChildren()) {
                Block block2 = (Block) node;
                if (piece1.canMove(block2) && ValidMove(piece1.getBlock(), block2)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public boolean canMove(Block end) {
        if (this.hasSameColor(end) || !this.notChecked(end, end)) {
            return false;
        }
        return canReach(end);
    }

    @Override
    // only checks the range of the king (not checking if being checked)
    public boolean canReach(Block end) {
        int startX = this.getBlock().getPosition()[0];
        int startY = this.getBlock().getPosition()[1];
        int endX = end.getPosition()[0];
        int endY = end.getPosition()[1];
        return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1;
    }
}
