package Backend.spital.model;

public class Pacient extends Persoana {
    private String diagnosticPreliminar;

    public Pacient(String nume, String prenume, String cnp, int varsta, String diagnosticPreliminar) {
        super(nume, prenume, cnp, varsta);
        this.diagnosticPreliminar = diagnosticPreliminar;
    }

    public String getDiagnosticPreliminar() { return diagnosticPreliminar; }

    @Override
    public String toString() {
        return super.toString() + "," + diagnosticPreliminar;
    }
}