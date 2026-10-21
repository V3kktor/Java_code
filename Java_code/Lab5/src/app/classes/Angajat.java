package src.app.classes;

import java.util.Objects;

/**
 * Clasa de baza pentru angajatii companiei.
 */
public abstract class Angajat {
    private static int ID_seq = 1;

    private final int id;
    private String nume;
    private String prenume;
    private double salariu;

    public Angajat(String nume, String prenume, double salariu) {
        this.nume = textValid(nume, "Numele");
        this.prenume = textValid(prenume, "Prenumele");
        setSalariu(salariu);
        this.id = ID_seq;
        ID_seq++;
    }

    protected static String textValid(String valoare, String camp) {
        if (valoare == null || valoare.isBlank())
            throw new IllegalArgumentException(camp + " nu este valid(a)!");
        return valoare.strip();
    }

    /** Functia angajatului (ex: Sofer, Controlor). */
    public abstract String getFunctie();

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = textValid(nume, "Numele");
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = textValid(prenume, "Prenumele");
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        if (salariu <= 0) throw new IllegalArgumentException("Salariul nu este valid!");
        this.salariu = salariu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angajat that = (Angajat) o;
        return nume.equals(that.nume) && prenume.equals(that.prenume)
                && Double.compare(salariu, that.salariu) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, salariu);
    }

    @Override
    public String toString() {
        return "[ID " + id + "] " + getFunctie() + " " + nume + " " + prenume + ", salariu: " + salariu + " RON";
    }
}