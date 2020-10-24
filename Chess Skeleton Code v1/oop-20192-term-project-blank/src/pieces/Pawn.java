package pieces;
import util.Core;
import util.Move;
import java.lang.*;

public class Pawn extends Piece {

	public Pawn(Color color) {
		super(color);
		this.type = Type.PAWN;
	}

	private int fileMoved(Move move1) {
		return ((Character.getNumericValue(move1.getDestinationFile()) - Character.getNumericValue(move1.getOriginFile())));
	}
	private int rankMoved(Move move2) {
		return (move2.getDestinationRank() - move2.getOriginRank());
	}

	@Override
	public boolean validateMove(Move move) {
		// executeMove
		if (move.getCapturedPiece() == null) {
			if (Core.getPreferences().isBoardReversed()) {
				if (move.getPiece().getColor() == Color.WHITE) {
					if (move.getOriginRank()==7){
						return (move.getDestinationFile() == move.getOriginFile())
								&& (rankMoved(move) == -1 ||rankMoved(move) == -2);
					}
					return (move.getDestinationFile() == move.getOriginFile())
							&& (rankMoved(move) == -1);
				} else if (move.getPiece().getColor() == Color.BLACK) {
					if (move.getOriginRank()==2){
						return (move.getDestinationFile() == move.getOriginFile())
								&& (rankMoved(move) == 1 ||rankMoved(move)==2);
					}
					return (move.getDestinationFile() == move.getOriginFile())
							&& (rankMoved(move) == 1);
				}
			}
			else if (!Core.getPreferences().isBoardReversed()){
				if (move.getPiece().getColor()==Color.WHITE){
					if (move.getOriginRank()==2){
						return (move.getDestinationFile() == move.getOriginFile())
								&& (rankMoved(move) == 1 ||rankMoved(move) == 2);
					}
					return (move.getDestinationFile() == move.getOriginFile())
							&& (move.getDestinationRank() - move.getOriginRank() == 1);
				}
				else if(move.getPiece().getColor()==Color.BLACK) {
					if (move.getOriginRank() == 7) {
						return (move.getDestinationFile() == move.getOriginFile()
								&& (rankMoved(move) == -1|| rankMoved(move) == -2));
					}
					return (move.getDestinationFile() == move.getOriginFile())
							&& (move.getDestinationRank() - move.getOriginRank() == -1);
				}
			}
		}



		//capture
		if ((move.getCapturedPiece() != null
				&& !move.getPiece().getColor().equals(move.getCapturedPiece().getColor()))) {
			if (Core.getPreferences().isBoardReversed()) {
				if (move.getPiece().getColor() == Color.WHITE) {
					if (move.getOriginRank()==7){
						return (move.getDestinationFile() == move.getOriginFile()
								&& (rankMoved(move) == -1 ||rankMoved(move) == -2))
								||(Math.abs(fileMoved(move))==1&&rankMoved(move)==-1);
					}
					return ((move.getDestinationFile() == move.getOriginFile())
							&& rankMoved(move) == -1)
							|| ((Math.abs(fileMoved(move))==1)&&rankMoved(move)==-1);
				} else if (move.getPiece().getColor() == Color.BLACK) {
					if (move.getOriginRank()==2){
						return (move.getDestinationFile() == move.getOriginFile()
								&& (rankMoved(move) == 1 ||rankMoved(move)==2))
								||(Math.abs(fileMoved(move))==1&&rankMoved(move)==1);
					}
					return (move.getDestinationFile() == move.getOriginFile()
							&& rankMoved(move) == 1)
							||(Math.abs(fileMoved(move))==1&&rankMoved(move)==1);
				}
			}
			else if (!Core.getPreferences().isBoardReversed()){
				if (move.getPiece().getColor()==Color.WHITE){
					if (move.getOriginRank()==2){
						return (move.getDestinationFile() == move.getOriginFile()
								&& (rankMoved(move)== 1||rankMoved(move) == 2))
								||(Math.abs(fileMoved(move))==1&&rankMoved(move)==1);
					}
					return (move.getDestinationFile() == move.getOriginFile()
							&& rankMoved(move) == 1)
							||(Math.abs(fileMoved(move))==1&&rankMoved(move)==1);
				}
				else if(move.getPiece().getColor()==Color.BLACK) {
					if (move.getOriginRank() == 7) {
						return (move.getDestinationFile() == move.getOriginFile()
								&& ( rankMoved(move)== -1||rankMoved(move) == -2))
								||(Math.abs(fileMoved(move))==1&&rankMoved(move)==-1);
					}
					return (move.getDestinationFile() == move.getOriginFile()
							&& rankMoved(move) == -1)
							||(Math.abs(fileMoved(move))==1&&rankMoved(move)==-1);
				}
			}
		}
		return false;

		}


	}
