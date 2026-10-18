package Backend.spital.model;

public class Angajat extends Persoana {
    private String functie; // Medic, Asistent, Infirmier, Manager, Director etc.
    private double salariu;
    private String sectie;

    public Angajat(String nume, String prenume, String cnp, int varsta, String functie, double salariu, String sectie) {
        super(nume, prenume, cnp, varsta); // apel super
        this.functie = functie;
        this.salariu = salariu;
        this.sectie = sectie;
    }

    public String getFunctie() { return functie; }
    public double getSalariu() { return salariu; }
    public String getSectie() { return sectie; }

    @Override
    public String toString() {
        return super.toString() + "," + functie + "," + salariu + "," + sectie;
    }
}