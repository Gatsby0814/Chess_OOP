package board;

import pieces.*;
import util.Core;
import util.Move;

import java.util.Iterator;

public class Board {

    public static final int DIMENSION = 8;

    private static Square[][] grid = new Square[DIMENSION][DIMENSION];

    private static Board boardInstance = new Board();

    public static Board getInstance() {
        return boardInstance;
    }

    private Board() {
        initialize();
    }

    public static void initialize() {
        initializeSquares();
        initializePieces();
    }

    public static Square getSquare(char file, int rank) {
        file = Character.toLowerCase(file);
        if (file < 'a' || file > 'h' || rank < 1 || rank > 8) {
            return null;
        } else {
            return grid[file - 'a'][rank - 1];
        }
    }

    public static void executeMove(Move move) {
        Square originSquare = getSquare(move.getOriginFile(), move.getOriginRank());
        Square destinationSquare = getSquare(move.getDestinationFile(), move.getDestinationRank());
        if (destinationSquare.getCurrentPiece() != null) {
            destinationSquare.getCurrentPiece().setCapture(true);
            PieceSet.addCapturedPiece(destinationSquare.getCurrentPiece());
        }
        destinationSquare.setCurrentPiece(originSquare.getCurrentPiece());
        originSquare.setCurrentPiece(null);
    }

    private static void initializeSquares() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                grid[i][j] = new Square();
            }
        }
    }

    private static void initializePieces() {
        /*
        TODO-piece
            Initialize pieces on board
            Check following code to implement other pieces
            Highly recommended to use same template!
         */
        // rooks
        Iterator<Piece> whiteRooksIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.ROOK).iterator();
        Iterator<Piece> blackRooksIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.ROOK).iterator();
        getSquare('a', 1).setCurrentPiece(whiteRooksIterator.next());
        getSquare('h', 1).setCurrentPiece(whiteRooksIterator.next());
        getSquare('a', 8).setCurrentPiece(blackRooksIterator.next());
        getSquare('h', 8).setCurrentPiece(blackRooksIterator.next());

        Iterator<Piece> whiteBishopsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.BISHOP).iterator();
        Iterator<Piece> blackBishopsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.BISHOP).iterator();
        getSquare('c', 1).setCurrentPiece(whiteBishopsIterator.next());
        getSquare('f', 1).setCurrentPiece(whiteBishopsIterator.next());
        getSquare('c', 8).setCurrentPiece(blackBishopsIterator.next());
        getSquare('f', 8).setCurrentPiece(blackBishopsIterator.next());

        Iterator<Piece> whiteKnightsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.KNIGHT).iterator();
        Iterator<Piece> blackKnightsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.KNIGHT).iterator();
        getSquare('b', 1).setCurrentPiece(whiteKnightsIterator.next());
        getSquare('g', 1).setCurrentPiece(whiteKnightsIterator.next());
        getSquare('b', 8).setCurrentPiece(blackKnightsIterator.next());
        getSquare('g', 8).setCurrentPiece(blackKnightsIterator.next());

        Iterator<Piece> whitePawnsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.PAWN).iterator();
        Iterator<Piece> blackPawnsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.PAWN).iterator();
        getSquare('a', 2).setCurrentPiece(whitePawnsIterator.next());
        getSquare('b', 2).setCurrentPiece(whitePawnsIterator.next());
        getSquare('c', 2).setCurrentPiece(whitePawnsIterator.next());
        getSquare('d', 2).setCurrentPiece(whitePawnsIterator.next());
        getSquare('e', 2).setCurrentPiece(whitePawnsIterator.next());
        getSquare('f', 2).setCurrentPiece(whitePawnsIterator.next());
        getSquare('g', 2).setCurrentPiece(whitePawnsIterator.next());
        getSquare('h', 2).setCurrentPiece(whitePawnsIterator.next());
        getSquare('b', 7).setCurrentPiece(blackPawnsIterator.next());
        getSquare('c', 7).setCurrentPiece(blackPawnsIterator.next());
        getSquare('d', 7).setCurrentPiece(blackPawnsIterator.next());
        getSquare('e', 7).setCurrentPiece(blackPawnsIterator.next());
        getSquare('f', 7).setCurrentPiece(blackPawnsIterator.next());
        getSquare('g', 7).setCurrentPiece(blackPawnsIterator.next());
        getSquare('h', 7).setCurrentPiece(blackPawnsIterator.next());

        Iterator<Piece> whiteKingIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.KING).iterator();
        Iterator<Piece> blackKingIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.KING).iterator();
        getSquare('e', 1).setCurrentPiece(whiteKingIterator.next());
        getSquare('e', 8).setCurrentPiece(blackKingIterator.next());

        Iterator<Piece> whiteQueenIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.QUEEN).iterator();
        Iterator<Piece> blackQueenIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.QUEEN).iterator();
        getSquare('d', 1).setCurrentPiece(whiteQueenIterator.next());
        getSquare('d', 8).setCurrentPiece(blackQueenIterator.next());
    }
}
//package board;
//
//import pieces.*;
//import util.Core;
//import util.Move;
//
//import java.util.Iterator;
//
//public class Board {
//
//    public static final int DIMENSION = 8;
//
//    private static Square[][] grid = new Square[DIMENSION][DIMENSION]; //8*8 스퀘어보드 생성
//
//    private static Board boardInstance = new Board(); //보드 이니셜라이즈
//
//    public static Board getInstance() {
//        return boardInstance;
//    }
//
//    private Board() {
//        initialize();
//    }
//
//    public static void initialize() {
//        initializeSquares(); //스퀘어시작
//        initializePieces();//피스 시작
//    }
//
//    public static Square getSquare(char file, int rank) { //그리드로 변환된 스퀘어 현재 데이터를 불러온다.
//        file = Character.toLowerCase(file); //소문자로 변형
//        if (file < 'a' || file > 'h' || rank < 1 || rank > 8) {
//            return null;
//        } else {
//            return grid[file - 'a'][rank - 1]; //좌표반영(0부터 시작이므로, -1)
//        }
//    }
//
//    public static void executeMove(Move move) {  //
//        Square originSquare = getSquare(move.getOriginFile(), move.getOriginRank());
//        Square destinationSquare = getSquare(move.getDestinationFile(), move.getDestinationRank());
//        if (destinationSquare.getCurrentPiece() != null) { //destinationSquare에 피스가 있다면, Capture 실행.
//            destinationSquare.getCurrentPiece().setCapture(true);
//            PieceSet.addCapturedPiece(destinationSquare.getCurrentPiece());
//        }
//        destinationSquare.setCurrentPiece(originSquare.getCurrentPiece());
//        originSquare.setCurrentPiece(null);
//    }
//
//    private static void initializeSquares() {
//        for (int i = 0; i < DIMENSION; i++) { //i 0~7까지 총 8개
//            for (int j = 0; j < DIMENSION; j++) {
//                grid[i][j] = new Square();
//            }
//        }
//    }
//
//    private static void initializePieces() { //피스를 시작한다.
//        /*
//        TODO-piece
//            Initialize pieces on board
//            Check following code to implement other pieces
//            Highly recommended to use same template!
//         */
//        // rooks
//        Iterator<Piece> whiteRooksIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.ROOK).iterator();
//        Iterator<Piece> blackRooksIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.ROOK).iterator();
//        getSquare('a', 1).setCurrentPiece(whiteRooksIterator.next());
//        getSquare('h', 1).setCurrentPiece(whiteRooksIterator.next());
//        getSquare('a', 8).setCurrentPiece(blackRooksIterator.next());
//        getSquare('h', 8).setCurrentPiece(blackRooksIterator.next());
//    }
//}
