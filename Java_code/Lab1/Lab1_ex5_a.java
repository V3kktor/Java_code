package Java_code.Lab1;

import java.util.Scanner;
import java.util.Random;
public class Lab1_ex5_a {

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Dati limita de incercari: ");
        int limita = sc.nextInt();
        Random rnd= new Random();
        int nr_de_ghicit= rnd.nextInt(100);

        for( int i =0;i<limita;++i){
            System.out.println("Incearca sa ghicesti nr intre 0 si 100:");
            int guess= sc.nextInt();
            if(guess==nr_de_ghicit) {
                System.out.println("Felicitari ai ghicit numarul!");
                break;
            } else if (guess>nr_de_ghicit) {
                System.out.println("Numarul este mai mic decat ce ai ghicit tu!");

            }else{
                System.out.println("Numarul este mai mare decat ce ai ghicit tu!");


            }
        }

    }
}