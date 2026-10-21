package src.app.classes;

import app.interfaces.IWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/**
 * Scrie date intr-un fisier text.
 */
public class ScriitorFisier implements IWriter {
    private final String caleFisier;
    private final Charset codare;
    private int numarScrieri = 0;

    public ScriitorFisier(String caleFisier) {
        this(caleFisier, StandardCharsets.UTF_8);
    }

    public ScriitorFisier(String caleFisier, Charset codare) {
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

    public int getNumarScrieri() {
        return numarScrieri;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScriitorFisier that = (ScriitorFisier) o;
        return caleFisier.equals(that.caleFisier) && codare.equals(that.codare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caleFisier, codare);
    }

    @Override
    public String toString() {
        return "ScriitorFisier{caleFisier='" + caleFisier + "', codare=" + codare
                + ", numarScrieri=" + numarScrieri + "}";
    }
}