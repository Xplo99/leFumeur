package sample;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    private final static int NBMATRICES = 300;
    int compteur = 1;
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
    //ScatterChart
    @FXML private LineChart<Number, Number> lineChart;

    @FXML
    public void initialize() {
        XYChart.Series<Number, Number> serieTH = new XYChart.Series<>();
        serieTH.setName("théorique");

        double q = 0.89;
        double p;
        for(int i = 0; i< 6; i++) {
            p = 9*q-8;
            serieTH.getData().add(new XYChart.Data<>(q, p));
            q += 0.02;
        }

        lineChart.getData().add(serieTH);
    }

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

        //Construction du graphe
        //TODO reset le graphe ?

        XYChart.Series<Number, Number> serie = new XYChart.Series<>();

        serie.setName("série " + compteur);
        compteur++;

        for (Matrice m : lesSolutions) {
            serie.getData().add(new XYChart.Data<>(m.getQValue(), m.getPValue()));
        }

        lineChart.getData().add(serie);

    }

    private void clearTextArea(TextArea text) {
        text.clear();
    }



}
