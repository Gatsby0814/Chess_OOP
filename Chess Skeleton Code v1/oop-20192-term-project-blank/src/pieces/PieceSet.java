package pieces;

import java.util.*;

public class PieceSet {

    private static Map<Piece.Color, Map<Piece.Type, List<Piece>>> pieceSet = null;
    private static Map<Piece.Color, Stack<Piece>> capturedPieceSet;

    private static char whiteKingFile;
    private static int whiteKingRank;
    private static char blackKingFile;
    private static int blackKingRank;

    private static PieceSet pieceSetInstance = new PieceSet();

    public static PieceSet getInstance() {
        return pieceSetInstance;
    }

    private PieceSet() {
        initialize();
    }

    private static void initialize() {
        initializePieceSet();
        initializeCapturedPieceSet();
        initializeKingsCoordinates();
    }

    public static List<Piece> getPieces(Piece.Color color) {
        List<Piece> piecesSameColor = new ArrayList<Piece>();
        for (Map.Entry<Piece.Type, List<Piece>> piecesEntry : pieceSet.get(color).entrySet()) {
            for (Piece piece : piecesEntry.getValue()) {
                piecesSameColor.add(piece);
            }
        }
        return piecesSameColor;
    }

    public static List<Piece> getPieces(Piece.Color color, Piece.Type type) {
        return pieceSet.get(color).get(type);
    }

    public static void addCapturedPiece(Piece piece) {
        piece.setCapture(true);
        capturedPieceSet.get(piece.getColor()).push(piece);
    }

    public static List<Piece> getCapturedPieces(Piece.Color color) {
        return capturedPieceSet.get(color);
    }

    public static char getOpponentKingFile(Piece.Color color) {
        if (color.equals(Piece.Color.WHITE)) {
            return blackKingFile;
        } else {
            return whiteKingFile;
        }
    }

    public static int getOpponentKingRank(Piece.Color color) {
        if (color.equals(Piece.Color.WHITE)) {
            return blackKingRank;
        } else {
            return whiteKingRank;
        }
    }

    private static void initializePieceSet() {
        /*
        TODO-piece
            Initialize pieces and put to pieceSet
            The structure of pieceSet is as following
                pieceSet - collection by color - collection by category - each pieces
            The following code should be good start, but you can fix if you want
         */
        pieceSet = new LinkedHashMap<Piece.Color, Map<Piece.Type, List<Piece>>>();
//rook
        Map<Piece.Type, List<Piece>> whitePieces = new LinkedHashMap<Piece.Type, List<Piece>>();
        Map<Piece.Type, List<Piece>> blackPieces = new LinkedHashMap<Piece.Type, List<Piece>>();

        List<Piece> whiteRooks = new ArrayList<Piece>();
        List<Piece> blackRooks = new ArrayList<Piece>();
        List<Piece> whiteBishops = new ArrayList<Piece>();
        List<Piece> blackBishops = new ArrayList<Piece>();
        List<Piece> whiteKnights = new ArrayList<Piece>();
        List<Piece> blackKnights = new ArrayList<Piece>();
        List<Piece> whitePawns = new ArrayList<Piece>();
        List<Piece> blackPawns = new ArrayList<Piece>();
        List<Piece> whiteKing = new ArrayList<Piece>();
        List<Piece> blackKing = new ArrayList<Piece>();
        List<Piece> whiteQueen = new ArrayList<Piece>();
        List<Piece> blackQueen = new ArrayList<Piece>();


        for (int i = 0; i < 2; i++) {
            whiteRooks.add(new Rook(Piece.Color.WHITE));
            blackRooks.add(new Rook(Piece.Color.BLACK));
            whiteBishops.add(new Bishop(Piece.Color.WHITE));
            blackBishops.add(new Bishop(Piece.Color.BLACK));
            whiteKnights.add(new Knight(Piece.Color.WHITE));
            blackKnights.add(new Knight(Piece.Color.BLACK));
        }
        for (int i = 0; i < 8; i++) {
            whitePawns.add(new Pawn(Piece.Color.WHITE));
            blackPawns.add(new Pawn(Piece.Color.BLACK));
        }
        whiteKing.add(new King(Piece.Color.WHITE));
        blackKing.add(new King(Piece.Color.BLACK));
        whiteQueen.add(new Queen(Piece.Color.WHITE));
        blackQueen.add(new Queen(Piece.Color.BLACK));


        whitePieces.put(Piece.Type.ROOK, whiteRooks);
        blackPieces.put(Piece.Type.ROOK, blackRooks);
        whitePieces.put(Piece.Type.BISHOP, whiteBishops);
        blackPieces.put(Piece.Type.BISHOP, blackBishops);
        whitePieces.put(Piece.Type.KNIGHT, whiteKnights);
        blackPieces.put(Piece.Type.KNIGHT, blackKnights);
        whitePieces.put(Piece.Type.PAWN, whitePawns);
        blackPieces.put(Piece.Type.PAWN, blackPawns);
        whitePieces.put(Piece.Type.KING, whiteKing);
        blackPieces.put(Piece.Type.KING, blackKing);
        whitePieces.put(Piece.Type.QUEEN, whiteQueen);
        blackPieces.put(Piece.Type.QUEEN, blackQueen);


        pieceSet.put(Piece.Color.WHITE, whitePieces);
        pieceSet.put(Piece.Color.BLACK, blackPieces);
    }

    private static void initializeCapturedPieceSet() {
        capturedPieceSet = new LinkedHashMap<Piece.Color, Stack<Piece>>();
        Stack<Piece> whiteCapturedPieces = new Stack<Piece>();
        Stack<Piece> blackCapturedPieces = new Stack<Piece>();
        capturedPieceSet.put(Piece.Color.WHITE, whiteCapturedPieces);
        capturedPieceSet.put(Piece.Color.BLACK, blackCapturedPieces);
    }

    private static void initializeKingsCoordinates() {
        whiteKingFile = blackKingFile = 'e';
        whiteKingRank = 1;
        blackKingRank = 8;
    }

}
//package pieces;
//
//import java.util.*;
//
//public class PieceSet {
//
//    private static Map<Piece.Color, Map<Piece.Type, List<Piece>>> pieceSet = null; //pieceSet, capturedPieceSet은 Map타입?
//    private static Map<Piece.Color, Stack<Piece>> capturedPieceSet;
//
//    private static char whiteKingFile;
//    private static int whiteKingRank;
//    private static char blackKingFile;
//    private static int blackKingRank;
//
//    private static PieceSet pieceSetInstance = new PieceSet();
//
//    public static PieceSet getInstance() {
//        return pieceSetInstance;
//    }
//
//    private PieceSet() {
//        initialize();
//    } //피스셋 메소드
//
//    private static void initialize() {
//        initializePieceSet(); //어디에?
//        initializeCapturedPieceSet();
//        initializeKingsCoordinates();
//    }
//
//    public static List<Piece> getPieces(Piece.Color color) {
//        List<Piece> piecesSameColor = new ArrayList<Piece>();
//        for (Map.Entry<Piece.Type, List<Piece>> piecesEntry : pieceSet.get(color).entrySet()) {
//            for (Piece piece : piecesEntry.getValue()) {
//                piecesSameColor.add(piece);
//            }
//        }
//        return piecesSameColor;
//    }
//
//    public static List<Piece> getPieces(Piece.Color color, Piece.Type type) {
//        return pieceSet.get(color).get(type);
//    }
//
//    public static void addCapturedPiece(Piece piece) {
//        piece.setCapture(true);
//        capturedPieceSet.get(piece.getColor()).push(piece);
//    }
//
//    public static List<Piece> getCapturedPieces(Piece.Color color) {
//        return capturedPieceSet.get(color);
//    }
//
//    public static char getOpponentKingFile(Piece.Color color) {
//        if (color.equals(Piece.Color.WHITE)) {
//            return blackKingFile;
//        } else {
//            return whiteKingFile;
//        }
//    }
//
//    public static int getOpponentKingRank(Piece.Color color) {
//        if (color.equals(Piece.Color.WHITE)) {
//            return blackKingRank;
//        } else {
//            return whiteKingRank;
//        }
//    }
//
//    private static void initializePieceSet() {
//        /*
//        TODO-piece
//            Initialize pieces and put to pieceSet
//            The structure of pieceSet is as following
//                pieceSet - collection by color - collection by category - each pieces
//            The following code should be good start, but you can fix if you want
//         */
//        pieceSet = new LinkedHashMap<Piece.Color, Map<Piece.Type, List<Piece>>>();
//
//        Map<Piece.Type, List<Piece>> whitePieces = new LinkedHashMap<Piece.Type, List<Piece>>();
//        Map<Piece.Type, List<Piece>> blackPieces = new LinkedHashMap<Piece.Type, List<Piece>>();
//
//        List<Piece> whiteRooks = new ArrayList<Piece>();
//        List<Piece> blackRooks = new ArrayList<Piece>();
//        for (int i = 0; i < 2; i++) {
//            whiteRooks.add(new Rook(Piece.Color.WHITE));
//            blackRooks.add(new Rook(Piece.Color.BLACK));
//        }
//        whitePieces.put(Piece.Type.ROOK, whiteRooks);
//        blackPieces.put(Piece.Type.ROOK, blackRooks);
//
//        pieceSet.put(Piece.Color.WHITE, whitePieces);
//        pieceSet.put(Piece.Color.BLACK, blackPieces);
//    }
//
//    private static void initializeCapturedPieceSet() {
//        capturedPieceSet = new LinkedHashMap<Piece.Color, Stack<Piece>>();
//        Stack<Piece> whiteCapturedPieces = new Stack<Piece>();
//        Stack<Piece> blackCapturedPieces = new Stack<Piece>();
//        capturedPieceSet.put(Piece.Color.WHITE, whiteCapturedPieces);
//        capturedPieceSet.put(Piece.Color.BLACK, blackCapturedPieces);
//    }
//
//    private static void initializeKingsCoordinates() {
//        whiteKingFile = blackKingFile = 'e';
//        whiteKingRank = 1;
//        blackKingRank = 8;
//    }
//
//}
