package Java_code.Lab2.ex1;

public class ContBancar {
    private double _sold = 0.0;
    public ContBancar ( double sold ) {
        _sold = sold ;
    }
    public void depozitareSuma ( double suma ) {
        _sold += suma ;
    }
    public double getSold(){
        return _sold;
    }
    public boolean retragereSuma( double suma) {
        if(_sold-suma<0.0)
            return false;
        else{
            _sold-=suma;
            return true;
        }
    }
}
