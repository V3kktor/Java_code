package Backend.spital.model;

public class Sectie {
    private String numeSectie;
    private String sefSectie;

    public Sectie(String numeSectie, String sefSectie) {
        this.numeSectie = numeSectie;
        this.sefSectie = sefSectie;
    }

    public String getNumeSectie() { return numeSectie; }
    public String getSefSectie() { return sefSectie; }

    @Override
    public String toString() {
        return numeSectie + "," + sefSectie;
    }
}