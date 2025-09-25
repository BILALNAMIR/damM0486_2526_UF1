package damM0486_2526_UF1;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Ex21 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Introdueix el nom del fitxer ficant-li l'extensio de .txt");
		
		String file = sc.nextLine();
		
		System.out.println("Quina litra vol saber la quantitat total d'aquesta lletra en aquest fitxer: ");
		char caracter = sc.next().charAt(0);
		
		File f = new File(file);
		int totalCaracter=0;
		 
		try {
			FileInputStream f1 = new FileInputStream(f);
			int l = 0;
			while(l!=-1) {
				l = f1.read();
				if (l==(int)caracter) {
					totalCaracter++;
				}
				
			}
			f1.close();
		} catch (Exception e) {
			
		}
		System.out.println("El total d'aquest caracter ("+caracter+") es : " + totalCaracter);
		

	}

}
