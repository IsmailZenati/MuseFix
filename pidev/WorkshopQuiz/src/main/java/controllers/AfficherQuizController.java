package controllers;

import entities.quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Servicequiz;
import javafx.beans.property.SimpleStringProperty;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.sql.SQLException;

public class AfficherQuizController {

    Servicequiz servicequiz= new Servicequiz();

    @FXML
    private TableColumn<quiz, String> col_dateCreation;

    @FXML
    private TableColumn<quiz, String> col_description;

    @FXML
    private TableColumn<quiz, String> col_difficulte;

    @FXML
    private TableColumn<quiz, Integer> col_idCelebrite;

    @FXML
    private TableColumn<quiz, Integer> col_idQuiz;

    @FXML
    private TableColumn<quiz, String> col_titre;

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

            col_dateCreation.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                property.setValue(dateFormat.format(cellData.getValue().getDateCreation()));
                return property;
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

