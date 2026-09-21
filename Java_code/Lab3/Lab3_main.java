package Java_code.Lab3;
import java.util.Scanner;

public class Lab3_main {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        System.out.println("Doriti sa cititi de la tastatura (2) sau din fisier(1): ");
        String read_type= sc.nextLine();
        DataReadManager manager=new DataReadManager();
        manager.set_readType(read_type);
        manager.Read_info();
        System.out.println(manager.toString());
    }
}
