package Java_code.Lab2.ex1;
import java.util.Scanner;
import  java.util.Random;

public class Lab2_ex1 {
    public static void main(String[] args){
        ContBancar cont_ex1=new ContBancar(0);
        float suma=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Dati o suma pentru depozitare:");
        suma=sc.nextFloat();
        cont_ex1.depozitareSuma(suma);
        System.out.println("Suma din cont este:"+cont_ex1.getSold());
        System.out.println("Mai dati o suma pentru depozitare:");
        suma=sc.nextFloat();
        cont_ex1.depozitareSuma(suma);
        System.out.println("Suma din cont este:"+cont_ex1.getSold());

        ContBancar cont_ex2 = new ContBancar(500);
        cont_ex2.depozitareSuma(300);
        System.out.println("Soldul contului este: "+cont_ex2.getSold());
        Random rnd= new Random();
        int N=rnd.nextInt(1001);

        while(N>0){
            double valoare= rnd.nextDouble(100);
            int chance= rnd.nextInt(100);
            if(chance<50){
                cont_ex2.depozitareSuma(valoare);
            }else{
                cont_ex2.retragereSuma(valoare);

            }
            N--;
        }
        System.out.println("Soldul din cont dupa toate operatiile este: "+ cont_ex2.getSold());

    }
}
