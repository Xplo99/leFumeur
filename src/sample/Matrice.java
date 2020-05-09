package sample;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Matrice {

    private final static int NBSIMU = 100;
    private final static Double OBJECTIF = 0.1;

    private Vector<Vector<Double>> matrix;

    /** Constructeurs **/
    public Matrice(TextField piZeroX, TextField piZeroY) {
        this.matrix = new Vector<>();
        Vector<Double> line0 = new Vector<>();
        line0.add(Double.parseDouble(piZeroX.getText()));
        line0.add(Double.parseDouble(piZeroY.getText()));

        matrix.add(line0);
    }

    public Matrice(Double d0, Double d1) {
        this.matrix = new Vector<>();
        Vector<Double> line0 = new Vector<>();
        line0.add(d0);
        line0.add(d1);

        matrix.add(line0);
    }

    //p élément en position 0,0
    public double getPValue() {
        return this.matrix.get(0).get(0);
    }

    //p élément en position 1,1
    public double getQValue() {
        return this.matrix.get(1).get(1);
    }

    public Matrice(TextField s11, TextField s12, TextField s21, TextField s22) {
        this.matrix = new Vector<>();
        Vector<Double> line0 = new Vector<>();
        Vector<Double> line1 = new Vector<>();

        line0.add(Double.parseDouble(s11.getText()));
        line0.add(Double.parseDouble(s12.getText()));

        line1.add(Double.parseDouble(s21.getText()));
        line1.add(Double.parseDouble(s22.getText()));

        matrix.add(line0);
        matrix.add(line1);
    }

    public Matrice(Double s11, Double s12, Double s21, Double s22) {
        this.matrix = new Vector<>();
        Vector<Double> line0 = new Vector<>();
        Vector<Double> line1 = new Vector<>();

        line0.add(s11);
        line0.add(s12);

        line1.add(s21);
        line1.add(s22);

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

        return new Matrice(valueX, valueY);
    }

    public static ArrayList<Matrice> simuRegimePermanent(Matrice m1, Matrice m2) {
        ArrayList<Matrice> res = new ArrayList<>();

        Matrice vecteurInitial = m1;

        for (int i = 0; i< NBSIMU; i++) {
            Matrice newVecteur = multiplication(vecteurInitial, m2);
            res.add(newVecteur);
            vecteurInitial = newVecteur;
        }

        return res;
    }

    public static boolean isObjectifAtteint(ArrayList<Matrice> lesMatrices) {
        int coefficient = 100;
        double lastValue = lesMatrices.get(lesMatrices.size()-1).matrix.get(0).get(0) * coefficient;
        return Math.round(lastValue) <= (OBJECTIF * coefficient);
    }

    public static ArrayList<Matrice> genRandomMatrice(int nbToGen) {
        ArrayList<Matrice> resultat = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i< nbToGen; i++) {
            double p = rand.nextDouble();
            double q = rand.nextDouble();
            resultat.add(new Matrice(p, 1-p, 1-q, q));
        }

        return resultat;
    }

    public static ArrayList<Matrice> rechercheSolution(Matrice vecteurInitial, ArrayList<Matrice> lesMatrices) {
        ArrayList<Matrice> resultat = new ArrayList<>();

        for(Matrice m : lesMatrices) {
            ArrayList<Matrice> regimePermanent = simuRegimePermanent(vecteurInitial, m);

            if (isObjectifAtteint(regimePermanent)) {
                resultat.add(m);
            }
        }

        return resultat;
    }
}
