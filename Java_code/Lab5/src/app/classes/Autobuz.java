package src.app.classes;

import java.util.Objects;
import src.app.classes.Sofer;

public class Autobuz {
    private static int ID_seq = 1;

    private final int id;
    private String numarInmatriculare;
    private String marca;
    private int capacitate;
    private src.app.classes.Sofer sofer;

    public Autobuz(String numarInmatriculare, String marca, int capacitate) {
        this.numarInmatriculare = textValid(numarInmatriculare, "Numarul de inmatriculare").toUpperCase();
        this.marca = textValid(marca, "Marca");
        setCapacitate(capacitate);
        this.id = ID_seq;
        ID_seq++;
    }

    protected static String textValid(String valoare, String camp) {
        if (valoare == null || valoare.isBlank())
            throw new IllegalArgumentException(camp + " nu este valid(a)!");
        return valoare.strip();
    }

    /** Tipul vehiculului; este suprascris in clasele derivate. */
    public String getTip() {
        return "Autobuz";
    }

    public int getId() {
        return id;
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = textValid(marca, "Marca");
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        if (capacitate <= 0) throw new IllegalArgumentException("Capacitatea nu este valida!");
        this.capacitate = capacitate;
    }

    public src.app.classes.Sofer getSofer() {
        return sofer;
    }

    public void setSofer(Sofer sofer) {
        this.sofer = sofer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autobuz that = (Autobuz) o;
        return numarInmatriculare.equals(that.numarInmatriculare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numarInmatriculare);
    }

    @Override
    public String toString() {
        String soferTxt = (sofer == null) ? "fara sofer" : "sofer: " + sofer.getNume() + " " + sofer.getPrenume();
        return "[ID " + id + "] " + getTip() + " " + numarInmatriculare + " (" + marca + "), capacitate: "
                + capacitate + " locuri, " + soferTxt;
    }
}