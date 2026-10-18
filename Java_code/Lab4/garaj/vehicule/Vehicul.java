package garaj.vehicule;

public abstract class Vehicul {
    protected String _marca = "";
    protected int _viteza = 0;
    private int ID = 0;
    protected static int ID_seq = 0;

    public Vehicul(String marca) {
        this(marca, 100);
    }

    public Vehicul(String marca, int viteza) {
        if (marca == null || marca.isBlank()) throw new InvalidInputException("Marca nu este valida");
        if (viteza <= 0) throw new InvalidInputException("Viteza nu este valida");

        this._marca = marca.strip();
        this._viteza = viteza;
        this.ID = ID_seq;
        ID_seq++;
    }

    public abstract void porneste();

    public int get_viteza() {
        return _viteza;
    }

    public String get_marca() {
        return _marca;
    }

    public void set_marca(String _marca) {
        if (_marca == null || _marca.isBlank()) throw new InvalidInputException("Marca nu este valida");
        this._marca = _marca.strip();
    }

    public void set_viteza(int _viteza) {
        if (_viteza <= 0) throw new InvalidInputException("Viteza nu este valida");
        this._viteza = _viteza;
    }

    public int getID() {
        return ID;
    }
}