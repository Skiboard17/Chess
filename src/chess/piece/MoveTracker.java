package chess.piece;

public interface MoveTracker{
    public boolean hasMoved;

    public boolean hasMoved(){
        return hasMoved;
    }

    public void setMoved(){
        if (!hasMoved){
            hasMoved = true;
        }
    }
}