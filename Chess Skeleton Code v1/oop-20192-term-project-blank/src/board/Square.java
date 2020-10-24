package board;

import pieces.Piece;

public class Square {

    private Piece currentPiece;

    public Square() {
        this.currentPiece = null;
    } //빈 칸이다.

    public void setCurrentPiece(Piece piece) {
        this.currentPiece = piece;
    } //그 칸에 Piece piece가 들어왔다.

    public Piece getCurrentPiece() {
        return this.currentPiece;
    } //현재 피스를 불러온다.

}
