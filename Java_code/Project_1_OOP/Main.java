
/*private String input_new_nume(){
    Scanner sc=new Scanner(System.in);
    System.out.println("Dati un nume elevului: ");
    String new_nume=sc.nextLine();
    while(!is_nume_valid(new_nume)){
        System.out.println("Numele este valid! Dati un alt nume: ");
        new_nume=sc.nextLine();
    }
    return new_nume;
}*/


import java.util.ArrayList;
import java.util.Scanner;




public class Main {



    static void main()
    {
        ArrayList<Elev> Lista_elevii=new ArrayList<>();
        ArrayList<Sala> Lista_sali=new ArrayList<>();
        boolean EXIT=false;
        while(!EXIT){

            System.out.println(
                    """
                            \t\t -----MENIU-----
                            1. Adauga elev
                            2. Creeaza sala
                            3. Adauga elev intr-o sala
                            4. Afiseaza toti elevii
                            5. Afiseaza toate salile
                            6. Afiseaza elevii unei sali
                            7. Cauta elev dupa ID
                            8. Muta elev dintr-o sala in alta
                            9. Sterge elev
                            10. Sterge sala
                            11. Filtreaza elevii promovati
                            12. Filtreaza elevii nepromovati
                            13. Filtreaza elevii dupa interval de medie
                            14. Filtreaza elevii dupa varsta
                            15. Cauta elevi dupa nume
                            16. Afiseaza statistici
                            17. Sorteaza elevii
                            0. Inchide aplicatia""");
            Scanner sc= new Scanner(System.in);
            String input=sc.nextLine();
            switch (input){
                case "0":
                    EXIT=true;
                    break;

                case "1":
                    Adauga_elev(Lista_elevii);
                    break;
                case "2":
                    Adauga_sala(Lista_sali);
                    break;
                case "3":
                    Adauga_elev_sala(Lista_elevii,Lista_sali);
                    break;
                case "4":
                    Afisare_elevi(Lista_elevii);
                    break;
                case "5":
                    Afisare_sali(Lista_sali);
                    break;
                case "6":
                    Afiseaza_elevi_sala(Lista_sali);
                    break;
                case "7":
                    Cauta_elev_id(Lista_elevii);
                    break;
                case "8":
                    Muta_elev_sala(Lista_sali);
                    break;
                case "9":
                    Sterge_elev(Lista_elevii,Lista_sali);
                    break;
                case "10":
                    Sterge_sala(Lista_sali);
                    break;
                case "11":
                    Filtreaza_elevi_promovati(Lista_elevii);
                    break;
                case "12":
                    Filtreaza_elevi_nepromovati(Lista_elevii);
                    break;
                case "13":
                    Filtreaza_elev_interval_medie(Lista_elevii);
                    break;
                case "14":
                    Filtreaza_elevi_varsta(Lista_elevii);
                    break;
                case "15":
                    Cauta_elev_nume(Lista_elevii);
                    break;
                case "16":
                    Afiseaza_statistici(Lista_elevii,Lista_sali);
                    break;
                case "17":
                    Sorteaza_elevi_varsta(Lista_elevii);
                    break;
                default:
                    System.out.println("Input invalid!");
                    break;
            }


        }


    }

    private static void Afiseaza_statistici(ArrayList<Elev> listaElevii, ArrayList<Sala> listaSali) {
        if (listaElevii.isEmpty()) {
            System.out.println("Nu exista elevi pentru calcularea statisticilor.");
            return;
        }

        double sumaMedii = 0;
        int promovati = 0;
        Elev maxElev = listaElevii.getFirst();
        Elev minElev = listaElevii.getFirst();

        for (Elev e : listaElevii) {
            sumaMedii += e.get_medie();
            if (e.is_promovat()) promovati++;
            if (e.get_medie() > maxElev.get_medie()) maxElev = e;
            if (e.get_medie() < minElev.get_medie()) minElev = e;
        }

        double mediaGenerala = sumaMedii / listaElevii.size();
        double procentPromovare = ((double) promovati / listaElevii.size()) * 100;

        System.out.println("========== STATISTICI GENERALE ==========");
        System.out.println("Numar total elevi: " + listaElevii.size());
        System.out.println("Numar total sali: " + listaSali.size());
        System.out.printf("Media generala a scolii: %.2f\n", mediaGenerala);
        System.out.printf("Rata de promovabilitate: %.2f%%\n", procentPromovare);
        System.out.println("Elevul cu cea mai mare medie: " + maxElev.get_nume() + " (" + maxElev.get_medie() + ")");
        System.out.println("Elevul cu cea mai mica medie: " + minElev.get_nume() + " (" + minElev.get_medie() + ")");
        System.out.println("=========================================");
    }


    private static void Cauta_elev_nume(ArrayList<Elev> listaElevii) {
        if(listaElevii.isEmpty()){
            System.out.println("Nu exista elevi!");
            return;
        }
        System.out.println("Dati numele dorit:");
        String nume_cautat="";
        boolean ok=false;
        while(!ok){
            try{
                nume_cautat=Citeste_nume();
                ok=true;
            } catch (Exception e) {
                System.out.println("Dati un nume valid!");
            }
        }
        ok=false;
        for(Elev e :listaElevii){
            if(e.get_nume().equals(nume_cautat)){
                ok=true;
                System.out.println(e);
            }
        }
        if(!ok){
            System.out.println("Nu exista elev cu acest nume!");
        }
    }

    private static void Filtreaza_elevi_varsta(ArrayList<Elev> listaElevii) {
        if(listaElevii.isEmpty()){
            System.out.println("Nu exista elevi!");
            return;
        }
        int varsta=0;
        boolean ok=false;
        while(!ok){
            try{
                varsta=Citeste_varsta();
                ok=true;
            }catch (Exception e){
                System.out.println("Varsta data nu este valida!");
            }
        }
        ArrayList<Elev> lista_varsta=new ArrayList<>();
        for(Elev e:listaElevii){
            if(e.get_varsta()>varsta){
                lista_varsta.add(e);
            }
        }
        if(lista_varsta.isEmpty()){
            System.out.println("Nu exista elev cu varsta mai mare dacat cea data!");
        }else {
            System.out.println("Elevii cu varsta mai mare decat cea data sunt:");
            System.out.println(lista_varsta);
        }
    }

    private static void Sorteaza_elevi_varsta(ArrayList<Elev> listaElevii) {
        if(listaElevii.isEmpty()){
            System.out.println("Nu exista elevi!");
            return;
        }

        for(Elev d:listaElevii){
            for(Elev t:listaElevii){
                if(d.get_varsta()<t.get_varsta()){
                    swap_elev(d,t);
                }
            }
        }
        System.out.println("Elevii sortati dupa varsta:");
        System.out.println(listaElevii);
    }

    private static void swap_elev(Elev d, Elev t) {
        int temp_id= d.get_id();
        d.set_id(t.get_id());
        t.set_id(temp_id);

        String temp_nume=d.get_nume();
        d.set_nume(t.get_nume());
        t.set_nume(temp_nume);

        int temp_varsta=d.get_varsta();
        d.set_varsta(t.get_varsta());
        t.set_varsta(temp_varsta);

        Double temp_medie=d.get_medie();
        d.set_medie(t.get_medie());
        t.set_medie(temp_medie);

        boolean temp_pro=d.is_promovat();
        d.set_promovat(t.is_promovat());
        t.set_promovat(temp_pro);
    }

    private static void Filtreaza_elev_interval_medie(ArrayList<Elev> listaElevii) {
        if(listaElevii.isEmpty()){
            System.out.println("Nu exista elevi!");
            return;
        }
        System.out.println("Dati intervalul de medie:\n Minimul:");
        Double min_medie=0.0,max_medie=0.0;
        boolean ok=false;
        while(!ok){
            try {
                min_medie=Citeste_medie();
                ok=true;
            } catch (Exception e) {
                System.out.println("Media data nu este valida!");
            }
        }
        System.out.println("Maximul: ");
        ok=false;
        while(!ok){
            try {
                max_medie=Citeste_medie();
                if(max_medie<min_medie) throw new Exception();
                ok=true;
            } catch (Exception e) {
                System.out.println("Media data nu este valida!");
            }
        }
        boolean exista_in_interval=false;
        ArrayList<Elev> elevi_in_interval=new ArrayList<>();
        for(Elev e : listaElevii){
            if(min_medie<=e.get_medie()&&e.get_medie()<=max_medie){
                exista_in_interval=true;
                elevi_in_interval.add(e);
            }
        }
        if(exista_in_interval){
            System.out.println("Elevii cu media in intervalul dat sunt:");
            System.out.println(elevi_in_interval);
        }else System.out.println("Nu exista elevi in intervalul dat!");



    }

    private static void Filtreaza_elevi_nepromovati(ArrayList<Elev> listaElevii) {
        if(listaElevii.isEmpty()){
            System.out.println("Nu exista elevi!");
            return;
        }
        boolean nepromovati=false;
        ArrayList<Elev> elevi_nepromovati=new ArrayList<>();
        for(Elev e :listaElevii){
            if(!e.is_promovat()){
                nepromovati=true;
                elevi_nepromovati.add(e);
            }
        }
        if(nepromovati){
            System.out.println("Elevii nepromovati sunt:");
            System.out.println(elevi_nepromovati);
        }else System.out.println("Nu exista elevi nepromoovati!");
    }

    private static void Filtreaza_elevi_promovati(ArrayList<Elev> listaElevii) {
        if(listaElevii.isEmpty()){
            System.out.println("Nu exista elevi!");
            return;
        }
        boolean promovati=false;
        ArrayList<Elev> elevi_promovati=new ArrayList<>();
        for(Elev e :listaElevii){
            if(e.is_promovat()){
                promovati=true;
                elevi_promovati.add(e);
            }
        }
        if(promovati){
            System.out.println("Elevii promovati sunt:");
            System.out.println(elevi_promovati);
        }else System.out.println("Nu exista elevi promoovati!");
    }

    private static void Sterge_sala(ArrayList<Sala> listaSali) {
        if(listaSali.isEmpty()){
            System.out.println("Nu exista sali!");
            return;
        }
        System.out.println("Ce sala doresti sa stergi?");
        int id_sala_delete;
        int index=0;
        boolean ok=false;
        while(!ok) {
            try {
                id_sala_delete = Citeste_id();
                for (Sala s : listaSali) {
                    if (s.get_id() == id_sala_delete) {
                        ok = true;
                        break;
                    }
                    index++;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Nu exista sala cu acest ID! Dati altul!");
                System.out.println("Salile sunt:");
                System.out.println(listaSali);
            }
        }
        listaSali.remove(listaSali.get(index));
    }

    private static void Sterge_elev(ArrayList<Elev> listaElevii, ArrayList<Sala> listaSali) {
        if(listaElevii.isEmpty()){
            System.out.println("Nu exista elevi , nu ai ce sterge!");
            return;
        }
        int id_to_delete=-1;
        boolean ok=false;
        while(!ok){
            try {
                id_to_delete = Citeste_id();
                for (Elev e : listaElevii) {
                    if(e.get_id()==id_to_delete)
                    {
                        ok=true;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Nu exista un elev cu acest ID! Dati altul!");
                System.out.println("Elevii sunt:");
                System.out.println(listaElevii);
            }
        }
        for(Sala s:listaSali){
            for(Elev e:s.get_elevi()){
                if(e.get_id()==id_to_delete){
                    s.get_elevi().remove(e);

                }
            }
        }
        for(Elev e:listaElevii){
            if(id_to_delete==e.get_id()){
                listaElevii.remove(e);
            }
        }

    }

    private static void Muta_elev_sala(ArrayList<Sala> listaSali) {
        if(listaSali.size()<2) System.out.println("Sunt mai putin de 2 sali in sistem!");

        System.out.println("Din ce sala doresti sa muti elevul?(ID)");
        int id_sala_root=-1;
        boolean ok=false;
        while(!ok){
            id_sala_root=Citeste_id();
            for(Sala s:listaSali){
                if(s.get_id()==id_sala_root){
                    ok=true;
                    break;
                }

            }
            if(!ok) System.out.println("Nu exista aceasta sala!");
        }
        if(listaSali.get(id_sala_root).get_elevi().isEmpty()){
            System.out.println("Nu este niciun elev in sala asta!");
            return;
        }
        int id_sala_dest=-1;
        ok=false;
        while(!ok){
            id_sala_root=Citeste_id();
            for(Sala s:listaSali){
                if(s.get_id()==id_sala_dest&&id_sala_dest!=id_sala_root){
                    ok=true;
                    break;
                }

            }
            if(!ok) System.out.println("Nu exista aceasta sala! Sau incerci sa muti in aceeasi sala");
        }
        System.out.println("Ce elevi doriti sa mutati?");
        int id_elev=-1;
        ok=false;
        while(!ok){
            id_elev=Citeste_id();
            for(Elev e : listaSali.get(id_sala_root).get_elevi()){
                if(e.get_id()==id_elev){
                    ok=true;
                    break;
                }
            }
            if(!ok) System.out.println("Nu exista un elev cu acest id in prima sala! Dati un alt ID!");

        }
        listaSali.get(id_sala_dest).get_elevi().add(listaSali.get(id_sala_root).get_elevi().get(id_elev));
        listaSali.get(id_sala_root).get_elevi().remove(id_elev);

    }

    private static int Citeste_id(){
        System.out.println("Dati ID: ");
        Scanner sc= new Scanner(System.in);
        boolean ok=false;
        int id=-1;
        while(!ok) {
            try {
                id = sc.nextInt();
                if(id<0) throw new Exception();
                ok=true;
            }catch (Exception e){
                System.out.println("ID invalid! Dati alt ID:");
            }
        }
        return id;
    }
    private static void Cauta_elev_id(ArrayList<Elev> listaElevii) {
        int id=Citeste_id();
        for(Elev e :listaElevii){
            if(e.get_id()==id){
                System.out.println(e);
                return;
            }
        }
        System.out.println("Nu exista un elev cu acest ID!");
    }

    private static boolean is_id_sala_ok(int id,ArrayList<Sala> listaSali){
        boolean ok=false;
        for(Sala s :listaSali){
            if(id==s.get_id()) {
                ok=true;
                break;
            }
        }
        return ok;
    }
    private static void Afiseaza_elevi_sala(ArrayList<Sala> listaSali) {
        if(listaSali.isEmpty())
        {
            System.out.println("Nu exista sali!");
            return;}
        System.out.println("Din ce sala doresti sa afisezi elevii?(ID)");
        boolean done=false;
        int id=-1;
        while(!done) {

            try {
                Scanner sc = new Scanner(System.in);
                id=sc.nextInt();
                if(is_id_sala_ok(id,listaSali)){
                  done=true;
                }
            } catch (Exception e) {
                System.out.println("ID invalid!");
            }
        }
        System.out.println(listaSali.get(id).toString());
    }

    private static void Afisare_sali(ArrayList<Sala> listaSali) {
        for(Sala s : listaSali){
            System.out.println(s.toString());
        }
    }

    private static void Adauga_elev_sala(ArrayList<Elev> listaElevii, ArrayList<Sala> listaSali) {
        System.out.println("Doriti sa adaugati un elev nou sau unul existent? (N/E)");
        boolean gata_elev=false;
        Elev elev_target=new Elev();
        while(!gata_elev) {
            System.out.println("...");
            Scanner sc=new Scanner(System.in);
            String tip_elev=sc.nextLine();
            if (tip_elev.equals("N")) {
                elev_target = Citeste_elev();
                listaElevii.add(elev_target);
                gata_elev=true;
            } else if (tip_elev.equals("E")) {
                System.out.println("Ce elevi doresti sa adaugi?(id elev)");
                boolean id_ok = false;
                while (!id_ok) {
                    try {
                        System.out.println("Dati id-ul elevului dorit: ");
                        int id_cautat = sc.nextInt();
                        for (Elev e : listaElevii) {
                            if (e.get_id() == id_cautat) {
                                elev_target = e;
                                id_ok = true;
                            }
                        }
                        if(!id_ok)throw new Exception("Nu exista acest elev");

                    } catch (Exception e) {
                        System.out.println("ID nu este valid sau nu exista un elev cu acest ID!");
                        return;
                    }

                }

                gata_elev=true;
            } else {
                System.out.println("Selectie invalida!");

            }
        }
        System.out.println("In ce sala doresti sa adaugi elevul?");
        Sala sala_target= new Sala();
        boolean gata_sala=false;
        int id_sala = -1;
        while(!gata_sala){
            System.out.println("...");
            Scanner sc= new Scanner(System.in);
            boolean id_sala_ok=false;
            while(!id_sala_ok)
            {
                try{
                    id_sala=sc.nextInt();
                    id_sala_ok=true;
                } catch (Exception e) {
                    System.out.println("Nu este un id valid");
                }
            }

            for(Sala s : listaSali){

                if(s.get_id()==id_sala)
                {
                    sala_target=s;
                    gata_sala=true;
                }

            }
            if(!gata_sala)
                System.out.println("Nu exista sala cu acest id!");
        }
        for(Elev e : sala_target.get_elevi())
        {
            if(e.get_id()== elev_target.get_id())
            {
                System.out.println("Elevul exista deja in aceasta sala!");
                return;
            }
        }
        if(sala_target.get_capacity()>sala_target.get_elevi().size())
            sala_target.add_elev(elev_target);
        else System.out.println("Nu mai este loc in aceasta sala!");
    }

    private static void Adauga_sala(ArrayList<Sala> listaSali) {
        listaSali.add(Citeste_sala());
    }

    private static Sala Citeste_sala() {
        Sala sala_nou=new Sala();
        boolean gata=false;

        while(!gata){
            boolean nume_ok=false;
            boolean capacitate_ok=false;
            while(!nume_ok){

                try{
                    String nume_nou=Citeste_nume();
                    sala_nou.set_name(nume_nou);
                    nume_ok=true;
                } catch (Exception e) {
                    System.out.println("Nume invalid! Dati alt nume!");
                }
            }
            while(!capacitate_ok){
                try{
                    int capacitate_nou=Citeste_capacitate();
                    sala_nou.set_capacity(capacitate_nou);
                    capacitate_ok=true;
                } catch (Exception e) {
                    System.out.println("Capacitate invalida! Dati alta capacitate!");
                }
            }
            gata=true;

        }
        return sala_nou;
    }

    private static int Citeste_capacitate() {
        System.out.println("Dati capacitatea : ");
        Scanner sc= new Scanner(System.in);
        return sc.nextInt();
    }

    private static String Citeste_nume(){
        System.out.println("Dati numele : ");
        Scanner sc= new Scanner(System.in);
        return sc.nextLine();
    }

    private static int Citeste_varsta() {
        System.out.println("Dati varsta elevului: ");
        Scanner sc= new Scanner(System.in);
        return sc.nextInt();
    }

    private static Double Citeste_medie() {
        System.out.println("Dati media elevului: ");
        Scanner sc= new Scanner(System.in);
        return sc.nextDouble();
    }

    private static Elev Citeste_elev(){
        Elev elev_nou=new Elev();
        boolean gata=false;
        while(!gata){
            boolean nume_ok=false;
            boolean varsta_ok=false;
            boolean medie_ok=false;
            while(!nume_ok){
                try{
                    String nume_nou=Citeste_nume();
                    elev_nou.set_nume(nume_nou);
                    nume_ok=true;

                } catch (Exception e) {
                    System.out.println("Nume invalid! Dati alt nume!");
                }
            }
            while(!varsta_ok) {
                try {
                    int varsta_nou = Citeste_varsta();
                    elev_nou.set_varsta(varsta_nou);
                    varsta_ok = true;

                } catch (Exception e) {
                    System.out.println("Varsta invalida! Dati alta varsta!");
                }
            }
            while(!medie_ok) {
                try {
                    Double medie_nou = Citeste_medie();
                    elev_nou.set_medie(medie_nou);
                    medie_ok = true;
                } catch (Exception e) {
                    System.out.println("Medie invalida! Dati alta medie!");
                }
            }
            gata=true;

        }
        return elev_nou;
    }

    private static void Adauga_elev(ArrayList<Elev> totiElevii) {
        totiElevii.add(Citeste_elev());
    }

    private static void Afisare_elevi(ArrayList<Elev> elevi){
        for(Elev elev: elevi){
            System.out.println(elev.toString());
        }
    }

}

