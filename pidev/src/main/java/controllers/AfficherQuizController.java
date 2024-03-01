package controllers;

import entities.quiz;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Servicequiz;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AfficherQuizController {

    Servicequiz servicequiz = new Servicequiz();

    @FXML
    private TableColumn<quiz, Integer> col_idQuiz;

    @FXML
    private TableColumn<quiz, String> col_titre;

    @FXML
    private TableColumn<quiz, String> col_description;

    @FXML
    private TableColumn<quiz, String> col_difficulte;

    @FXML
    private TableColumn<quiz, String> col_dateCreation;

    @FXML
    private TableColumn<quiz, Integer> col_idCelebrite;

    @FXML
    private TableView<quiz> tv_quiz;

    @FXML
    void initialize() {
        try {
            ObservableList<quiz> quizzes = FXCollections.observableList(servicequiz.afficher());
            tv_quiz.setItems(quizzes);

            col_idQuiz.setCellValueFactory(new PropertyValueFactory<>("idQuiz"));
            col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_difficulte.setCellValueFactory(new PropertyValueFactory<>("difficulte"));
            col_idCelebrite.setCellValueFactory(new PropertyValueFactory<>("idCelebrite"));

            // Convert Date to String for display
            col_dateCreation.setCellValueFactory(cellData -> {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return new SimpleStringProperty(dateFormat.format(cellData.getValue().getDateCreation()));
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}