package Java_code.Lab2.ex3;
import java.util.ArrayList;
import java.util.Scanner;
public class Lab2_ex3 {
     static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int nr_masini = sc.nextInt();
        ArrayList<Masina> vectori_de_masini= new ArrayList<Masina>();


        for(int i =0;i<nr_masini;i++){
            Masina x= new Masina();
            System.out.println("Dati tipul culoarea si viteza masinii");
            System.out.print("Dati tipul: ");
            x.set_tip(sc.next());
            System.out.println("Dati culoarea: ");
            x.set_culoare(sc.next());
            System.out.println("Dati viteza: ");
            x.set_viteza(sc.nextDouble());
            vectori_de_masini.add(x);
        }
        for( Masina z: vectori_de_masini){
            System.out.println(z.toString());
        }


      for (Masina y : vectori_de_masini){
          if (y.get_viteza()>200){
              System.out.println("Masina "+y.get_tip()+ "este rapida");
          }
      }
     }
}
