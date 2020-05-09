package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {

    //TextField
    @FXML private TextField PiZeroX;
    @FXML private TextField PiZeroY;
    @FXML private TextField S11;
    @FXML private TextField S12;
    @FXML private TextField S21;
    @FXML private TextField S22;

    //Button
    @FXML
    private Button lancerSimu;

    @FXML
    private void lanceSimu(ActionEvent event) {

        Matrice vecteurInitial = new Matrice(PiZeroX, PiZeroY);
        Matrice transition = new Matrice(S11, S12, S21, S22);

        Matrice resMult = Matrice.multiplication(vecteurInitial, transition);

        resMult.displayMatrix();

    }
}
