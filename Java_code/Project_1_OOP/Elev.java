
import javax.lang.model.type.NullType;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Elev {

    private boolean is_nume_valid(String new_nume){
        if(new_nume.equals("")){
            return false;
        } else  {
            return true;
        }
    }
    private static int INT_NOT_ASSIGNED =-1;
    private static String STRING_NOT_ASSIGNED="";
    private static Double DOUBLE_NOT_ASSIGNED=0.0;




    public static int Sequence = 0;

    private int _id=-INT_NOT_ASSIGNED;
    private String _nume=STRING_NOT_ASSIGNED;
    private int _varsta= INT_NOT_ASSIGNED;
    private Double _medie=DOUBLE_NOT_ASSIGNED;
    private boolean _promovat=false;
    public Elev(){
        _id=Sequence;
        Sequence++;
    }
    public Elev(String nume, int varsta){
        _id=Sequence;
        Sequence++;
        if(is_nume_valid(nume)){
            _nume=nume;
        }else throw new InputMismatchException("Numele nu este valid!");
        if(is_varsta_valid(varsta)){
            _varsta=varsta;
        }else throw new InputMismatchException("Varsta nu este valida!");

    }
    public Elev(String nume, int varsta, Double medie){
        _id=Sequence;
        Sequence++;
        if(is_nume_valid(nume)){
            _nume=nume;
        }else throw new InputMismatchException("Numele nu este valid!");
        if(is_varsta_valid(varsta)){
            _varsta=varsta;
        }else throw new InputMismatchException("Varsta nu este valida!");
        if(is_medie_valid(medie)){
            _medie=medie;
            if(_medie>=5){
                _promovat=true;
            }
        }else throw new InputMismatchException("Media nu este valida!");

    }

    public int get_id(){
        return _id;
    }
    public String get_nume(){
        return _nume;
    }
    public void set_nume(String new_nume){
        if(is_nume_valid(new_nume)){
            _nume=new_nume;
        }else throw new InputMismatchException("Numele nu este valid!");
    }

    public int get_varsta(){
        return _varsta;
    }

    private boolean is_varsta_valid(int new_varsta){
        if(new_varsta<10||new_varsta>40){
            return false;
        }
        return true;
    }

    public void set_varsta(int new_varsta){
        if(is_varsta_valid(new_varsta)){
            _varsta=new_varsta;
        }else throw new InputMismatchException("Varsta nu este valida!");
    }

    public Double get_medie(){
        return _medie;
    }

    private boolean is_medie_valid( Double new_medie){
        if(new_medie<1||new_medie>10){
            return false;
        }
        return true;
    }

    public void set_medie(Double new_medie){
        if(is_medie_valid(new_medie)){
            _medie=new_medie;
            if(_medie>=5){
                _promovat=true;
            }
        }else throw new InputMismatchException("Media nu este valida!");
    }

    public String toString(){
        return "Id elev: "+_id+" { Nume: "+_nume+" Varsta: "+_varsta+" Medie: "+_medie+"}";
    }

    public boolean is_promovat() {
        return _promovat;
    }

    public void set_id(int id)
    {
        _id=id;
    }
    public void set_promovat(boolean promovat){
        _promovat=promovat;
    }

}
