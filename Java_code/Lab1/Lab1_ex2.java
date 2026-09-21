import java.util.Scanner;
import java.util.Random;
public class Lab1_ex2
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduceti un numar: ");
		int nr1= sc.nextInt();
        System.out.println("Introduceti un al doilea numar: ");
		int nr2= sc.nextInt();

		int suma=nr1+nr2;
		int produs=nr1*nr2;
		int catul=nr1/nr2;
		int restul=nr1%nr2;
		Random rnd= new Random();
		int inmultire_rand1=nr1*rnd.nextInt(100);
		
		int inmultire_rand2=nr2*rnd.nextInt(100);
		
		System.out.println("Suma = " + suma);
		
		System.out.println("Produsul = " + produs);
		
		System.out.println("Catul = " + catul);
		
		System.out.println("Restul = " + restul);
		
		System.out.println("Random inmultire 1 = " + inmultire_rand1);
		
		System.out.println("Random inmultire 2 = " + inmultire_rand2);
		
	}
}
