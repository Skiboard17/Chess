package chess.piece;

import chess.board.Block;
import javafx.scene.image.ImageView;

public abstract class Piece extends ImageView {
    private PieceType type;
    private final boolean isWhite;

    public Piece(String url, boolean isWhite) {
        super(url);
        this.isWhite = isWhite;
    }

    public static void movePiece(Block start, Block end) {
        Piece piece = start.getPiece();
        if (piece != null && piece.canMove(start, end)) {
            start.setPiece(null);
            end.setPiece(piece);
            start.restoreColor();
            end.restoreColor();
        }
        start.deselect();
        end.deselect();
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean canMove(Block start, Block end);

    // check if the intended move has the same color
    public boolean hasSameColor(Block end) {
        if (end.getPiece() == null) {
            return false;
        }
        return end.getPiece().isWhite() == this.isWhite();
    }
}
