package pieces;
import util.Move;
import java.lang.*;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
        this.type = Type.KNIGHT;
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
                && !move.getPiece().getColor().equals(move.getCapturedPiece().getColor()))) {

            return (fileMoved(move) * rankMoved(move) == 2);
        }
        return false;
    }
}
//package pieces;
//
//import util.Move;
//
//public class Knight extends Piece {
//    public Knight(Color color) {
//        super(color);
//    }
//
//    @Override
//    public boolean validateMove(Move move) {
//        return false;
//    }
//}
