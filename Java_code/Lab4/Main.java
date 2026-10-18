import garaj.Garaj;
import garaj.vehicule.InvalidInputException;
import garaj.vehicule.Vehicul;

import javax.swing.plaf.synth.SynthUI;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean EXIT=false;
        ArrayList<Vehicul> lista_Vehicule=new ArrayList<>();
        ArrayList<Garaj> lista_Garaje=new ArrayList<>();

        while(!EXIT){

            System.out.println(
                    """
                            \t\t -----MENIU-----
                            1. Adauga Vehicul
                            2. Creeaza garaj
                            3. Adauga vehicul intr-un garaj
                            4. Afiseaza toate vehiculele
                            5. Afiseaza toate garajele
                            6. Afiseaza vehiculele dintr-un garaj
                            7. Cauta vehicul dupa ID
                            8. Muta vehicul dintr-un garaj in altul
                            9. Sterge vehicul
                            10. Sterge garaj
                            11. Cauta vehicul dupa marca
                            12. Afiseaza statistici
                            13. Porneste un vehicul
                            14. Porneste toate vehiculele dintr-un garaj
                            0. Inchide aplicatia""");
            Scanner sc= new Scanner(System.in);
            String input=sc.nextLine();
            switch (input){
                case "0":

                    EXIT=true;

                    break;

                case "1":
                    Adauga_vehicul(lista_Vehicule);
                    break;

                default:
                    System.out.println("Input invalid!");
                    break;
            }


        }


    }



    private static void Adauga_vehicul(ArrayList<Vehicul> listaVehicule) {
        System.out.println("Ce vehicul doriti sa adaugati?(1:Masina sau 2:Motocicleta?())");
        Scanner sc= new Scanner(System.in);
        String tip_vehicul=sc.nextLine();
        if(tip_vehicul.equalsIgnoreCase("1")){
            Adauga_Masina();
        } else if (tip_vehicul.equalsIgnoreCase("2")) {
            Adauga_Motocicleta();
        } else throw new InvalidInputException("Dati un tip valid!");



    }

    private static void Adauga_Motocicleta() {
        boolean ok_marca=false;
        boolean ok_viteza=false;
        boolean ok_culoare=false;

        while(!ok_marca){
            System.out.println("Dati marca : ");
            try{
                Scanner sc= new Scanner(System.in);
                String marca=sc.nextLine();
                if(marca.isBlank()||marca.strip().) throw new InvalidInputException("Dati o marca valida!");
            }
        }
    }

    private static void Adauga_Masina() {
    }


}

