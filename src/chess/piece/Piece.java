package chess.piece;

import chess.board.Block;
import javafx.scene.image.ImageView;

import static chess.board.Block.findBlock;

public abstract class Piece extends ImageView {
    private PieceType type;
    private boolean isWhite;
    private Block position;

    public Piece(String url) {
        super(url);
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public boolean isWhite() {
        return isWhite;
    }

//    public abstract boolean move(); TODO

    // check if the intended move has the same color
    public boolean hasSameColor(int x, int y) {
        Block destination = findBlock(x, y);
        return destination.getPiece().isWhite() == this.isWhite();
    }
}
