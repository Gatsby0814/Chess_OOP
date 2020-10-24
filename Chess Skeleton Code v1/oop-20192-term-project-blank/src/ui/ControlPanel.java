package ui;

import pieces.Piece;
import util.GameModel;
import util.Move;
import util.MoveLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static util.MoveLogger.*;


public class ControlPanel extends JPanel implements Observer {

    private GameModel gameModel;

    private JButton undoButton;
    private JButton saveButton;
    private JButton loadButton;

    public ControlPanel(GameModel gameModel) {
        this.gameModel = gameModel;
        initialize();
        gameModel.addObserver(this);
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(0, 1));

        undoButton = new JButton("Request Undo");
        undoButton.setEnabled(true);
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                char returnFile = getrecentMoveHistory().getOriginFile();
                int returnRank = getrecentMoveHistory().getOriginRank();
                int currentnRank = getrecentMoveHistory().getDestinationRank();
                char currentFile = getrecentMoveHistory().getDestinationFile();
                Piece currentPiece = getrecentMoveHistory().getPiece();
                Piece currentCapturedPiece = getrecentMoveHistory().getCapturedPiece();

                gameModel.undoMove(currentFile, currentnRank, returnFile, returnRank);
                removeRecentMoveHistory();



            }
        });



        saveButton = new JButton("Save Game");
        saveButton.setEnabled(true);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                for (int r = 7; r >= 0; r --) {
//                    for (int f = 0; f < 8; f++) {
//                        //square상태 저장
//
//                    }
//
//
//                }
                    }
        });


        loadButton = new JButton("Load Game");
        loadButton.setEnabled(true);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                for (int r = 7; r >= 0; r --) {
//                    for (int f = 0; f < 8; f++) {
//                        //square 상태 불러옴
//                    }
//                }
                    }
        });

        this.add(undoButton);
        this.add(saveButton);
        this.add(loadButton);
        this.setPreferredSize(new Dimension(300, 200));
    }

    @Override
    public void update(Observable o, Object arg) {


    }

}
