package app.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * Contract pentru citirea datelor dintr-o sursa (in cazul nostru, un fisier).
 */
public interface IReader {

    /** Citeste intreg continutul sursei ca un singur text. */
    String citesteTot() throws IOException;

    /** Citeste continutul sursei, linie cu linie. */
    List<String> citesteLinii() throws IOException;
}