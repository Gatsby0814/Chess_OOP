package util;

import board.Board;

import pieces.Knight;
import pieces.Piece;


import java.util.List;

import static board.Board.getSquare;

public class MoveValidator {

    private static MoveValidator ourInstance = new MoveValidator();

    public static MoveValidator getInstance() {
        return ourInstance;
    }

    private MoveValidator() {

        if (Core.getPreferences().isBoardReversed()){
            currentMoveColor = Piece.Color.BLACK;
        }
        else {
            currentMoveColor = Piece.Color.WHITE;
        }
    }
    public static Piece.Color currentMoveColor;


    public static boolean validateMove(Move move) {
        return validateMove(move, false);
    }

    public static boolean validateMove(Move move, boolean ignoreColorCheck) {
        // check for out of bounds
        if (move.getDestinationFile() < 'a' || move.getDestinationFile() > 'h'
                || move.getDestinationRank() < 1 || move.getDestinationRank() > 8) {
            return false;
        }
        // check for valid origin
        if (move.getPiece() == null) {
            return false;
        }

        // check for valid color
        if (!move.getPiece().getColor().equals(currentMoveColor) && !ignoreColorCheck) {
            return false;
        }

        // check for valid destination
        if (move.getCapturedPiece() != null) {
            if (move.getPiece().getColor().equals(move.getCapturedPiece().getColor())) {
                return false;
            }
        }

        // check for piece rule
        if (!move.getPiece().validateMove(move)) {
            return false;
        }

        // check for clear path
        if (!validateClearPath(move)) {
            return false;
        } else
            currentMoveColor = currentMoveColor.equals(Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
        return true;
    }

    public static int checkOpponentKingRank(Piece piece) {
        for (char file = 'a'; file < 'i'; file++) {
            for (int j = 1; j < 9; j++) {
                if (piece.getColor() == Piece.Color.WHITE && (getSquare(file, j).getCurrentPiece() != null)) {
                    if (getSquare(file, j).getCurrentPiece().getColor() == Piece.Color.BLACK &&
                            getSquare(file, j).getCurrentPiece().getType() == Piece.Type.KING) {
                        return j;
                    }
                }
                if (piece.getColor() == Piece.Color.BLACK && (getSquare(file, j).getCurrentPiece() != null)) {
                    if (getSquare(file, j).getCurrentPiece().getColor() == Piece.Color.WHITE &&
                            getSquare(file, j).getCurrentPiece().getType() == Piece.Type.KING) {
                        return j;
                    }
                }
            }
        }
        return ' ';
    }

    public static char checkOpponentKingFile(Piece piece) {
        for (char file = 'a'; file < 'i'; file++) {
            for (int j = 1; j < 9; j++) {
                if (piece.getColor() == Piece.Color.WHITE && (getSquare(file, j).getCurrentPiece() != null)) {
                    if (getSquare(file, j).getCurrentPiece().getColor() == Piece.Color.BLACK &&
                            getSquare(file, j).getCurrentPiece().getType() == Piece.Type.KING) {
                        return file;
                    }
                }
                if (piece.getColor() == Piece.Color.BLACK && (getSquare(file, j).getCurrentPiece() != null)) {
                    if (getSquare(file, j).getCurrentPiece().getColor() == Piece.Color.WHITE &&
                            getSquare(file, j).getCurrentPiece().getType() == Piece.Type.KING) {
                        return file;
                    }
                }
            }
        }
        return ' ';
    }

    public static boolean isCheckMove(Move move) {
        // TODO-check
        if ((move.getPiece().getType() != Piece.Type.KING) && validateClearPath(move)) {
            Move CheckMove
                    = new Move(move.getPiece(), move.getDestinationFile(), move.getDestinationRank(), checkOpponentKingFile(move.getPiece()), checkOpponentKingRank(move.getPiece()));
            return (CheckMove.getPiece().validateMove(CheckMove) && validateClearPath(CheckMove));
        }
        if (move.getPiece().getType() == Piece.Type.KING) {
            for (char file = 'a'; file < 'i'; file++) {
                for (int j = 1; j < 9; j++) {
                    if (getSquare(file, j).getCurrentPiece() != null) {
                        if (getSquare(file, j).getCurrentPiece().getColor() != move.getPiece().getColor()) {
                            Move tmp = new Move(getSquare(file, j).getCurrentPiece(), file, j, move.getDestinationFile(), move.getDestinationRank());
                            return (tmp.getPiece().validateMove(tmp) && validateClearPath(tmp));
                        }
                    }
                }
            }
        }
        return false;
    }


    public static boolean isCheckMate(Move move) {
        // TODO-check
        if (isCheckMove(move)) {
            for (char file = 'a'; file < 'i'; file++) {
                for (int j = 1; j < 9; j++) {
                    Move OpponentKingAvailable = new Move(getSquare(checkOpponentKingFile(move.getPiece()), checkOpponentKingRank(move.getPiece())).getCurrentPiece(), checkOpponentKingFile(move.getPiece()), checkOpponentKingRank(move.getPiece()), file, j);
                    if ((OpponentKingAvailable.getPiece().validateMove(OpponentKingAvailable)) && !isCheckMove(OpponentKingAvailable))
                        return false;
                }
            }
        }
        return true;
    }

    private static int fileMoved(Move move1) {
        return (Character.getNumericValue(move1.getDestinationFile()) - Character.getNumericValue(move1.getOriginFile()));
    }

    private static int rankMoved(Move move2) {
        return move2.getDestinationRank() - move2.getOriginRank();
    }

    public static boolean validateClearPath(Move move) {
        // TODO-movement
        if (move.getPiece().getType().equals(Piece.Type.KNIGHT)
                || move.getPiece().getType().equals(Piece.Type.KING)) {
            return true;
        }
        if (move.getPiece().getType().equals(Piece.Type.BISHOP)) {
            for (char file = 'a'; file < 'i'; file++) {
                for (int j = 1; j < 9; j++) {
                    if (Board.getSquare(file, j).getCurrentPiece() != null
                            && !Board.getSquare(file, j).equals(move.getCapturedPiece())
                            && !Board.getSquare(file, j).equals(move.getPiece())) {
//                    if (move.getPiece().getColor().equals(Board.getSquare(file,j).getCurrentPiece().getColor())) {
                        Move checkClearPath = new Move(move.getPiece(), null, move.getOriginFile(), move.getOriginRank(), file, j);
                        if ((checkClearPath.getPiece().validateMove(checkClearPath))
                                && (Math.abs(fileMoved(checkClearPath))) < Math.abs(fileMoved(move))
                                && (Math.abs(rankMoved(checkClearPath))) < Math.abs((rankMoved(move)))
                                && (fileMoved(checkClearPath) * fileMoved(move) > 0)
                                && (rankMoved(checkClearPath) * rankMoved(move) > 0)) {
                            return false;
                        }
                    }
                }
            }
        }
        if (move.getPiece().getType().equals(Piece.Type.QUEEN)) {
            for (char file = 'a'; file < 'i'; file++) {
                for (int j = 1; j < 9; j++) {
                    if (Board.getSquare(file, j).getCurrentPiece() != null
                            && !Board.getSquare(file, j).equals(move.getCapturedPiece())
                            && !Board.getSquare(file, j).equals(move.getPiece())) {
                        Move checkClearPath = new Move(move.getPiece(), null, move.getOriginFile(), move.getOriginRank(), file, j);
                        if (fileMoved(move) <= 1 && rankMoved(move) <= 1) {
                            return true;
                        }
                        if (fileMoved(move) != 0 && rankMoved(move) != 0 & Math.abs(fileMoved(move)) == Math.abs(rankMoved(move))) {
                            if ((checkClearPath.getPiece().validateMove(checkClearPath))
                                    && (Math.abs(fileMoved(checkClearPath))) < Math.abs(fileMoved(move))
                                    && (Math.abs((rankMoved(checkClearPath)))) < Math.abs((rankMoved(move)))
                                    && (fileMoved(checkClearPath) * fileMoved(move) > 0)
                                    && (rankMoved(checkClearPath) * rankMoved(move) > 0)) {
                                return false;
                            }
                        }
                        if (fileMoved(move) == 0) {
                            if (checkClearPath.getPiece().validateMove(checkClearPath)
                                    && ((fileMoved(checkClearPath) == 0 && Math.abs(rankMoved(move)) > Math.abs(rankMoved(checkClearPath)) && rankMoved(move) * rankMoved(checkClearPath) > 0))) {
                                return false;
                            }
                        }
                        if (rankMoved(move) == 0) {
                            if (checkClearPath.getPiece().validateMove(checkClearPath)
                                    && ((rankMoved(checkClearPath) == 0 && Math.abs(fileMoved(move)) > Math.abs(fileMoved(checkClearPath)) && fileMoved(move) * fileMoved(checkClearPath) > 0))) {
                                return false;
                            }
                        }

                    }
                }
            }
        }
        if (move.getPiece().getType().equals(Piece.Type.ROOK)) {
            for (char file = 'a'; file < 'i'; file++) {
                for (int j = 1; j < 9; j++) {
                    if (Board.getSquare(file, j).getCurrentPiece() != null
                            && !Board.getSquare(file, j).equals(move.getCapturedPiece())
                            && !Board.getSquare(file, j).equals(move.getPiece())) {
//                    if (move.getPiece().getColor().equals(Board.getSquare(file,j).getCurrentPiece().getColor())) {
                        Move checkClearPath = new Move(move.getPiece(), null, move.getOriginFile(), move.getOriginRank(), file, j);
                        if ((checkClearPath.getPiece().validateMove(checkClearPath))
                                && (((fileMoved(checkClearPath)) == 0 && Math.abs(rankMoved(move)) > Math.abs(rankMoved(checkClearPath)) && rankMoved(move) * rankMoved(checkClearPath) > 0)
                                || ((rankMoved(checkClearPath)) == 0 && Math.abs(fileMoved(move)) > Math.abs(fileMoved(checkClearPath)) && fileMoved(move) * fileMoved(checkClearPath) > 0))) {
                            return false;
                        }
                    }
                }
            }
        } else if (move.getPiece().getType().equals(Piece.Type.PAWN))
            for (char file = 'a'; file < 'i'; file++) {
                for (int j = 1; j < 9; j++) {
                    if (Board.getSquare(file, j).getCurrentPiece() != null
                            && !Board.getSquare(file, j).equals(move.getCapturedPiece())
                            && !Board.getSquare(file, j).equals(move.getPiece())) {
//                    if (move.getPiece().getColor().equals(Board.getSquare(file,j).getCurrentPiece().getColor())) {
                        Move checkClearPath = new Move(move.getPiece(), null, move.getOriginFile(), move.getOriginRank(), file, j);
                        if ((checkClearPath.getPiece().validateMove(checkClearPath))
                                && (Math.abs(fileMoved(checkClearPath))) <= Math.abs(fileMoved(move))
                                && (Math.abs((rankMoved(checkClearPath)))) <= Math.abs((rankMoved(move)))
                                && (fileMoved(checkClearPath) == 0 && fileMoved(move) == 0)
                                && (rankMoved(checkClearPath) * rankMoved(move) >= 0)) {
                            return false;
                        }
//                    }
//                    else if (!move.getPiece().getColor().equals(Board.getSquare(file,j).getCurrentPiece().getColor())){
//                        Move checkClearPath = new Move(move.getPiece(), move.getOriginFile(), move.getOriginRank(), file, j);
//                        if (!checkClearPath.getCapturedPiece().equals(move.getCapturedPiece())) {
//                            if ((checkClearPath.getPiece().validateMove(checkClearPath))
//                                    && (Math.abs(fileMoved(checkClearPath)) <= Math.abs(fileMoved(move)))
//                                    && (Math.abs(rankMoved(checkClearPath)) <= Math.abs(rankMoved(move)))
//                                    &&(fileMoved(checkClearPath)*fileMoved(move)>=0)
//                                    && (rankMoved(checkClearPath)*rankMoved(move)>=0)) {
//                                return false;
//                            }
//                        }
//                    }
                    }
                }
            }
        return true;
    }
}