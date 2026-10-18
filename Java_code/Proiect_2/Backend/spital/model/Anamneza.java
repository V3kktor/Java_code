package Backend.spital.model;

public class Anamneza {
    private String cnpPacient;
    private String simptome;
    private String istoricMedical;
    private String diagnosticPrincipal;
    private String medicatiePrescrisa;

    public Anamneza(String cnpPacient, String simptome, String istoricMedical, String diagnosticPrincipal, String medicatiePrescrisa) {
        this.cnpPacient = cnpPacient;
        this.simptome = simptome;
        this.istoricMedical = istoricMedical;
        this.diagnosticPrincipal = diagnosticPrincipal;
        this.medicatiePrescrisa = medicatiePrescrisa;
    }

    @Override
    public String toString() {
        // Înlocuim virgulele din text cu spații ca să nu strice structura fișierului CSV
        return cnpPacient + "," +
                simptome.replace(",", " ") + "," +
                istoricMedical.replace(",", " ") + "," +
                diagnosticPrincipal.replace(",", " ") + "," +
                medicatiePrescrisa.replace(",", " ");
    }
}