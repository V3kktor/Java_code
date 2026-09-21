package Java_code.test;
import java.io.File;
import java.util.Scanner;

public class test {
    public static void main(String[] args){

        try{
            Scanner fsc= new Scanner(new File("Imput_Lab3.txt"));
            String test=fsc.nextLine();
            System.out.println(test);
        }
        catch (Exception e){
            System.out.print("It does not work");
        }
    }
}
