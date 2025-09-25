package damM0486_2526_UF1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex22 {
	
	public static void main(String args[]) {
	Scanner sc = new Scanner(System.in);
	
	boolean terminar = false;
	
	String file = "text.txt";
	File f = new File(file);
	ArrayList <String> cadenes = new ArrayList<>();
	
	while (!terminar) {
		try {
			System.out.println("introdueix un text i prem la tecla Enter");			
			String entradaUsuari = sc.nextLine();
			
			if (entradaUsuari.equals("quit")) {
				
				FileOutputStream fos = new FileOutputStream(f);
				
				for (String c : cadenes) {
					fos.write(c.getBytes());
					fos.write(("\n").getBytes());
					}
					fos.close();
					terminar = true;
					
				} else {
					
					cadenes.add(entradaUsuari);

				}
			} catch (Exception e) {
				
			}
		}
		
		try {
			FileInputStream fis = new FileInputStream(f);
			
			int b=0;
			while (b!=-1){
				b = fis.read();
				System.out.print((char)b);
				
			}
		} catch (Exception e) {

		}
		
		
		
	}
}
