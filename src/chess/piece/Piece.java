package chess.piece;

import chess.board.Block;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

import static chess.board.Block.findBlock;
import static chess.main.Main.BLOCK_SIZE;

public abstract class Piece extends StackPane {
    private PieceType type;
    private final boolean isWhite;
    private Block position;

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public Piece(int x, int y) {
        setPrefSize(BLOCK_SIZE, BLOCK_SIZE);
        setAlignment(Pos.CENTER);
        relocate(x * BLOCK_SIZE, y * BLOCK_SIZE);
        isWhite = y > 2;
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
