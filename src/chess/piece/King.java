package chess.piece;

import chess.board.Block;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;

import static chess.util.Util.*;

public class King extends Piece {
    public boolean checked;

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
        // TODO: think about ways of implementing it
        Group opponents = isWhite() ? BlackPieces : WhitePieces;
        Group allies = isWhite() ? WhitePieces : BlackPieces;
//        for (Node node : opponents.getChildren()) {
//            Piece piece = (Piece) node;
//            if (piece.canReach(this.getBlock()) && !this.dodge()) {
//                // all the block between the attacker and the position of the attacker
//                // TODO: fix the exclusion of the king
//                if (this.canGo(allies, piece.getBlock())) {
//                    return true;
//                }
////                switch (piece.getType()) {
////                    case QUEEN:
////                    case BISHOP:
////                    case CASTLE:
////                        int startX = piece.getBlock().getPosition()[0];
////                        int startY = piece.getBlock().getPosition()[1];
////                        int endX = this.getBlock().getPosition()[0];
////                        int endY = this.getBlock().getPosition()[1];
////                        int smallerX = Math.min(startX, endX);
////                        int biggerX = Math.max(startX, endX);
////                        int smallerY = Math.min(startY, endY);
////                        int biggerY = Math.max(startY, endY);
////                        // bishop-style attack
////                        if (startY != endY && Math.abs((double) (startX - endX) / (startY - endY)) == 1) {
////                            // bottom-left to top-right
////                            if ((double) (endX - startX) / (endY - startY) == 1) {
////                                for (int i = smallerX + 1, j = smallerY + 1; i < biggerX; i++, j++) {
////                                    if (canGo(allies, Block.findBlock(i, j))) {
////                                        return true;
////                                    }
////                                }
////                            } else {
////                                for (int i = smallerX + 1, j = biggerY - 1; i < biggerX; i++, j--) {
////                                    if (canGo(allies, Block.findBlock(i, j))) {
////                                        return true;
////                                    }
////                                }
////                            }
////                        } else if (startX == endX || startY == endY) {
////                            for (int i = smallerX + 1; i < biggerX; i++) {
////                                if (Block.findBlock(i, startY).getPiece() != null) {
////                                    return true;
////                                }
////                            }
////                            for (int i = smallerY + 1; i < biggerY; i++) {
////                                if (Block.findBlock(startX, i).getPiece() != null) {
////                                    return true;
////                                }
////                            }
////                            return false;
////                        }
////                }
//                return false;
//            }
//        }
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

    private boolean dodge() {
        return false;
    }

    // check if any ally member can go to the block end.
    private boolean canGo(Group allies, Block end) {
        for (Node node : allies.getChildren()) {
            Piece piece = (Piece) node;
            // TODO:fix this
            if (piece.canMove(end)) {
                return true;
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
        if (Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1) {
            return true;
        }
        return false;
    }
}
