package pieces;
import util.Move;
import java.lang.*;

public class King extends Piece {

    public King(Color color) {
        super(color);
        this.type = Type.KING;
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
            return fileMoved(move) <=1 && rankMoved(move)<=1 ;
        return false;
    }
}

//package pieces;
//
//import util.Move;
//
//public class King extends Piece{
//    public King(Color color) {
//        super(color);
//    }
//
//    @Override
//    public boolean validateMove(Move move) {
//        return false;
//    }
//}
