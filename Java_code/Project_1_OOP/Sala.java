import java.util.ArrayList;
import java.util.InputMismatchException;

public class Sala {
    private  ArrayList<Elev> _elevi = new ArrayList<>();
    private static int counter =0;
    private int _id=0;
    private String _name="";
    private int _capacity=0;


    public Sala() {
        _id=counter;
        counter++;
    }
    private boolean is_nume_valid(String new_nume){
        if(new_nume.equals("")){
            return false;
        } else  {
            return true;
        }
    }

    private boolean is_capacity_valid(int capacitate){
        if(capacitate<=0){
            return false;
        }
        else return true;
    }
    public Sala(String nume){
        _id=counter;
        counter++;
        if(is_nume_valid(nume)){
            _name=nume;
        }else throw new InputMismatchException("Dati un nume valid!");
    }

    public Sala(String nume, int capacitate, ArrayList<Elev> elevi_new ){
        _id=counter;
        counter++;
        if(is_nume_valid(nume)){
            _name=nume;
        }else throw new InputMismatchException("Dati un nume valid!");
        if (is_capacity_valid(capacitate)){
            _capacity=capacitate;

        }else throw new InputMismatchException("Dati o capacitate valida!");
        _elevi=elevi_new;
    }

    public ArrayList<Elev> get_elevi() {
        return _elevi;
    }
    public String get_name(){
        return _name;
    }

    public int get_capacity() {
        return _capacity;
    }

    public int get_id() {
        return _id;
    }

    public void set_capacity(int capacity) {
        if(is_capacity_valid(capacity))
            _capacity = capacity;
        else throw new InputMismatchException("Dati o capacitate valida!");

    }

    public void set_name(String name) {
        if(is_nume_valid(name))
            _name = name;
        else throw new InputMismatchException("Dati un nume valid!");
    }

    public void add_elev(Elev e){
        if(!_elevi.add(e)) throw new InputMismatchException("Elevul deja exista!");
    }

    public String toString(){
        StringBuffer return_string=new StringBuffer();
        return_string.append("Sala: "+_id+ "\n{ Nume: "+_name+" Capacitate: "+_capacity+" Elevi:\n");
        for(Elev elev :_elevi ){
            return_string.append(elev.toString()+"\n");
        }
        return return_string.append(" }\n").toString();
    }
}
