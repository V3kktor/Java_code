package garaj.vehicule.auto;

import garaj.vehicule.InvalidInputException;
import garaj.vehicule.Vehicul;

public class Masina extends Vehicul {
    private int _nr_usi = 0;

    public Masina(String marca, int numar_usi) {
        super(marca);
        setNrUsi(numar_usi);
    }

    public Masina(String marca, int viteza, int numar_usi) {
        super(marca, viteza);
        setNrUsi(numar_usi);
    }

    private void setNrUsi(int numar_usi) {
        if (numar_usi <= 0) throw new InvalidInputException("Numarul de usi nu este valid");
        this._nr_usi = numar_usi;
    }

    public int get_nr_usi() {
        return _nr_usi;
    }

    @Override
    public void porneste() {
        System.out.println("Masina " + _marca + " a pornit!");
    }

    @Override
    public String toString() {
        return "[ID " + getID() + "] Masina " + _marca + " are viteza: " + _viteza + " si are " + _nr_usi + " usi";
    }
}