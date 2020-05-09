package sample;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    //TextField
    @FXML private TextField PiZeroX;
    @FXML private TextField PiZeroY;
    @FXML private TextField S11;
    @FXML private TextField S12;
    @FXML private TextField S21;
    @FXML private TextField S22;

    //Button
    @FXML private Button lancerSimu;
    @FXML private Button lancerRecherche;

    //TextArea
    @FXML private TextArea text1;
    @FXML private TextArea text2;

    @FXML
    private void lanceSimu(ActionEvent event) {
        clearTextArea(text1);

        Matrice vecteurInitial = new Matrice(PiZeroX, PiZeroY);
        Matrice transition = new Matrice(S11, S12, S21, S22);

        //Matrice resMult = Matrice.multiplication(vecteurInitial, transition);

        ArrayList<Matrice> simulation = Matrice.simuRegimePermanent(vecteurInitial, transition);

        for (int i = 0; i < simulation.size(); i++) {
            text1.appendText("PI(" + (i+1) + ") = "+ simulation.get(i) + "\n");
        }
    }

    @FXML
    private void lanceRecherche(ActionEvent event) {
        clearTextArea(text2);





    }

    private void clearTextArea(TextArea text) {
        text.clear();
    }
}
