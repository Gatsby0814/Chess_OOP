package util;

import board.Board;
import pieces.Piece;
import ui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class GameModel extends Observable { //게임모델클래스: 프레임, 보드패널 타이머페널, 콘트롤패널, 무브히스토리패널

    private GameFrame gameFrame;
    private BoardPanel boardPanel;
    private TimerPanel timerPanel;
    private ControlPanel controlPanel;
    private MoveHistoryPanel moveHistoryPanel;

    private Timer whiteTimer;
    private Timer blackTimer;

    public GameModel() {
        initialize();
    }

    private void initialize() { //타이머를 시작한다, UI컴포넌트를 시작한다( 보드패널 타이머패널 콘트롤패널 무브히스토리패널 만든다
        initializeTimers();//73 this.lines
        initializeUIComponents();
    }

    public void onMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        onLocalMoveRequest(originFile, originRank, destinationFile, destinationRank); //무브메소드 실행요청
    }

    public void undoMove(char originFile, int originRank, char destinationFile, int destinationRank)
    {
        onLocalundoMoverequest(originFile, originRank,  destinationFile, destinationRank);
    }

    private void onLocalMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        Move move = new Move(originFile, originRank, destinationFile, destinationRank);//무브메소드
        if (MoveValidator.validateMove(move)) { //MoveValidator가 참이라면 무브excute
            executeMove(move);
        } else {
            //MoveValidator가 참이 아니라면 실행X
        }
    }

    private void onLocalundoMoverequest(char originFile, int originRank, char destinationFile, int destinationRank)
    {
        Move undoMove = new Move(originFile, originRank, destinationFile, destinationRank);

        Board.executeMove(undoMove);
        MoveValidator.currentMoveColor = MoveValidator.currentMoveColor.equals(Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
        boardPanel.executeMove(undoMove);
        switchTimer(undoMove);
    }

    private void executeMove(Move move) {
        MoveLogger.addMove(move); //무브 로그에 add무브
        Board.executeMove(move); //보드에 무브실행
        moveHistoryPanel.printMove(move);
        boardPanel.executeMove(move);
        switchTimer(move);
         if (MoveValidator.isCheckMove(move)) {
            if (MoveValidator.isCheckMate(move)) {
                stopTimer();
                gameFrame.showCheckmateDialog();
            } else {
                gameFrame.showCheckDialog();
            }
        }

    }


    public Piece queryPiece(char file, int rank) {
        return Board.getSquare(file, rank).getCurrentPiece();
    }

    private void initializeUIComponents() {
        boardPanel = new BoardPanel(this);
        timerPanel = new TimerPanel(this);
        controlPanel = new ControlPanel(this);
        moveHistoryPanel = new MoveHistoryPanel(this);
        gameFrame = new GameFrame(this);
    }

    private void initializeTimers() {
        whiteTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimerforTimeover();
                timerPanel.whiteTimerTikTok();

            } //e라는 액션이벤트가 일어나면 화이트타이머가 틱톡
        });
        blackTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimerforTimeover();
                timerPanel.blackTimerTikTok();

            }
        });
    }

    private void switchTimer(Move move) {
        if (move.getPiece().getColor()== Piece.Color.WHITE) {
//            if (!blackTimer.isRunning())
//                blackTimer.start();
//                whiteTimer.
//            else{
            whiteTimer.stop();
            blackTimer.start();
        }
        else{
//            if (!whiteTimer.isRunning())
//                whiteTimer.start();
            blackTimer.stop();
            whiteTimer.start();
        }

        /*
        TODO-timer
            start and stop whiteTimer and blackTimer
         */
    }

    private void stopTimer() {
        whiteTimer.stop();
        blackTimer.stop();

        // TODO-timer: stop timers
    }

    private void stopTimerforTimeover(){
        if(timerPanel.getBlackTimeover()==true) {
            gameFrame.showBlackTimeoverDialog();
            stopTimer();
        }
        else if(timerPanel.getWhiteTimeover()==true){
            gameFrame.showWhiteTimeoverDialog();;
            stopTimer();
        }

    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public TimerPanel getTimerPanel() {
        return timerPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public MoveHistoryPanel getMoveHistoryPanel() {
        return moveHistoryPanel;
    }

}