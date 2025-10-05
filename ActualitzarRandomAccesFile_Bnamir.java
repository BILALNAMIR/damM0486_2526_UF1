package damM0486_2526_UF1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ActualitzarRandomAccesFile_Bnamir {
	
	private static String llegirChar(RandomAccessFile fitxer, int numChars) throws IOException{
		StringBuilder b = new StringBuilder();
		char ch = ' ';
		for (int i = 0; i < numChars;i++) {
			ch = fitxer.readChar();
			if( ch !=  '\0' ) {
				b.append(ch);
			}
		}
		return b.toString();
	} 
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introdueix el numero de index del registre");
		
		int indexRegist = sc.nextInt();
		sc.nextLine();
		
		
		int[] posValorBinary = {40,3,40};
		
		try(RandomAccessFile fitxer = new RandomAccessFile("paisos.dat","rw")) {
			
			int posRegistBinary = (indexRegist-1)*174;
			
			if(fitxer.length() <= posRegistBinary || posRegistBinary<0 ) {

				throw new IOException("Aquest registre no existeix");
			}
			fitxer.seek(posRegistBinary);
			fitxer.readInt();//id
			String nom = llegirChar(fitxer,posValorBinary[0]);
			String codiISO = llegirChar(fitxer, posValorBinary[1]);
			String capital = llegirChar(fitxer, posValorBinary[2]);
			int poblacio = fitxer.readInt();
			
			System.out.println("Registre actual:\n"+"Nom: "+nom+"  "+"Codi ISO: "+codiISO+"  "+"Capital: "+capital+"  "+"Poblacio: "+poblacio+"  ");
			
			System.out.println("Escull el valor que volos modificar.\n1.Nom\n2.Codi ISO\n3.Capital\n4.Poblacio");
			
			int opcioUsuari = sc.nextInt();
			sc.nextLine();
			
			fitxer.seek(posRegistBinary);
			
			if(opcioUsuari == 1) {
				fitxer.readInt();
				System.out.println("Introduix el nou nom");
				nom = sc.nextLine();
				fitxer.writeChars(nom);
				System.out.println("Nom Actualizat correctament");
				
			}else if(opcioUsuari == 2) {
				fitxer.readInt();
				llegirChar(fitxer, posValorBinary[0]);
				System.out.println("Introdueix el nou codi ISO:");
				codiISO = sc.nextLine();
				fitxer.writeChars(codiISO);
				System.out.println("Codi ISO Actualizat correctament");

			}else if(opcioUsuari == 3) {
				fitxer.readInt();
				llegirChar(fitxer, posValorBinary[0]+posValorBinary[1]);
				System.out.println("Introdueix el nou capital:");
				capital = sc.nextLine();
				fitxer.writeChars(capital);
				System.out.println("Capital Actualizat correctament");
				
			}else if(opcioUsuari == 4) {
				fitxer.readInt();
				llegirChar(fitxer, posValorBinary[0]+posValorBinary[1]+posValorBinary[2]);
				System.out.println("Introdueix la nova poblacio:");
				poblacio = sc.nextInt();
				fitxer.writeInt(poblacio);
				System.out.println("Poblacio Actualizat correctament");				
			}else {
				throw new IOException("opcio del menu invalid");
			}
			
		} catch (Exception e) {
			
		}
		
		
	}
	
	

}



