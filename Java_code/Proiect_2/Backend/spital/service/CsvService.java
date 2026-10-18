package Backend.spital.service;

import Backend.spital.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvService {

    public static void salveazaAngajat(Angajat angajat) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("angajati.csv", true))) {
            pw.println(angajat.toString());
        } catch (IOException e) {
            System.err.println("Eroare la salvarea angajatului: " + e.getMessage());
        }
    }

    public static void salveazaPacient(Pacient pacient) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("pacienti.csv", true))) {
            pw.println(pacient.toString());
        } catch (IOException e) {
            System.err.println("Eroare la salvarea pacientului: " + e.getMessage());
        }
    }

    public static void salveazaSectie(Sectie sectie) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("sectii.csv", true))) {
            pw.println(sectie.toString());
        } catch (IOException e) {
            System.err.println("Eroare la salvarea secției: " + e.getMessage());
        }
    }

    public static void salveazaAnamneza(Anamneza anamneza) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("anamneze.csv", true))) {
            pw.println(anamneza.toString());
        } catch (IOException e) {
            System.err.println("Eroare la salvarea anamnezei: " + e.getMessage());
        }
    }

    // Metode de citire a întregului conținut pentru rapoarte și încărcare în memorie
    public static List<String> citesteLinii(String numeFisier) {
        List<String> linii = new ArrayList<>();
        File file = new File(numeFisier);
        if (!file.exists()) return linii;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                if (!linie.trim().isEmpty()) {
                    linii.add(linie);
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului " + numeFisier);
        }
        return linii;
    }

    // Metodă generală de suprascriere a fișierului (folosită la ștergere)
    public static void suprascrieFisier(String numeFisier, List<String> liniiNoi) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(numeFisier, false))) {
            for (String linie : liniiNoi) {
                pw.println(linie);
            }
        } catch (IOException e) {
            System.err.println("Eroare la suprascrierea fișierului " + numeFisier);
        }
    }
}