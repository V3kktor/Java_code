package Java_code.Lab3;
import java.util.Scanner;
public class DataReadManager {
    private String _read_type = "";
    private String _info="";
    public DataReadManager(){
    }
    Scanner sc=new Scanner(System.in);
    Read_file file=new Read_file();
    Read_keyboard kbd_imput= new Read_keyboard();

    public String get_readType(){
        return _read_type;
    }
    public void set_readType(String type){
        _read_type=type;
    }
    public String get_info(){
        return _info;
    }
    public void set_info(String new_info){
        _info=new_info;
    }
    public void Read_info(){

        if(_read_type.equals("1")){
            System.out.println("Give file path");
            file.set_filePath(sc.nextLine());
            _info= file.get_file_content();
        } else if (_read_type.equals("2")) {
            kbd_imput.set_keyboard_input();
            _info = kbd_imput.get_keyboard_input();
        }
    }
    public String toString(){
        return "{Informatia citita este:\n"+_info+" }";
    }
}
