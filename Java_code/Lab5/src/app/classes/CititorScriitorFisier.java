package src.app.classes;

import app.interfaces.IReader;
import app.interfaces.IWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

/**
 * Citeste si scrie intr-un fisier text (implementeaza ambele interfete).
 * Metoda scrie este supraincarcata pentru mai multe tipuri de date (String, List).
 */
public class CititorScriitorFisier implements IReader, IWriter {
    private final String caleFisier;
    private final Charset codare;
    private int numarCitiri = 0;
    private int numarScrieri = 0;

    public CititorScriitorFisier(String caleFisier) {
        this(caleFisier, StandardCharsets.UTF_8);
    }

    public CititorScriitorFisier(String caleFisier, Charset codare) {
        if (caleFisier == null || caleFisier.isBlank())
            throw new IllegalArgumentException("Calea fisierului nu este valida!");
        if (codare == null)
            throw new IllegalArgumentException("Codarea nu este valida!");
        this.caleFisier = caleFisier.strip();
        this.codare = codare;
    }

    public String getCaleFisier() {
        return caleFisier;
    }

    public Charset getCodare() {
        return codare;
    }

    public int getNumarCitiri() {
        return numarCitiri;
    }

    public int getNumarScrieri() {
        return numarScrieri;
    }

    // ----- IReader -----

    @Override
    public String citesteTot() throws IOException {
        String continut = Files.readString(Path.of(caleFisier), codare);
        numarCitiri++;
        return continut;
    }

    @Override
    public List<String> citesteLinii() throws IOException {
        List<String> linii = Files.readAllLines(Path.of(caleFisier), codare);
        numarCitiri++;
        return linii;
    }

    // ----- IWriter -----

    @Override
    public void scrie(String text) throws IOException {
        if (text == null) throw new IllegalArgumentException("Textul nu este valid!");
        Files.writeString(Path.of(caleFisier), text, codare,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        numarScrieri++;
    }

    @Override
    public void adauga(String text) throws IOException {
        if (text == null) throw new IllegalArgumentException("Textul nu este valid!");
        Files.writeString(Path.of(caleFisier), text + System.lineSeparator(), codare,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        numarScrieri++;
    }

    // ----- Supraincarcare: aceeasi metoda, alt tip de date -----

    /** Scrie o lista in fisier, cate un element (toString) pe fiecare linie. */
    public void scrie(List<?> elemente) throws IOException {
        if (elemente == null) throw new IllegalArgumentException("Lista nu este valida!");
        StringBuilder sb = new StringBuilder();
        for (Object element : elemente) {
            sb.append(element).append(System.lineSeparator());
        }
        scrie(sb.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CititorScriitorFisier that = (CititorScriitorFisier) o;
        return caleFisier.equals(that.caleFisier) && codare.equals(that.codare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caleFisier, codare);
    }

    @Override
    public String toString() {
        return "CititorScriitorFisier{caleFisier='" + caleFisier + "', codare=" + codare
                + ", numarCitiri=" + numarCitiri + ", numarScrieri=" + numarScrieri + "}";
    }
}