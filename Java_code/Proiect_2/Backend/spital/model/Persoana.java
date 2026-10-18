package Backend.spital.model;

public abstract class Persoana {
    protected String nume;
    protected String prenume;
    protected String cnp;
    protected int varsta;

    // Constructor 1
    public Persoana(String nume, String prenume, String cnp, int varsta) {
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.varsta = varsta;
    }

    // Gettere și Settere
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }

    public String getCnp() { return cnp; }
    public void setCnp(String cnp) { this.cnp = cnp; }

    public int getVarsta() { return varsta; }
    public void setVarsta(int varsta) { this.varsta = varsta; }

    @Override
    public String toString() {
        return nume + "," + prenume + "," + cnp + "," + varsta;
    }
}