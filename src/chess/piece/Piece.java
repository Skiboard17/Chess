package chess.piece;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

import static chess.main.Main.BLOCK_SIZE;

public abstract class Piece extends StackPane {
    private PieceType type;
    private boolean isWhite;

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
}
