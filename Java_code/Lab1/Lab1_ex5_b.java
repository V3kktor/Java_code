package Java_code.Lab1;
import java.util.Scanner;
public class Lab1_ex5_b {

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);

        System.out.println("Dati un numar de 6 cifre:");
        int nr= sc.nextInt();

        System.out.println("Data nasterii este: an:" + nr%100 + " luna: " + (nr/100)%100 + " zi: "+(nr/10000)%100);
    }

}
