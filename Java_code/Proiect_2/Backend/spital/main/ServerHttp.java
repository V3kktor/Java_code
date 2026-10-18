package Backend.spital.main;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import Backend.spital.model.*;
import Backend.spital.service.CsvService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ServerHttp {

    // Colecții în memorie încărcate automat la pornire din CSV
    public static void main(String[] args) throws IOException {
        incarcaDateleInMemorieLaPornire();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Endpoint-uri Adăugare
        server.createContext("/api/sectie", new SectieHandler());
        server.createContext("/api/angajat", new AngajatHandler());
        server.createContext("/api/pacient", new PacientHandler());
        server.createContext("/api/anamneza", new AnamnezaHandler());

        // Endpoint-uri Rapoarte (GET)
        server.createContext("/api/raport/sectii", new RaportSectiiHandler());
        server.createContext("/api/raport/angajati", new RaportAngajatiHandler());
        server.createContext("/api/raport/pacienti", new RaportPacientiHandler());
        server.createContext("/api/raport/anamneze", new RaportAnamnezeHandler());

        // Endpoint-uri Ștergere (DELETE)
        server.createContext("/api/sterge/sectie", new StergeSectieHandler());
        server.createContext("/api/sterge/angajat", new StergeAngajatHandler());
        server.createContext("/api/sterge/pacient", new StergePacientHandler());
        server.createContext("/api/sterge/anamneza", new StergeAnamnezaHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("🚀 Serverul Java a pornit pe http://localhost:8080 și a încărcat datele din CSV!");
    }

    private static void incarcaDateleInMemorieLaPornire() {
        System.out.println("--- Încărcare date din CSV în memorie ---");
        System.out.println("Secții: " + CsvService.citesteLinii("sectii.csv").size() + " înregistrate.");
        System.out.println("Angajați: " + CsvService.citesteLinii("angajati.csv").size() + " înregistrați.");
        System.out.println("Pacienți: " + CsvService.citesteLinii("pacienti.csv").size() + " înregistrați.");
        System.out.println("Anamneze: " + CsvService.citesteLinii("anamneze.csv").size() + " înregistrate.");
        System.out.println("------------------------------------------");
    }

    private static void adaugaHeadereCors(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
    }

    // --- HANDLERE PENTRU SALVARE (POST) ---

    static class SectieHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) { exchange.sendResponseHeaders(204, -1); return; }
            String body = citesteBody(exchange);
            String nume = extractJsonField(body, "nume");
            String sef = extractJsonField(body, "sefSectie");

            CsvService.salveazaSectie(new Sectie(nume, sef));
            trimiteRaspuns(exchange, "Secție salvată cu succes!");
        }
    }

    static class AngajatHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) { exchange.sendResponseHeaders(204, -1); return; }
            String body = citesteBody(exchange);
            String nume = extractJsonField(body, "nume");
            String prenume = extractJsonField(body, "prenume");
            String cnp = extractJsonField(body, "cnp");
            int varsta = 30; // Valoare implicită sau poți parsa
            String functie = extractJsonField(body, "functie");
            double salariu = Double.parseDouble(extractJsonField(body, "salariu").isEmpty() ? "0" : extractJsonField(body, "salariu"));
            String sectie = extractJsonField(body, "sectie");

            CsvService.salveazaAngajat(new Angajat(nume, prenume, cnp, varsta, functie, salariu, sectie));
            trimiteRaspuns(exchange, "Angajat salvat cu succes!");
        }
    }

    static class PacientHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) { exchange.sendResponseHeaders(204, -1); return; }
            String body = citesteBody(exchange);
            String nume = extractJsonField(body, "nume");
            String prenume = extractJsonField(body, "prenume");
            String cnp = extractJsonField(body, "cnp");
            int varsta = Integer.parseInt(extractJsonField(body, "varsta").isEmpty() ? "0" : extractJsonField(body, "varsta"));
            String diagnostic = extractJsonField(body, "diagnostic");

            CsvService.salveazaPacient(new Pacient(nume, prenume, cnp, varsta, diagnostic));
            trimiteRaspuns(exchange, "Pacient salvat cu succes!");
        }
    }

    static class AnamnezaHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) { exchange.sendResponseHeaders(204, -1); return; }
            String body = citesteBody(exchange);
            String cnp = extractJsonField(body, "cnpPacient");
            String simptome = extractJsonField(body, "simptome");
            String istoric = extractJsonField(body, "istoricMedical");
            String diagnostic = extractJsonField(body, "diagnosticPrincipal");
            String medicatie = extractJsonField(body, "medicatiePrescrisa");

            CsvService.salveazaAnamneza(new Anamneza(cnp, simptome, istoric, diagnostic, medicatie));
            trimiteRaspuns(exchange, "Anamneză salvată cu succes!");
        }
    }

    // --- HANDLERE PENTRU RAPOARTE (GET) ---

    static class RaportSectiiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            List<String> linii = CsvService.citesteLinii("sectii.csv");
            trimiteJsonArray(exchange, linii);
        }
    }

    static class RaportAngajatiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            List<String> linii = CsvService.citesteLinii("angajati.csv");
            trimiteJsonArray(exchange, linii);
        }
    }

    static class RaportPacientiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            List<String> linii = CsvService.citesteLinii("pacienti.csv");
            trimiteJsonArray(exchange, linii);
        }
    }

    static class RaportAnamnezeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            List<String> linii = CsvService.citesteLinii("anamneze.csv");
            trimiteJsonArray(exchange, linii);
        }
    }

    // --- HANDLERE PENTRU ȘTERGERE (DELETE / POST) ---

    static class StergeSectieHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) { exchange.sendResponseHeaders(204, -1); return; }
            String body = citesteBody(exchange);
            String numeDeSters = extractJsonField(body, "nume");

            List<String> linii = CsvService.citesteLinii("sectii.csv");
            linii.removeIf(l -> l.startsWith(numeDeSters + ","));
            CsvService.suprascrieFisier("sectii.csv", linii);
            trimiteRaspuns(exchange, "Secție ştearsă cu succes!");
        }
    }

    static class StergeAngajatHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) { exchange.sendResponseHeaders(204, -1); return; }
            String body = citesteBody(exchange);
            String cnpDeSters = extractJsonField(body, "cnp");

            List<String> linii = CsvService.citesteLinii("angajati.csv");
            linii.removeIf(l -> l.contains(cnpDeSters));
            CsvService.suprascrieFisier("angajati.csv", linii);
            trimiteRaspuns(exchange, "Angajat şters cu succes!");
        }
    }

    static class StergePacientHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) { exchange.sendResponseHeaders(204, -1); return; }
            String body = citesteBody(exchange);
            String cnpDeSters = extractJsonField(body, "cnp");

            List<String> linii = CsvService.citesteLinii("pacienti.csv");
            linii.removeIf(l -> l.contains(cnpDeSters));
            CsvService.suprascrieFisier("pacienti.csv", linii);
            trimiteRaspuns(exchange, "Pacient şters cu succes!");
        }
    }

    static class StergeAnamnezaHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            adaugaHeadereCors(exchange);
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) { exchange.sendResponseHeaders(204, -1); return; }
            String body = citesteBody(exchange);
            String cnpDeSters = extractJsonField(body, "cnp");

            List<String> linii = CsvService.citesteLinii("anamneze.csv");
            linii.removeIf(l -> l.startsWith(cnpDeSters + ","));
            CsvService.suprascrieFisier("anamneze.csv", linii);
            trimiteRaspuns(exchange, "Anamneză ştearsă cu succes!");
        }
    }

    // --- METODE AJUTĂTOARE ---

    private static String citesteBody(HttpExchange exchange) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        return sb.toString();
    }

    private static void trimiteRaspuns(HttpExchange exchange, String mesaj) throws IOException {
        exchange.sendResponseHeaders(200, mesaj.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = exchange.getResponseBody();
        os.write(mesaj.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private static void trimiteJsonArray(HttpExchange exchange, List<String> linii) throws IOException {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < linii.size(); i++) {
            json.append("\"").append(linii.get(i).replace("\"", "\\\"")).append("\"");
            if (i < linii.size() - 1) json.append(",");
        }
        json.append("]");
        byte[] bytes = json.toString().getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    private static String extractJsonField(String json, String key) {
        try {
            String searchKey = "\"" + key + "\":\"";
            int startIndex = json.indexOf(searchKey);
            if (startIndex == -1) {
                // Încearcă fără ghilimele (pentru numere ex: varsta sau salariu)
                searchKey = "\"" + key + "\":";
                startIndex = json.indexOf(searchKey);
                if (startIndex == -1) return "";
                startIndex += searchKey.length();
                int endIndex = json.indexOf(",", startIndex);
                if (endIndex == -1) endIndex = json.indexOf("}", startIndex);
                return json.substring(startIndex, endIndex).trim();
            }
            startIndex += searchKey.length();
            int endIndex = json.indexOf("\"", startIndex);
            if (endIndex == -1) return "";
            return json.substring(startIndex, endIndex);
        } catch (Exception e) {
            return "";
        }
    }
}