package src.app.ui;

import src.app.classes.Angajat;
import src.app.classes.Autobuz;
import src.app.classes.AutobuzElectric;
import src.app.classes.CititorFisier;
import src.app.classes.CititorScriitorFisier;
import src.app.classes.Companie;
import src.app.classes.Controlor;
import src.app.classes.Ruta;
import src.app.classes.ScriitorFisier;
import src.app.classes.Sofer;
import src.app.classes.Statie;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FISIER_RAPORT = "raport_companie.txt";
    private static final Scanner sc = new Scanner(System.in);
    private static final Companie companie = new Companie("TransUrban");

    public static void main(String[] args) {
        boolean EXIT = false;

        while (!EXIT) {
            afiseazaMeniu();
            String input = sc.nextLine().strip();

            try {
                switch (input) {
                    case "0" -> EXIT = true;
                    case "1" -> adaugaAngajat();
                    case "2" -> adaugaAutobuz();
                    case "3" -> adaugaStatie();
                    case "4" -> creeazaRuta();
                    case "5" -> adaugaStatiePeRuta();
                    case "6" -> asigneazaAutobuzPeRuta();
                    case "7" -> asigneazaSofer();
                    case "8" -> afiseazaLista("Angajati", companie.getAngajati());
                    case "9" -> afiseazaLista("Autobuze", companie.getAutobuze());
                    case "10" -> afiseazaLista("Statii", companie.getStatii());
                    case "11" -> afiseazaLista("Rute", companie.getRute());
                    case "12" -> salveazaRaport();
                    case "13" -> citesteRaport();
                    case "14" -> adaugaNota();
                    case "15" -> inregistreazaAmenda();
                    default -> System.out.println("Input invalid!");
                }
            } catch (NoSuchFileException e) {
                System.out.println("Fisierul nu exista. Salvati mai intai raportul (optiunea 12).");
            } catch (IOException e) {
                System.out.println("Eroare la lucrul cu fisierul: " + e.getMessage());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Eroare: " + e.getMessage());
            }
        }
    }

    private static void afiseazaMeniu() {
        System.out.println(
                """

                        \t\t ----- %s -----
                        1. Adauga angajat
                        2. Adauga autobuz
                        3. Adauga statie
                        4. Creeaza ruta
                        5. Adauga statie pe o ruta
                        6. Asigneaza autobuz pe o ruta
                        7. Asigneaza sofer unui autobuz
                        8. Afiseaza angajatii
                        9. Afiseaza autobuzele
                        10. Afiseaza statiile
                        11. Afiseaza rutele
                        12. Salveaza raportul in fisier
                        13. Citeste raportul din fisier
                        14. Adauga o nota la sfarsitul raportului
                        15. Inregistreaza o amenda unui controlor
                        0. Inchide aplicatia""".formatted(companie.getNume()));
    }

    // ---------- Citire input ----------

    private static String citesteText(String mesaj) {
        while (true) {
            System.out.println(mesaj);
            String s = sc.nextLine();
            if (!s.isBlank()) return s.strip();
            System.out.println("Valoare invalida, incercati din nou.");
        }
    }

    private static int citesteInt(String mesaj, int minim) {
        while (true) {
            System.out.println(mesaj);
            try {
                int n = Integer.parseInt(sc.nextLine().strip());
                if (n >= minim) return n;
                System.out.println("Valoarea trebuie sa fie cel putin " + minim + ".");
            } catch (NumberFormatException e) {
                System.out.println("Introduceti un numar intreg valid.");
            }
        }
    }

    private static double citesteDouble(String mesaj, double minim) {
        while (true) {
            System.out.println(mesaj);
            try {
                double n = Double.parseDouble(sc.nextLine().strip().replace(',', '.'));
                if (n >= minim) return n;
                System.out.println("Valoarea trebuie sa fie cel putin " + minim + ".");
            } catch (NumberFormatException e) {
                System.out.println("Introduceti un numar valid.");
            }
        }
    }

    /** Cere un ID de ruta; intoarce null (si afiseaza un mesaj) daca nu exista. */
    private static Ruta citesteRuta() {
        if (companie.getRute().isEmpty()) {
            System.out.println("Nu exista nicio ruta.");
            return null;
        }
        int id = citesteInt("Dati ID-ul rutei: ", 1);
        Ruta r = companie.gasesteRuta(id);
        if (r == null) System.out.println("Nu exista o ruta cu ID-ul " + id + ".");
        return r;
    }

    private static Statie citesteStatie() {
        if (companie.getStatii().isEmpty()) {
            System.out.println("Nu exista nicio statie.");
            return null;
        }
        int id = citesteInt("Dati ID-ul statiei: ", 1);
        Statie s = companie.gasesteStatie(id);
        if (s == null) System.out.println("Nu exista o statie cu ID-ul " + id + ".");
        return s;
    }

    private static Autobuz citesteAutobuz() {
        if (companie.getAutobuze().isEmpty()) {
            System.out.println("Nu exista niciun autobuz.");
            return null;
        }
        int id = citesteInt("Dati ID-ul autobuzului: ", 1);
        Autobuz a = companie.gasesteAutobuz(id);
        if (a == null) System.out.println("Nu exista un autobuz cu ID-ul " + id + ".");
        return a;
    }

    private static Angajat citesteAngajat() {
        if (companie.getAngajati().isEmpty()) {
            System.out.println("Nu exista niciun angajat.");
            return null;
        }
        int id = citesteInt("Dati ID-ul angajatului: ", 1);
        Angajat a = companie.gasesteAngajat(id);
        if (a == null) System.out.println("Nu exista un angajat cu ID-ul " + id + ".");
        return a;
    }

    // ---------- Optiunile din meniu ----------

    // 1
    private static void adaugaAngajat() {
        System.out.println("Ce angajat doriti sa adaugati? (1: Sofer, 2: Controlor)");
        String tip = sc.nextLine().strip();
        if (!tip.equals("1") && !tip.equals("2")) throw new IllegalArgumentException("Dati un tip valid!");

        String nume = citesteText("Nume: ");
        String prenume = citesteText("Prenume: ");
        double salariu = citesteDouble("Salariu (RON): ", 1);

        Angajat angajat;
        if (tip.equals("1")) {
            String permis = citesteText("Categoria permisului (ex: D, DE): ");
            int ani = citesteInt("Ani de experienta: ", 0);
            angajat = new Sofer(nume, prenume, salariu, permis, ani);
        } else {
            String zona = citesteText("Zona de control: ");
            angajat = new Controlor(nume, prenume, salariu, zona);
        }
        companie.adaugaAngajat(angajat);
        System.out.println("Angajatul a fost adaugat cu ID-ul " + angajat.getId() + ".");
    }

    // 2
    private static void adaugaAutobuz() {
        System.out.println("Ce autobuz doriti sa adaugati? (1: Clasic, 2: Electric)");
        String tip = sc.nextLine().strip();
        if (!tip.equals("1") && !tip.equals("2")) throw new IllegalArgumentException("Dati un tip valid!");

        String inmatriculare = citesteText("Numar de inmatriculare: ");
        String marca = citesteText("Marca: ");
        int capacitate = citesteInt("Capacitate (locuri): ", 1);

        Autobuz autobuz;
        if (tip.equals("1")) {
            autobuz = new Autobuz(inmatriculare, marca, capacitate);
        } else {
            int autonomie = citesteInt("Autonomie (km): ", 1);
            autobuz = new AutobuzElectric(inmatriculare, marca, capacitate, autonomie);
        }
        companie.adaugaAutobuz(autobuz);
        System.out.println("Autobuzul a fost adaugat cu ID-ul " + autobuz.getId() + ".");
    }

    // 3
    private static void adaugaStatie() {
        String nume = citesteText("Numele statiei: ");
        String adresa = citesteText("Adresa: ");
        Statie statie = new Statie(nume, adresa);
        companie.adaugaStatie(statie);
        System.out.println("Statia a fost adaugata cu ID-ul " + statie.getId() + ".");
    }

    // 4
    private static void creeazaRuta() {
        String numar = citesteText("Numarul rutei (ex: 101): ");
        Ruta ruta = new Ruta(numar);
        companie.adaugaRuta(ruta);
        System.out.println("Ruta a fost creata cu ID-ul " + ruta.getId() + ".");
    }

    // 5
    private static void adaugaStatiePeRuta() {
        Ruta ruta = citesteRuta();
        if (ruta == null) return;
        Statie statie = citesteStatie();
        if (statie == null) return;

        ruta.adaugaStatie(statie);
        System.out.println("Statia a fost adaugata pe ruta " + ruta.getNumar() + ".");
    }

    // 6
    private static void asigneazaAutobuzPeRuta() {
        Ruta ruta = citesteRuta();
        if (ruta == null) return;
        Autobuz autobuz = citesteAutobuz();
        if (autobuz == null) return;

        companie.asigneazaAutobuzPeRuta(ruta, autobuz);
        System.out.println("Autobuzul " + autobuz.getNumarInmatriculare() + " circula acum pe ruta "
                + ruta.getNumar() + ".");
    }

    // 7
    private static void asigneazaSofer() {
        Autobuz autobuz = citesteAutobuz();
        if (autobuz == null) return;
        Angajat angajat = citesteAngajat();
        if (angajat == null) return;

        if (!(angajat instanceof Sofer sofer)) {
            System.out.println("Angajatul ales nu este sofer.");
            return;
        }
        companie.asigneazaSofer(autobuz, sofer);
        System.out.println("Soferul " + sofer.getNume() + " " + sofer.getPrenume() + " conduce acum autobuzul "
                + autobuz.getNumarInmatriculare() + ".");
    }

    // 8, 9, 10, 11
    private static void afiseazaLista(String titlu, List<?> elemente) {
        if (elemente.isEmpty()) {
            System.out.println(titlu + ": lista este goala.");
            return;
        }
        System.out.println(titlu + " (" + elemente.size() + "):");
        for (Object element : elemente) {
            System.out.println("  " + element);
        }
    }

    // 12 - foloseste clasa care implementeaza ambele interfete (metoda scrie supraincarcata pentru List)
    private static void salveazaRaport() throws IOException {
        CititorScriitorFisier fisier = new CititorScriitorFisier(FISIER_RAPORT);
        List<String> raport = companie.genereazaRaport();
        fisier.scrie(raport);
        System.out.println("Raportul (" + raport.size() + " linii) a fost salvat in " + FISIER_RAPORT + ".");
    }

    // 13 - foloseste clasa care implementeaza doar IReader
    private static void citesteRaport() throws IOException {
        CititorFisier cititor = new CititorFisier(FISIER_RAPORT);
        for (String linie : cititor.citesteLinii()) {
            System.out.println(linie);
        }
    }

    // 14 - foloseste clasa care implementeaza doar IWriter
    private static void adaugaNota() throws IOException {
        String nota = citesteText("Nota de adaugat: ");
        ScriitorFisier scriitor = new ScriitorFisier(FISIER_RAPORT);
        scriitor.adauga(nota);
        System.out.println("Nota a fost adaugata in " + FISIER_RAPORT + ".");
    }

    // 15
    private static void inregistreazaAmenda() {
        Angajat angajat = citesteAngajat();
        if (angajat == null) return;

        if (!(angajat instanceof Controlor controlor)) {
            System.out.println("Angajatul ales nu este controlor.");
            return;
        }
        controlor.inregistreazaAmenda();
        System.out.println("Amenda a fost inregistrata. " + controlor.getNumarAmenzi()
                + " amenzi pentru " + controlor.getNume() + " " + controlor.getPrenume() + ".");
    }
}