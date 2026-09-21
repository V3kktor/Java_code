package Java_code.Lab3;
import java.util.Scanner;
public class Read_keyboard {
    private String _keyboard_input="";

    Scanner sc= new Scanner(System.in);
    public Read_keyboard(){

    }
    public String get_keyboard_input(){
        return _keyboard_input;
    }

    public void set_keyboard_input() {
        System.out.println("Give input: ");
        _keyboard_input = sc.nextLine() ;
    }
}
