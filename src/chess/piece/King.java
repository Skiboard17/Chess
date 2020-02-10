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
    public boolean checked(Block pos, Block kingPos) {
        // TODO
        Group opponents = isWhite() ? BlackPieces : WhitePieces;
        Piece excluded = null;
        if (pos.getPiece() != null) {
            excluded = pos.getPiece();
            opponents.getChildren().remove(pos.getPiece());
        }
        for (Node node : opponents.getChildren()) {
            Piece piece = (Piece) node;
            if (piece.canReach(kingPos)) {
                return true;
            }
        }
        if (excluded != null) {
            opponents.getChildren().add(excluded);
        }
        return false;
    }

    public boolean haveMove(){
        // TODO: think about ways of implementing it
        // Might be better if implement go() dodge().
        Group opponents = isWhite() ? BlackPieces : WhitePieces;
        Group allies = isWhite() ? WhitePieces : BlackPieces;
        for (Node node : opponents.getChildren()) {
            Piece piece = (Piece) node;
            if (piece.canReach(this.getPosition()) && !this.dodge())) {
                // all the block between the attacker and the position of the attacker
                if (king.go(allies, piece.getPosition())){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean go(Group allies, Block end){
        for (Node node : allies.getChildren()){
            Piece piece = (Piece) node;
            if (piece.canMove(end)){
                return true;
            }
        }        
        return false;
    }

    private boolean toBlock(){
        
    }

    @Override
    public boolean canMove(Block end) {
        if (this.hasSameColor(end)) {
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
        if (Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1) {
            return true;
        }
        return false;
    }
}
