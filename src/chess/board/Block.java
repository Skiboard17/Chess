package chess.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import chess.main.Main;
import static chess.main.Main.BLOCK_SIZE;
import static chess.main.Main.Blocks;

public class Block extends Rectangle {
    private boolean isLight;

    public Block(boolean isLight, int x, int y) {
        this.isLight = isLight;
        setX(x);
        setY(y);
        setWidth(BLOCK_SIZE);
        setHeight(BLOCK_SIZE);
        relocate(x * getWidth(), y * getHeight());
        setFill(isLight ? Color.valueOf("#E6CCAB") : Color.valueOf("#9D571B"));
    }

    public int[] getPosition() {
        return new int[]{(int) getX(), (int) getY()};
    }

    public boolean isLight() {
        return isLight;
    }

    public Block findBlock(int x, int y){
        return Blocks[x][y];
    }
}
