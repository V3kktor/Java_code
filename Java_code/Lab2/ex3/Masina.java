package Java_code.Lab2.ex3;

public class Masina {
    private String _tip="";
    private String _culoare="";
    private double _viteza=0.0;
    public Masina(){
    };
    public Masina(String tip, String culoare, double viteza){
        _tip=tip;
        _culoare=culoare;
        _viteza=viteza;
    }

    public double get_viteza() {
        return _viteza;
    }
    public String get_tip(){
        return _tip;
    }
    public String get_culoare(){
        return _culoare;
    }
    public void set_tip(String tip){
        _tip=tip;
    }
    public void set_culoare(String culoare){
        _culoare=culoare;
    }
    public void set_viteza(double valoare){
        _viteza=valoare;
    }
    public String toString(){
        return "Masina{Tipul= '"+ _tip + "', Culoare= '"+_culoare+"', Viteza= '"+_viteza+"'}";
    }
}
