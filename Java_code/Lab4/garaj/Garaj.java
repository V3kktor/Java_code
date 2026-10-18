package garaj;

import garaj.vehicule.InvalidInputException;
import garaj.vehicule.Vehicul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Garaj {
    private static int ID_seq = 0;
    private int _ID = 0;
    private int _capacitate = 0;

    private final ArrayList<Vehicul> Lista_vehicule = new ArrayList<>();

    public Garaj() {
        this._ID = ID_seq;
        ID_seq++;
    }

    public Garaj(int _capacitate) {
        if (_capacitate < 0) throw new InvalidInputException("Capacitatea nu este valida!");
        this._ID = ID_seq;
        ID_seq++;
        this._capacitate = _capacitate;
    }

    public int get_capacitate() {
        return _capacitate;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_capacitate(int _capacitate) {
        if (_capacitate < Lista_vehicule.size())
            throw new InvalidInputException("Capacitatea nu poate fi mai mica decat numarul de vehicule din garaj!");
        this._capacitate = _capacitate;
    }

    public int nr_vehicule() {
        return Lista_vehicule.size();
    }

    public boolean este_plin() {
        return Lista_vehicule.size() >= _capacitate;
    }

    public boolean contine(Vehicul v) {
        return Lista_vehicule.contains(v);
    }

    public List<Vehicul> get_vehicule() {
        return Collections.unmodifiableList(Lista_vehicule);
    }

    public void adauga(Vehicul v) {
        if (v == null) throw new InvalidInputException("Vehiculul nu este valid!");
        if (Lista_vehicule.contains(v)) throw new IllegalStateException("Vehiculul este deja in acest garaj!");
        if (este_plin()) throw new IllegalStateException("Garajul este plin!");
        Lista_vehicule.add(v);
    }

    public boolean sterge(Vehicul v) {
        return Lista_vehicule.remove(v);
    }

    @Override
    public String toString() {
        return "Garaj ID " + _ID + ": " + Lista_vehicule.size() + "/" + _capacitate + " locuri ocupate";
    }
}