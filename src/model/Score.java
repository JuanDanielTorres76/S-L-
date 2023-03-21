package model;

import java.util.Timer;

import java.util.TimerTask;

public class Score {

    private int score;

    private Timer timer;

    private int timeRemaining;

    public Score(int time) {

        this.score = 0;

        this.timeRemaining = time;

    }

    public void startTimer() {

        timer = new Timer();

        TimerTask task = new TimerTask() {

            public void run() {

                if (timeRemaining > 0) {

                    timeRemaining--;

                } else {

                    timer.cancel();

                    System.out.println("Time's up!");

                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000); // Run task every second
    }

    public void stopTimer() {

        timer.cancel();

    }

    public int getScore() {

        return score;

    }

    public void addToScore(int points) {

        score += points;

    }

    public int getTimeRemaining() {

        return timeRemaining;
        
    }
    
}
