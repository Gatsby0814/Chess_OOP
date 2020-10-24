package ui;

import util.GameModel;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.Observable;
import java.util.Observer;

import static util.Core.getPreferences;
import static util.Preferences.TimerMode.COUNTDOWN;
import static util.Preferences.TimerMode.STOPWATCH;
import static util.Preferences.*;

public class TimerPanel extends JPanel implements Observer {

    private GameModel gameModel;
    private Time whiteTime;
    private Time blackTime;
    private boolean WhiteTimeover;
    private boolean BlackTimeover;


    private JPanel displayPanel;
    private JPanel whiteTimerPanel;
    private JPanel whiteTimerDigitsPanel;
    private JLabel whiteTimerDigitsLabel;
    private JPanel whiteTimerStatusPanel;
    private JPanel blackTimerPanel;
    private JPanel blackTimerDigitsPanel;
    private JLabel blackTimerDigitsLabel;
    private JPanel blackTimerStatusPanel;
    private int whiteTimeSpent;
    private int blackTimeSpent;

    public boolean getWhiteTimeover(){
        return WhiteTimeover;
    }
    public boolean getBlackTimeover(){
        return BlackTimeover;
    }

    public Time getBlackTime() {
        return blackTime;
    }
    public Time getWhiteTime(){
        return whiteTime;
    }

    public TimerPanel(GameModel gameModel) {
        super(new BorderLayout()); //JPanel(new ...)
        this.gameModel = gameModel;

        if(getPreferences().getTimerMode().equals(STOPWATCH))
         {
            whiteTime = Time.valueOf("00:00:00");
            blackTime = Time.valueOf("00:00:00");
        }
        else
        {
            int givenTImeminutes = getPreferences().getTimeLimit()%60;
            int givenTImehours = getPreferences().getTimeLimit()/60;
            whiteTime = Time.valueOf(givenTImehours+":"+givenTImeminutes+":00");
            blackTime = Time.valueOf(givenTImehours+":"+givenTImeminutes+":00");
        }

        initialize();
        gameModel.addObserver(this);
    }

    @Override //JPanel에 update? 아니면 observe에?
    public void update(Observable o, Object arg) {

    }

    public void whiteTimerTikTok() {
        if(getPreferences().getTimerMode().equals(STOPWATCH)) {
            whiteTimeSpent++;
            int second = whiteTimeSpent % 60;
            int minute = (whiteTimeSpent / 60) % 60;
            int hour = whiteTimeSpent / 3600;
            if (whiteTimeSpent < 10) {
                whiteTime = Time.valueOf("00" + ":" + "00" + ":" + "0" + second);
            } else if (whiteTimeSpent < 60) {
                whiteTime = Time.valueOf("00" + ":" + "00" + ":" + second);
            } else if (whiteTimeSpent < 600) {
                if (second < 10) {
                    whiteTime = Time.valueOf("00" + ':' + "0" + minute + ':' + "0" + second);
                } else
                    whiteTime = Time.valueOf("00" + ':' + "0" + minute + ':' + second);
            } else if (whiteTimeSpent < 3600) {
                if (second < 10) {
                    whiteTime = Time.valueOf("00" + ':' + minute + ':' + "0" + second);
                } else
                    whiteTime = Time.valueOf("00" + ':' + minute + ':' + second);
            } else if (whiteTimeSpent < 36000) {
                if (minute < 10) {
                    if (second < 10) {
                        whiteTime = Time.valueOf("0" + hour + ":" + "0" + minute + ":" + "0" + second);
                    } else
                        whiteTime = Time.valueOf("0" + hour + ":" + "0" + minute + ":" + second);
                } else {
                    if (second < 10) {
                        whiteTime = Time.valueOf("0" + hour + ":" + minute + ":" + "0" + second);
                    } else
                        whiteTime = Time.valueOf("0" + hour + ":" + minute + ":" + second);
                }
            } else {
                if (minute < 10) {
                    if (second < 10) {
                        whiteTime = Time.valueOf(hour + ":" + "0" + minute + ":" + "0" + second);
                    } else
                        whiteTime = Time.valueOf(hour + ":" + "0" + minute + ":" + second);
                } else {
                    if (second < 10) {
                        whiteTime = Time.valueOf(hour + ":" + minute + ":" + "0" + second);
                    } else
                        whiteTime = Time.valueOf(hour + ":" + minute + ":" + second);
                }
            }
            whiteTimerDigitsLabel.setText(whiteTime.toString());
            whiteTimerStatusPanel.setVisible(true);  //   Show whiteTimerStatusPanel
            blackTimerStatusPanel.setVisible(false);                            //    Blind blackTimerStatusPanel


        /*
        TODO-timer
            whiteTime.start();                                                  Update whiteTime
            update(TimePanel.whiteTime, whiteDigitsLabel);                       Update whiteDigitsLabel
            whiteTimerPanel.add(whiteTimerStatusPanel, BorderLayout.CENTER);    Show whiteTimerStatusPanel
            blackTimerStatusPanel.visible(false);                                Blind blackTimerStatusPanel
         */

        }
        else

        {

             whiteTimeSpent++;

              int leftTimeinseconds =  (getPreferences().getTimeLimit()*60-whiteTimeSpent);
              int leftTimeseconds = leftTimeinseconds%60;
              int leftTimeminutes = (leftTimeinseconds/60)%60;
              int leftTimehours = leftTimeinseconds/3600;

            if(leftTimeinseconds==0)
            {
                WhiteTimeover=true;
            }
              //남은 시간 10시간대 -10분미만/이상 10초미만/이상
              if(leftTimehours<10)
              {
                  if(leftTimeminutes<10)
                  {
                      if(leftTimeseconds<10)
                      {
                          whiteTime = Time.valueOf("0"+leftTimehours+":"+"0"+leftTimeminutes+":0"+leftTimeseconds);
                      }
                      else if (leftTimeseconds>=10)
                      {
                          whiteTime = Time.valueOf("0"+leftTimehours+":"+"0"+leftTimeminutes+":"+leftTimeseconds);
                      }
                  }
                  else if (leftTimeminutes>=10)
                  {
                      if(leftTimeseconds<10)
                      {
                          whiteTime = Time.valueOf("0"+leftTimehours+":"+leftTimeminutes+":0"+leftTimeseconds);
                      }
                      else if(leftTimeseconds>=10)
                      {
                          whiteTime = Time.valueOf("0"+leftTimehours+":"+leftTimeminutes+":"+leftTimeseconds);
                      }
                  }
              }
              else if (leftTimehours>=10)
              {

                  if(leftTimeminutes<10)
                  {
                      if(leftTimeseconds<10)
                      {
                          whiteTime = Time.valueOf(leftTimehours+":"+"0"+leftTimeminutes+":0"+leftTimeseconds);
                      }
                      else if (leftTimeseconds>=10)
                      {
                          whiteTime = Time.valueOf(leftTimehours+":"+"0"+leftTimeminutes+":"+leftTimeseconds);
                      }
                  }
                  else if (leftTimeminutes>=10)
                  {
                      if(leftTimeseconds<10)
                      {
                          whiteTime = Time.valueOf(leftTimehours+":"+leftTimeminutes+":0"+leftTimeseconds);
                      }
                      else if(leftTimeseconds>=10)
                      {
                          whiteTime = Time.valueOf(leftTimehours+":"+leftTimeminutes+":"+leftTimeseconds);
                      }
                  }
              }


                whiteTime.valueOf(leftTimehours+":"+leftTimeminutes+":"+leftTimeseconds);
                whiteTimerDigitsLabel.setText(whiteTime.toString());
                whiteTimerStatusPanel.setVisible(true);  //   Show whiteTimerStatusPanel
                blackTimerStatusPanel.setVisible(false);


            }
    }


    public void blackTimerTikTok() {
        // TODO-timer: same with whiteTimerTikTok
        if(getPreferences().getTimerMode().equals(STOPWATCH))
        {
            blackTimeSpent++;
            int second = blackTimeSpent % 60;
            int minute = (blackTimeSpent / 60) % 60;
            int hour = blackTimeSpent / 3600;
            if (blackTimeSpent < 10) {
                blackTime = Time.valueOf("00" + ":" + "00" + ":" + "0" + second);
            } else if (blackTimeSpent < 60) {
                blackTime = Time.valueOf("00" + ":" + "00" + ":" + second);
            } else if (blackTimeSpent < 600) {
                if (second < 10) {
                    blackTime = Time.valueOf("00" + ":" + "0" + minute + ":" + "0" + second);
                } else
                    blackTime = Time.valueOf("00" + ":" + "0" + minute + ':' + second);
            } else if (blackTimeSpent < 3600) {
                if (second < 10) {
                    blackTime = Time.valueOf("00" + ":" + minute + ':' + "0" + second);
                } else
                    blackTime = Time.valueOf("00" + ":" + minute + ":" + second);
            } else if (blackTimeSpent < 36000) {
                if (minute < 10) {
                    if (second < 10) {
                        blackTime = Time.valueOf("0" + hour + ':' + "0" + minute + ':' + "0" + second);
                    } else
                        blackTime = Time.valueOf("0" + hour + ':' + "0" + minute + ':' + second);
                } else {
                    if (second < 10) {
                        blackTime = Time.valueOf("0" + hour + ':' + minute + ':' + "0" + second);
                    } else
                        blackTime = Time.valueOf("0" + hour + ':' + minute + ':' + second);
                }
            } else {
                if (minute < 10) {
                    if (second < 10) {
                        blackTime = Time.valueOf(hour + ':' + "0" + minute + ':' + "0" + second);
                    } else
                        blackTime = Time.valueOf(hour + ':' + "0" + minute + ':' + second);
                } else {
                    if (second < 10) {
                        blackTime = Time.valueOf(hour + ":" + minute + ":" + "0" + second);
                    } else
                        blackTime = Time.valueOf(hour + ":" + minute + ":" + second);
                }
            }
            blackTimerDigitsLabel.setText(blackTime.toString());
            blackTimerStatusPanel.setVisible(true);  //   Show whiteTimerStatusPanel
            whiteTimerStatusPanel.setVisible(false);                            //    Blind blackTimerStatusPanel
        }
        else

        {
            blackTimeSpent++;

            int leftTimeinseconds =  (getPreferences().getTimeLimit()*60-blackTimeSpent);
            int leftTimeseconds = leftTimeinseconds%60;
            int leftTimeminutes = (leftTimeinseconds/60)%60;
            int leftTimehours = leftTimeinseconds/3600;

            if(leftTimeinseconds==0)
            {
                BlackTimeover=true;
            }

            if(leftTimehours<10)
            {
                if(leftTimeminutes<10)
                {
                    if(leftTimeseconds<10)
                    {
                        blackTime = Time.valueOf("0"+leftTimehours+":"+"0"+leftTimeminutes+":0"+leftTimeseconds);
                    }
                    else if (leftTimeseconds>=10)
                    {
                        blackTime = Time.valueOf("0"+leftTimehours+":"+"0"+leftTimeminutes+":"+leftTimeseconds);
                    }
                }
                else if (leftTimeminutes>=10)
                {
                    if(leftTimeseconds<10)
                    {
                        blackTime = Time.valueOf("0"+leftTimehours+":"+leftTimeminutes+":0"+leftTimeseconds);
                    }
                    else if(leftTimeseconds>=10)
                    {
                        blackTime = Time.valueOf("0"+leftTimehours+":"+leftTimeminutes+":"+leftTimeseconds);
                    }
                }
            }
            else if (leftTimehours>=10)
            {

                if(leftTimeminutes<10)
                {
                    if(leftTimeseconds<10)
                    {
                        blackTime = Time.valueOf(leftTimehours+":"+"0"+leftTimeminutes+":0"+leftTimeseconds);
                    }
                    else if (leftTimeseconds>=10)
                    {
                        blackTime = Time.valueOf(leftTimehours+":"+"0"+leftTimeminutes+":"+leftTimeseconds);
                    }
                }
                else if (leftTimeminutes>=10)
                {
                    if(leftTimeseconds<10)
                    {
                        blackTime = Time.valueOf(leftTimehours+":"+leftTimeminutes+":0"+leftTimeseconds);
                    }
                    else if(leftTimeseconds>=10)
                    {
                        blackTime = Time.valueOf(leftTimehours+":"+leftTimeminutes+":"+leftTimeseconds);
                    }
                }
            }



            blackTimerDigitsLabel.setText(blackTime.toString());
            blackTimerStatusPanel.setVisible(true);  //   Show whiteTimerStatusPanel
            whiteTimerStatusPanel.setVisible(false);
        }

    }

    private void initialize() {
        whiteTimerDigitsLabel = new JLabel(whiteTime.toString());
        whiteTimerDigitsLabel.setFont(whiteTimerDigitsLabel.getFont().deriveFont(48f));
        whiteTimerDigitsPanel = new JPanel();
        whiteTimerDigitsPanel.add(whiteTimerDigitsLabel);
        whiteTimerStatusPanel = new JPanel();
        whiteTimerStatusPanel.setBackground(Color.WHITE);
        whiteTimerPanel = new JPanel(new BorderLayout());
        whiteTimerPanel.add(whiteTimerDigitsPanel, BorderLayout.LINE_START);
        whiteTimerPanel.add(whiteTimerStatusPanel, BorderLayout.CENTER);
        whiteTimerPanel.setBorder(BorderFactory.createTitledBorder("White"));

        blackTimerDigitsLabel = new JLabel(blackTime.toString());
        blackTimerDigitsLabel.setFont(blackTimerDigitsLabel.getFont().deriveFont(48f));
        blackTimerDigitsPanel = new JPanel();
        blackTimerDigitsPanel.add(blackTimerDigitsLabel);
        blackTimerStatusPanel = new JPanel();
        blackTimerStatusPanel.setBackground(Color.BLACK);
        blackTimerPanel = new JPanel(new BorderLayout());
        blackTimerPanel.add(blackTimerDigitsPanel, BorderLayout.LINE_START);
        blackTimerPanel.add(blackTimerStatusPanel, BorderLayout.CENTER);
        blackTimerPanel.setBorder(BorderFactory.createTitledBorder("Black"));

        displayPanel = new JPanel(new GridLayout(2, 1));
        displayPanel.add(whiteTimerPanel);
        displayPanel.add(blackTimerPanel);

        this.add(displayPanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(300, 200));
    }

}

//package ui;
//
//import util.GameModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.sql.Time;
//import java.util.Observable;
//import java.util.Observer;
//
//public class TimerPanel extends JPanel implements Observer {
//
//    private GameModel gameModel;
//    private Time whiteTime;
//    private Time blackTime;
//
//    private JPanel displayPanel;
//    private JPanel whiteTimerPanel;
//    private JPanel whiteTimerDigitsPanel;
//    private JLabel whiteTimerDigitsLabel;
//    private JPanel whiteTimerStatusPanel;
//    private JPanel blackTimerPanel;
//    private JPanel blackTimerDigitsPanel;
//    private JLabel blackTimerDigitsLabel;
//    private JPanel blackTimerStatusPanel;
//
//    public TimerPanel(GameModel gameModel) {
//        super(new BorderLayout()); //JPanel(new ...)
//        this.gameModel = gameModel;
//        whiteTime = Time.valueOf("00:00:00");
//        blackTime = Time.valueOf("00:00:00");
//        initialize();
//        gameModel.addObserver(this);
//        //타임패널은 이제부터 옵저버다. 게임모델이 정보를 줄 것이다.
//        //this(타임패널)를 옵저버로 등록 .
//    }
//
//    @Override //observable로부터 arg 업데이트 땡겨옴?? TimerPanel이 무엇을 받아오는가?
//    public void update(Observable o, Object arg) {
////        if (g instanceof GameModel)
////        {
////            whiteTime =(Time)arg;
////            whiteTimerDigitsLabel = new JLabel(whiteTime.toString());
////        }
//    }
//
//    public void whiteTimerTikTok() {
//
//
//        int startTime = (int)System.currentTimeMillis()/1000;
//        String currentTime;
//        int currentTimeHours = 0;
//        int currentTimeMinutes = 0;
//        int currentTimeSeconds = 0;
//
//        int pastTime = 0;
//        int elapsedTimeHours;
//        int elapsedTimeMinutes;
//        int elapsedTimeSeconds;
//
//        int newTimeHoures;
//        int newTimeMinutes;
//        int newTimeSeconds;
//
//
//// 시간측정
//         pastTime = (int)System.currentTimeMillis()/1000 - startTime;
//
//         // 현재 화이트타임의 시간들을 불러와서, past타임의 경과 초, 분, 시를 더해주고 다시 화이트 타임으로 변환.
//
//            currentTime = whiteTime.toString();
//
//            currentTimeHours = currentTime.charAt(0) + currentTime.charAt(1);  //whiteTime의 시분초를 int로
//            currentTimeMinutes = currentTime.charAt(3) + currentTime.charAt(4);
//            currentTimeSeconds = currentTime.charAt(6) + currentTime.charAt(7);
//
//            //변환한 시분초에 경과 시분초를 각각 더해주기!
//            elapsedTimeHours = pastTime/ 3600;
//            elapsedTimeMinutes = pastTime / 60;
//            elapsedTimeSeconds =  pastTime % 60;
//
//            newTimeHoures =  currentTimeHours + elapsedTimeHours;
//            newTimeMinutes = currentTimeMinutes + elapsedTimeMinutes;
//            newTimeSeconds = currentTimeSeconds + elapsedTimeSeconds;
//
//            //더해준 시분초를 다시 whiteTime으로 생성!
//
//
//        Time newwhitetime = new Time(newTimeHoures, newTimeMinutes, newTimeSeconds);
//
//
//
//
////               실패버전
////               currentTime = startTime - (int)System.currentTimeMillis()/1000;
////                pastTime = pastTime + currentTime;
////                oldTimeHours = pastTime /3600;
////                oldTImeMinutes = pastTime / 60;
////                oldTimeSeconds = pastTime % 60;
////                String stringOldTimeHours = String.valueOf(oldTimeHours);
////                String stringOldTimeMinutes = String.valueOf(oldTImeMinutes);
////                String stringOldTimeSeconds = String.valueOf(oldTimeSeconds);
////                String oldTimer = stringOldTimeHours+":"+stringOldTimeMinutes+ ":"+stringOldTimeSeconds;
////                whiteTime.valueOf(oldTimer); //whitetime 만들었다구!
//
//                update(gameModel, newwhitetime);                   //     Update whiteTime
////                update(gameModel, newwhitetime);                  //     Update whiteDigitsLabel
//                whiteTimerPanel.add(whiteTimerStatusPanel, BorderLayout.CENTER);  //   Show whiteTimerStatusPanel
//                blackTimerStatusPanel.setVisible(false);                            //    Blind blackTimerStatusPanel
//
//
//    }
//
//    public void blackTimerTikTok() {
//
//        int startTime = (int)System.currentTimeMillis()/1000;
//        String currentTime;
//        int currentTimeHours = 0;
//        int currentTimeMinutes = 0;
//        int currentTimeSeconds = 0;
//
//        int pastTime = 0;
//        int elapsedTimeHours;
//        int elapsedTimeMinutes;
//        int elapsedTimeSeconds;
//
//        int newTimeHoures;
//        int newTimeMinutes;
//        int newTimeSeconds;
//
//
//
//// 시간측정
//        pastTime = (int)System.currentTimeMillis()/1000 - startTime;
//
//        // 현재 화이트타임의 시간들을 불러와서, past타임의 경과 초, 분, 시를 더해주고 다시 화이트 타임으로 변환.
//
//        currentTime = blackTime.toString();
//
//        currentTimeHours = currentTime.charAt(0) + currentTime.charAt(1); //whiteTime의 시분초를 int로
//        currentTimeMinutes = currentTime.charAt(3) + currentTime.charAt(4);
//        currentTimeSeconds = currentTime.charAt(6) + currentTime.charAt(7);
//
//        //변환한 시분초에 경과 시분초를 각각 더해주기!
//        elapsedTimeHours = pastTime/ 3600;
//        elapsedTimeMinutes = pastTime / 60;
//        elapsedTimeSeconds =  pastTime % 60;
//
//        newTimeHoures =  currentTimeHours + elapsedTimeHours;
//        newTimeMinutes = currentTimeMinutes + elapsedTimeMinutes;
//        newTimeSeconds = currentTimeSeconds + elapsedTimeSeconds;
//
//        //더해준 시분초를 다시 blackTime으로 생성!
//
//
//
//        blackTime = new Time(newTimeHoures, newTimeMinutes, newTimeSeconds);
//
////
////        int startTime = 0;
////        int currentTime;
////        int oldTimeHours;
////        int oldTImeMinutes;
////        int oldTimeSeconds;
////
////
////
////
////            currentTime = startTime - (int)System.currentTimeMillis()/1000;
////
////            oldTimeHours = currentTime /3600;
////            oldTImeMinutes = currentTime / 60;
////            oldTimeSeconds = currentTime % 60;
////            String stringOldTimeHours = String.valueOf(oldTimeHours);
////            String stringOldTimeMinutes = String.valueOf(oldTImeMinutes);
////            String stringOldTimeSeconds = String.valueOf(oldTimeSeconds);
////            String oldTimer = stringOldTimeHours+":"+stringOldTimeMinutes+ ":"+stringOldTimeSeconds;
////            blackTime.valueOf(oldTimer);
//
//            update(gameModel, blackTime);                              // Update whiteTime
//            update(gameModel, blackTimerDigitsLabel);                  //     Update whiteDigitsLabel
//            blackTimerPanel.add(whiteTimerStatusPanel, BorderLayout.CENTER);  //   Show whiteTimerStatusPanel
//            whiteTimerStatusPanel.setVisible(false);                            //    Blind blackTimerStatusPanel
//
//    }
//
//
//
//    private void initialize() {
//        whiteTimerDigitsLabel = new JLabel(whiteTime.toString());
//        whiteTimerDigitsLabel.setFont(whiteTimerDigitsLabel.getFont().deriveFont(48f));
//        whiteTimerDigitsPanel = new JPanel();
//        whiteTimerDigitsPanel.add(whiteTimerDigitsLabel);
//        whiteTimerStatusPanel = new JPanel();
//        whiteTimerStatusPanel.setBackground(Color.WHITE);
//        whiteTimerPanel = new JPanel(new BorderLayout());
//        whiteTimerPanel.add(whiteTimerDigitsPanel, BorderLayout.LINE_START);
//        whiteTimerPanel.add(whiteTimerStatusPanel, BorderLayout.CENTER);
//        whiteTimerPanel.setBorder(BorderFactory.createTitledBorder("White"));
//
//        blackTimerDigitsLabel = new JLabel(blackTime.toString());
//        blackTimerDigitsLabel.setFont(blackTimerDigitsLabel.getFont().deriveFont(48f));
//        blackTimerDigitsPanel = new JPanel();
//        blackTimerDigitsPanel.add(blackTimerDigitsLabel);
//        blackTimerStatusPanel = new JPanel();
//        blackTimerStatusPanel.setBackground(Color.BLACK);
//        blackTimerPanel = new JPanel(new BorderLayout());
//        blackTimerPanel.add(blackTimerDigitsPanel, BorderLayout.LINE_START);
//        blackTimerPanel.add(blackTimerStatusPanel, BorderLayout.CENTER);
//        blackTimerPanel.setBorder(BorderFactory.createTitledBorder("Black"));
//
//        displayPanel = new JPanel(new GridLayout(2, 1));
//        displayPanel.add(whiteTimerPanel);
//        displayPanel.add(blackTimerPanel);
//
//        this.add(displayPanel, BorderLayout.CENTER);
//        this.setPreferredSize(new Dimension(300, 200));
//    }
//
//}