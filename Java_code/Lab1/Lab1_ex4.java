package Java_code.Lab1;

import java.util.Scanner;

import static java.lang.Math.*;

public class Lab1_ex4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduceti un numar: ");
        float nr = sc.nextFloat();

        System.out.println("Sinusul numarului este: " + sin(nr));

        System.out.println("Cosinusul numarului este: " + cos(nr));

        System.out.println("Radicalul  numarului este: " + sqrt(nr));

    }
}
