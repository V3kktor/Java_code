package Java_code.Project_1_OOP;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Elev {
    int INT_NOT_ASSIGNED =-1;
    String STRING_NOT_ASSIGNED="";
    Double DOUBLE_NOT_ASSIGNED=0.0;

    private int _id=-INT_NOT_ASSIGNED;
    private String _nume=STRING_NOT_ASSIGNED;
    private int _varsta= INT_NOT_ASSIGNED;
    private Double _medie=DOUBLE_NOT_ASSIGNED;

    public int get_id(){
        return _id;
    }
    public void set_id(){
        boolean valid=false;
        while(!valid) {
            Scanner sc = new Scanner(System.in);
            try {
                int new_id = sc.nextInt();
                if
            } catch (Exception e) {
                throw new InputMismatchException("Please enter a valid id!");
            }
        }
    }

}
