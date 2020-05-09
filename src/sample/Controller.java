package sample;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    private final static int NBMATRICES = 300;
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

    @FXML
    private void lanceSimu(ActionEvent event) {
        clearTextArea(text1);

        Matrice vecteurInitial = new Matrice(PiZeroX, PiZeroY);
        Matrice transition = new Matrice(S11, S12, S21, S22);

        //Matrice resMult = Matrice.multiplication(vecteurInitial, transition);

        ArrayList<Matrice> simulation = Matrice.simuRegimePermanent(vecteurInitial, transition);
        boolean objectifAtteint = Matrice.isObjectifAtteint(simulation);

        for (int i = 0; i < simulation.size(); i++) {
            text1.appendText("PI(" + (i+1) + ") = "+ simulation.get(i) + "\n");
        }

        text1.appendText("\n");
        if (objectifAtteint) {
            text1.appendText("L'objectif de fumer maximum 1 jour sur 10 SERA atteint !");
        } else {
            text1.appendText("L'objectif de fumer maximum 1 jour sur 10 NE sera PAS atteint !");
        }

    }

    @FXML
    private void lanceRecherche(ActionEvent event) {
        clearTextArea(text1);

        Matrice vecteurInitial = new Matrice(PiZeroX, PiZeroY);

        ArrayList<Matrice> les300MatriceGen = Matrice.genRandomMatrice(NBMATRICES);

        for(int i= 0; i< NBMATRICES; i++) {
            text1.appendText("M"+ (i+1) +" = "+les300MatriceGen.get(i) + "\n");
        }

        text1.appendText("\n");
        text1.appendText(NBMATRICES + " matrices générées \n");
        text1.appendText("\n");

        ArrayList<Matrice> lesSolutions = Matrice.rechercheSolution(vecteurInitial , les300MatriceGen);

        for(int i = 0; i< lesSolutions.size(); i++) {
            text1.appendText("M"+ (i+1) +" = "+ lesSolutions.get(i) + "\n");
        }

        text1.appendText("\n");
        text1.appendText(lesSolutions.size() + " matrices sont solutions \n");
        text1.appendText("\n");
    }

    private void clearTextArea(TextArea text) {
        text.clear();
    }



}
