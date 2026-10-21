package src.app.classes;

import java.util.Objects;

public class Controlor extends Angajat {
    private String zonaControl;
    private int numarAmenzi = 0;

    public Controlor(String nume, String prenume, double salariu, String zonaControl) {
        super(nume, prenume, salariu);
        setZonaControl(zonaControl);
    }

    @Override
    public String getFunctie() {
        return "Controlor";
    }

    public String getZonaControl() {
        return zonaControl;
    }

    public void setZonaControl(String zonaControl) {
        this.zonaControl = textValid(zonaControl, "Zona de control");
    }

    public int getNumarAmenzi() {
        return numarAmenzi;
    }

    public void inregistreazaAmenda() {
        numarAmenzi++;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Controlor that = (Controlor) o;
        return zonaControl.equals(that.zonaControl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), zonaControl);
    }

    @Override
    public String toString() {
        return super.toString() + ", zona: " + zonaControl + ", amenzi: " + numarAmenzi;
    }
}