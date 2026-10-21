package src.app.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Ruta {
    private static int ID_seq = 1;

    private final int id;
    private String numar;
    private final List<Statie> statii = new ArrayList<>();
    private final List<Autobuz> autobuze = new ArrayList<>();

    public Ruta(String numar) {
        if (numar == null || numar.isBlank())
            throw new IllegalArgumentException("Numarul rutei nu este valid!");
        this.numar = numar.strip().toUpperCase();
        this.id = ID_seq;
        ID_seq++;
    }

    public int getId() {
        return id;
    }

    public String getNumar() {
        return numar;
    }

    public List<Statie> getStatii() {
        return Collections.unmodifiableList(statii);
    }

    public List<Autobuz> getAutobuze() {
        return Collections.unmodifiableList(autobuze);
    }

    public void adaugaStatie(Statie statie) {
        if (statie == null) throw new IllegalArgumentException("Statia nu este valida!");
        if (statii.contains(statie)) throw new IllegalStateException("Statia face deja parte din ruta!");
        statii.add(statie);
    }

    public void adaugaAutobuz(Autobuz autobuz) {
        if (autobuz == null) throw new IllegalArgumentException("Autobuzul nu este valid!");
        if (autobuze.contains(autobuz)) throw new IllegalStateException("Autobuzul este deja pe aceasta ruta!");
        autobuze.add(autobuz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruta that = (Ruta) o;
        return numar.equals(that.numar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numar);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ID " + id + "] Ruta " + numar + ": ");
        if (statii.isEmpty()) {
            sb.append("fara statii");
        } else {
            for (int i = 0; i < statii.size(); i++) {
                if (i > 0) sb.append(" -> ");
                sb.append(statii.get(i).getNume());
            }
        }
        sb.append(" | autobuze: ");
        if (autobuze.isEmpty()) {
            sb.append("niciunul");
        } else {
            for (int i = 0; i < autobuze.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(autobuze.get(i).getNumarInmatriculare());
            }
        }
        return sb.toString();
    }
}