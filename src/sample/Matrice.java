package sample;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Vector;

public class Matrice {

    private final static int NBSIMU = 100;

    Vector<Vector<Double>> matrix;

    /** Constructeurs **/
    public Matrice(TextField piZeroX, TextField piZeroY) {
        this.matrix = new Vector<Vector<Double>>();
        Vector<Double> line0 = new Vector<>();
        line0.add(Double.parseDouble(piZeroX.getText()));
        line0.add(Double.parseDouble(piZeroY.getText()));

        matrix.add(line0);
    }

    public Matrice(Double d0, Double d1) {
        this.matrix = new Vector<Vector<Double>>();
        Vector<Double> line0 = new Vector<>();
        line0.add(d0);
        line0.add(d1);

        matrix.add(line0);
    }
    public Matrice(TextField s11, TextField s12, TextField s21, TextField s22) {
        this.matrix = new Vector<Vector<Double>>();
        Vector<Double> line0 = new Vector<>();
        Vector<Double> line1 = new Vector<>();

        line0.add(Double.parseDouble(s11.getText()));
        line0.add(Double.parseDouble(s12.getText()));

        line1.add(Double.parseDouble(s21.getText()));
        line1.add(Double.parseDouble(s22.getText()));

        matrix.add(line0);
        matrix.add(line1);
    }


    /** Méthodes **/
    public String toString() {
        return this.matrix.toString();
    }

    private static Matrice multiplication(Matrice m1, Matrice m2) {
        Double valueX = m1.matrix.get(0).get(0) * m2.matrix.get(0).get(0) + m1.matrix.get(0).get(1) * m2.matrix.get(1).get(0);
        Double valueY = m1.matrix.get(0).get(0) * m2.matrix.get(0).get(1) + m1.matrix.get(0).get(1) * m2.matrix.get(1).get(1);

        Matrice res = new Matrice(valueX, valueY);
        return res;
    }

    public static ArrayList<Matrice> simuRegimePermanent(Matrice m1, Matrice m2) {
        ArrayList<Matrice> res = new ArrayList<Matrice>();

        Matrice vecteurInitial = m1;

        for (int i = 0; i< NBSIMU; i++) {
            Matrice newVecteur = multiplication(vecteurInitial, m2);
            res.add(newVecteur);
            vecteurInitial = newVecteur;
        }

        return res;
    }
}
