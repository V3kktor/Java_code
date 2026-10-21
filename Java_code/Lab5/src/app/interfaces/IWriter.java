package app.interfaces;

import java.io.IOException;

/**
 * Contract pentru scrierea datelor intr-o destinatie (in cazul nostru, un fisier).
 */
public interface IWriter {

    /** Scrie textul in destinatie; continutul existent este inlocuit. */
    void scrie(String text) throws IOException;

    /** Adauga textul la sfarsitul destinatiei, pe o linie noua. */
    void adauga(String text) throws IOException;
}