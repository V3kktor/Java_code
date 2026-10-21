package src.app.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Compania de transport: tine evidenta angajatilor, autobuzelor, statiilor si rutelor.
 */
public class Companie {
    private final String nume;
    private final List<Angajat> angajati = new ArrayList<>();
    private final List<Autobuz> autobuze = new ArrayList<>();
    private final List<Statie> statii = new ArrayList<>();
    private final List<Ruta> rute = new ArrayList<>();

    public Companie(String nume) {
        if (nume == null || nume.isBlank())
            throw new IllegalArgumentException("Numele companiei nu este valid!");
        this.nume = nume.strip();
    }

    public String getNume() {
        return nume;
    }

    public List<Angajat> getAngajati() {
        return Collections.unmodifiableList(angajati);
    }

    public List<Autobuz> getAutobuze() {
        return Collections.unmodifiableList(autobuze);
    }

    public List<Statie> getStatii() {
        return Collections.unmodifiableList(statii);
    }

    public List<Ruta> getRute() {
        return Collections.unmodifiableList(rute);
    }

    // ----- Adaugare (fara dubluri) -----

    public void adaugaAngajat(Angajat angajat) {
        if (angajat == null) throw new IllegalArgumentException("Angajatul nu este valid!");
        if (angajati.contains(angajat)) throw new IllegalStateException("Acest angajat exista deja!");
        angajati.add(angajat);
    }

    public void adaugaAutobuz(Autobuz autobuz) {
        if (autobuz == null) throw new IllegalArgumentException("Autobuzul nu este valid!");
        if (autobuze.contains(autobuz)) throw new IllegalStateException("Un autobuz cu acest numar exista deja!");
        autobuze.add(autobuz);
    }

    public void adaugaStatie(Statie statie) {
        if (statie == null) throw new IllegalArgumentException("Statia nu este valida!");
        if (statii.contains(statie)) throw new IllegalStateException("Aceasta statie exista deja!");
        statii.add(statie);
    }

    public void adaugaRuta(Ruta ruta) {
        if (ruta == null) throw new IllegalArgumentException("Ruta nu este valida!");
        if (rute.contains(ruta)) throw new IllegalStateException("O ruta cu acest numar exista deja!");
        rute.add(ruta);
    }

    // ----- Cautare dupa ID (intoarce null daca nu exista) -----

    public Angajat gasesteAngajat(int id) {
        for (Angajat a : angajati) if (a.getId() == id) return a;
        return null;
    }

    public Autobuz gasesteAutobuz(int id) {
        for (Autobuz a : autobuze) if (a.getId() == id) return a;
        return null;
    }

    public Statie gasesteStatie(int id) {
        for (Statie s : statii) if (s.getId() == id) return s;
        return null;
    }

    public Ruta gasesteRuta(int id) {
        for (Ruta r : rute) if (r.getId() == id) return r;
        return null;
    }

    // ----- Asignari -----

    /** Un sofer poate conduce un singur autobuz. */
    public void asigneazaSofer(src.app.classes.Autobuz autobuz, Sofer sofer) {
        for (Autobuz a : autobuze) {
            if (a != autobuz && a.getSofer() == sofer)
                throw new IllegalStateException("Soferul conduce deja autobuzul " + a.getNumarInmatriculare() + "!");
        }
        autobuz.setSofer(sofer);
    }

    /** Un autobuz poate circula pe o singura ruta. */
    public void asigneazaAutobuzPeRuta(Ruta ruta, Autobuz autobuz) {
        for (Ruta r : rute) {
            if (r != ruta && r.getAutobuze().contains(autobuz))
                throw new IllegalStateException("Autobuzul este deja asignat rutei " + r.getNumar() + "!");
        }
        ruta.adaugaAutobuz(autobuz);
    }

    // ----- Raport -----

    public List<String> genereazaRaport() {
        List<String> linii = new ArrayList<>();
        linii.add("===== RAPORT " + nume + " =====");
        adaugaSectiune(linii, "Angajati", angajati);
        adaugaSectiune(linii, "Autobuze", autobuze);
        adaugaSectiune(linii, "Statii", statii);
        adaugaSectiune(linii, "Rute", rute);
        return linii;
    }

    private static void adaugaSectiune(List<String> linii, String titlu, List<?> elemente) {
        linii.add("--- " + titlu + " (" + elemente.size() + ") ---");
        for (Object element : elemente) {
            linii.add(String.valueOf(element));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Companie that = (Companie) o;
        return nume.equals(that.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume);
    }

    @Override
    public String toString() {
        return "Compania " + nume + ": " + angajati.size() + " angajati, " + autobuze.size() + " autobuze, "
                + statii.size() + " statii, " + rute.size() + " rute";
    }
}