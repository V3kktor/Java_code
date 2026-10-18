package Vehicule.Auto;

import garaj.vehicule.InvalidInputException;
import garaj.vehicule.Vehicul;

public class Motocicleta extends Vehicul {
    private String _culoare="";

    public Motocicleta(String marca){
        super(marca);

    }
    public Motocicleta(String marca,int viteza){
        super(marca,viteza);

    }
    public Motocicleta(String marca,int viteza,String culoare){
        super(marca,viteza);
        if(!culoare.isBlank()){
            this._culoare=culoare;
        }else throw new InvalidInputException("Culoarea nu este valida!");

    }

    @Override
    public void porneste() {
        System.out.println("garaj.vehicule.auto.Motocicleta"+super.get_marca()+" a pornit!");
    }

    public String get_culoare() {
        return _culoare;
    }

    public void set_culoare(String _culoare) {
        this._culoare = _culoare;
    }
}
