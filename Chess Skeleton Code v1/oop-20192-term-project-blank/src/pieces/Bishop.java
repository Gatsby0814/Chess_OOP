package pieces;

import util.Move;
import java.lang.*;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
        this.type = Type.BISHOP;
    }

    private int fileMoved(Move move1) {
        return Math.abs((Character.getNumericValue(move1.getDestinationFile()) - Character.getNumericValue(move1.getOriginFile())));
    }

    private int rankMoved(Move move2) {
        return Math.abs(move2.getDestinationRank() - move2.getOriginRank());
    }

    @Override
    public boolean validateMove(Move move) {
        if ((move.getCapturedPiece() == null)
                || (move.getCapturedPiece() != null
                && !move.getPiece().getColor().equals(move.getCapturedPiece().getColor()))) {
            if (fileMoved(move) == rankMoved(move)&&fileMoved(move)!=0&&rankMoved(move)!=0) {
                return true;
            }
        }
        return false;
    }
}