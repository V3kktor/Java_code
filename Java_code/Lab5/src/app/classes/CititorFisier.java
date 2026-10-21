package src.app.classes;

import app.interfaces.IReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Citeste date dintr-un fisier text.
 */
public class CititorFisier implements IReader {
    private final String caleFisier;
    private final Charset codare;
    private int numarCitiri = 0;

    public CititorFisier(String caleFisier) {
        this(caleFisier, StandardCharsets.UTF_8);
    }

    public CititorFisier(String caleFisier, Charset codare) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CititorFisier that = (CititorFisier) o;
        return caleFisier.equals(that.caleFisier) && codare.equals(that.codare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caleFisier, codare);
    }

    @Override
    public String toString() {
        return "CititorFisier{caleFisier='" + caleFisier + "', codare=" + codare
                + ", numarCitiri=" + numarCitiri + "}";
    }
}