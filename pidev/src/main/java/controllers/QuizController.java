package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class QuizController {

    @FXML
    public Label question;
    public Label timerLabel;

    @FXML
    public Button opt1, opt2, opt3, opt4;

    static int counter = 0;
    static int correct = 0;
    static int wrong = 0;

    static String timeTaken = "00:00:00";
    private Timeline timer;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    @FXML
    private void initialize() {
        loadQuestions();
        startTimer();
    }

    private void startTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
            }
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }
            timeTaken = String.format("%02d:%02d:%02d", hours, minutes, seconds); // Update timeTaken
            timerLabel.setText(timeTaken);
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void loadQuestions() {

        if (counter == 0) { //Question 1
            question.setText("1. Who is known as the King of Pop?");
            opt1.setText("Madonna");
            opt2.setText("Elvis Presley");
            opt3.setText("Michael Jackson");
            opt4.setText("Prince");
        }
        if (counter == 1) { //Question 2
            question.setText("2. Which musician is famous for the song Bohemian Rhapsody?");
            opt1.setText("Freddie Mercury");
            opt2.setText("David Bowie");
            opt3.setText("Elton John");
            opt4.setText("Mick Jagger");
        }
        if (counter == 2) { //Question 3
            question.setText("3. Who is the lead singer of the band The Beatles?");
            opt1.setText("Paul McCartney");
            opt2.setText("Ringo Starr");
            opt3.setText("George Harrison");
            opt4.setText("John Lennon");
        }
        if (counter == 3) { //Question 4
            question.setText("4. Which musician is known for the album Thriller?");
            opt1.setText("Prince");
            opt2.setText("Michael Jackson");
            opt3.setText("Madonna");
            opt4.setText("Whitney Houston");
        }
        if (counter == 4) {//Question 5
            question.setText("5. Who is often referred to as the Piano Man?");
            opt1.setText("Elton John");
            opt2.setText("Bruce Springsteen");
            opt3.setText("Billy Joel");
            opt4.setText("Paul McCartney");
        }
        if (counter == 5) { //Question 6
            question.setText("6. Which musician is famous for the song Imagine?");
            opt1.setText("Bob Dylan");
            opt2.setText("John Lennon");
            opt3.setText("Paul McCartney");
            opt4.setText("George Harrison");
        }
        if (counter == 6) { //Question 7
            question.setText("7. Who is known for the hit Purple Rain?");
            opt1.setText("Bob Marley");
            opt2.setText("David Bowie");
            opt3.setText("Michael Jackson");
            opt4.setText("Prince");
        }
        if (counter == 7) { //Question 8
            question.setText("8. Which musician is associated with the band The Rolling Stones?");
            opt1.setText("David Bowie");
            opt2.setText("Paul McCartney");
            opt3.setText("Mick Jagger");
            opt4.setText("Freddie Mercury");
        }
        if (counter == 8) { //Question 9
            question.setText("9. Who is considered the Queen of Soul?");
            opt1.setText("Diana Ross");
            opt2.setText("Aretha Franklin");
            opt3.setText("Whitney Houston");
            opt4.setText("Tina Turner");
        }
        if (counter == 9) { //Question 10
            question.setText("10. Which musician is known for the song Hotel California?");
            opt1.setText("Eagles");
            opt2.setText("Eric Clapton");
            opt3.setText("Bruce Springsteen");
            opt4.setText("Bob Dylan");
        }

    }


    @FXML
    public void opt1clicked(ActionEvent event) {
        checkAnswer(opt1.getText().toString());
        if (checkAnswer(opt1.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/result.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }

    }

    boolean checkAnswer(String answer) {

        if (counter == 0) {
            if (answer.equals("Michael Jackson")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 1) {
            if (answer.equals("Freddie Mercury")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 2) {
            if (answer.equals("John Lennon")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 3) {
            if (answer.equals("Michael Jackson")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 4) {
            if (answer.equals("Billy Joel")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 5) {
            if (answer.equals("John Lennon")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 6) {
            if (answer.equals("Prince")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 7) {
            if (answer.equals("Mick Jagger")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 8) {
            if (answer.equals("Aretha Franklin")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 9) {
            if (answer.equals("Eagles")) {
                return true;
            } else {
                return false;
            }
        }
        return false;


    }

    @FXML
    public void opt2clicked(ActionEvent event) {
        checkAnswer(opt2.getText().toString());
        if (checkAnswer(opt2.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/result.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        checkAnswer(opt3.getText().toString());
        if (checkAnswer(opt3.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/result.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        checkAnswer(opt4.getText().toString());
        if (checkAnswer(opt4.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/result.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

}
