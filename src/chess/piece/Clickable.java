package chess.piece;

public interface Clickable {

    // return value depends on whether it passes the block/piece to Game.trackMove()
    boolean click();
}
