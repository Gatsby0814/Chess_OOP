package pieces;
import util.Move;
import java.lang.*;

public class Queen extends Piece{
    public Queen(Color color) {
        super(color);
        this.type = Type.QUEEN;
    }

    private int fileMoved(Move move1) {
        return Math.abs((Character.getNumericValue(move1.getDestinationFile()) - Character.getNumericValue(move1.getOriginFile())));
    }
    private int rankMoved(Move move2) {
        return Math.abs(move2.getDestinationRank() - move2.getOriginRank());
    }

    @Override
    public boolean validateMove(Move move) {
        // executeMove or capture
        if ((move.getCapturedPiece() == null)
                || (move.getCapturedPiece() != null
                && !move.getPiece().getColor().equals(move.getCapturedPiece().getColor())))
            return (fileMoved(move) <=1 && rankMoved(move)<=1)
                    ||((move.getDestinationFile() == move.getOriginFile()) && (move.getDestinationRank() != move.getOriginRank()))
                    || ((move.getDestinationFile() != move.getOriginFile()) && (move.getDestinationRank() == move.getOriginRank()))
                    || (fileMoved(move)==rankMoved(move)&&fileMoved(move)!=0&&rankMoved(move)!=0);

        return false;
    }
}