package chess.piece;

import chess.board.Block;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;

import static chess.util.Util.*;

public class King extends Piece {
    public static King makeKing(int x, int y) {
        King king = null;
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
        setType(PieceType.KING);
    }

    // check if the king is checked after a piece moves to pos.
    public boolean checked(Block pos) {
        // TODO
        Group opponents = isWhite() ? BlackPieces : WhitePieces;
        Piece excluded = null;
        if (pos.getPiece() != null) {
            excluded = pos.getPiece();
            opponents.getChildren().remove(pos.getPiece());
        }
        for (Node node : opponents.getChildren()) {
            Piece piece = (Piece) node;
            if (piece.canReach(this.getBlock())) {
                return true;
            }
        }
        if (excluded != null) {
            opponents.getChildren().add(excluded);
        }
        return false;
    }

    // check if the king is checked after moving to end
    private boolean moveChecked(Block end) {
        // TODO
        Group opponents = isWhite() ? BlackPieces : WhitePieces;
        for (Node node : opponents.getChildren()) {
            Piece piece = (Piece) node;
            if (piece.canReach(end)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMove(Block end) {
        if (this.hasSameColor(end)) {
            return false;
        }
        return canReach(end);

    }

    @Override
    public boolean canReach(Block end) {
        int startX = this.getBlock().getPosition()[0];
        int startY = this.getBlock().getPosition()[1];
        int endX = end.getPosition()[0];
        int endY = end.getPosition()[1];
        if (Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1 && !moveChecked(end)) {
            return true;
        }
        return false;
    }
}
