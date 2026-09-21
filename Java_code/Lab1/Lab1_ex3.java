import java.util.Scanner;

public class Lab1_ex3
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduceti un numar de 4 cifre: ");
		boolean ok=false;
		int nr=0;
		while( !ok)
		{
		    nr= sc.nextInt();
		    if ( nr>999&&nr<10000)
		        {
		            ok=true;
		        }
		        else{
		            System.out.println("Numarul nu este de 4 cifre dati altul!");
		        }
		}
		boolean gata=false;
        while(!gata)
        { 
            System.out.println("/t/t-----Meniu-----");
            System.out.println("(1) Paritate ultima cifra; ");
            System.out.println("(2) Paritate prima cifra; ");
            System.out.println("(3) Suma cifrelor;");
            System.out.println("(0) EXIT;");
            
            int selectie=sc.nextInt();
            
            switch (selectie){
            
            case 0:
                gata=true;
                break;
            
            case 1:
                System.out.println("Ultima cifra este: ");
                if(nr%2==0)
                    System.out.print("Para");
                else 
                    System.out.print("Impara ");
                break;
            
            case 2:
                System.out.println("Prima cifra este: ");
                if((nr/1000)%2==0)
                    System.out.print("Para");
                else 
                    System.out.print("Impara ");
                break;
            case 3:
                System.out.println("Suma cifrelor este: ");
                int copie=nr;
                int suma=0;
                while(copie!=0)
                {
                    suma+=copie%10;
                    copie/=10;
                }
                System.out.print(suma);
                break;
            default:
                System.out.println("Selectie gresita!");
                break;
            }
        } 
		
	}
}
