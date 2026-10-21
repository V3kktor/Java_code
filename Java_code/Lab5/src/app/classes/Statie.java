package src.app.classes;

import java.util.Objects;

public class Statie {
    private static int ID_seq = 1;

    private final int id;
    private String nume;
    private String adresa;

    public Statie(String nume, String adresa) {
        this.nume = textValid(nume, "Numele statiei");
        this.adresa = textValid(adresa, "Adresa");
        this.id = ID_seq;
        ID_seq++;
    }

    private static String textValid(String valoare, String camp) {
        if (valoare == null || valoare.isBlank())
            throw new IllegalArgumentException(camp + " nu este valid(a)!");
        return valoare.strip();
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = textValid(nume, "Numele statiei");
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = textValid(adresa, "Adresa");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statie that = (Statie) o;
        return nume.equals(that.nume) && adresa.equals(that.adresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, adresa);
    }

    @Override
    public String toString() {
        return "[ID " + id + "] Statia " + nume + " (" + adresa + ")";
    }
}