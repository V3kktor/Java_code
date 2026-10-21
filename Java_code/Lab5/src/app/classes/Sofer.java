package src.app.classes;

import java.util.Objects;

public class Sofer extends Angajat {
    private String categoriePermis;
    private int aniExperienta;

    public Sofer(String nume, String prenume, double salariu, String categoriePermis, int aniExperienta) {
        super(nume, prenume, salariu);
        setCategoriePermis(categoriePermis);
        setAniExperienta(aniExperienta);
    }

    @Override
    public String getFunctie() {
        return "Sofer";
    }

    public String getCategoriePermis() {
        return categoriePermis;
    }

    public void setCategoriePermis(String categoriePermis) {
        this.categoriePermis = textValid(categoriePermis, "Categoria permisului").toUpperCase();
    }

    public int getAniExperienta() {
        return aniExperienta;
    }

    public void setAniExperienta(int aniExperienta) {
        if (aniExperienta < 0) throw new IllegalArgumentException("Anii de experienta nu sunt valizi!");
        this.aniExperienta = aniExperienta;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Sofer that = (Sofer) o;
        return categoriePermis.equals(that.categoriePermis) && aniExperienta == that.aniExperienta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoriePermis, aniExperienta);
    }

    @Override
    public String toString() {
        return super.toString() + ", permis: " + categoriePermis + ", experienta: " + aniExperienta + " ani";
    }
}