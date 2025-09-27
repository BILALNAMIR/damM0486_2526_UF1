package damM0486_2526_UF1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Exercici_41 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introdueix el nom usuari");
		String nomUsuari = sc.next();
		System.out.println("Introdueix la contrasenya");
		String contrasenya = sc.next();
		
		File userFile = new File(nomUsuari+".usr");
		
		if (userFile.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile));
				User userObject = (User) ois.readObject();
				if(userObject.getNom().equals(nomUsuari) && userObject.comprovarPasswrd(contrasenya)) {
					System.out.println("Acces correcte al sistema");
				}else {
					System.out.println("Acces no concedit: La contrasenya no es correcta");
				}
				ois.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			System.out.println("No s'ha trobat usauri!\n\nVols registrar-te?\n1.si\n2.no");
			int option = sc.nextInt();
			if (option==1) {
				User registUsuari = new User(nomUsuari,contrasenya);
				try {
					
					ObjectOutputStream oosFil = new ObjectOutputStream(new DataOutputStream(new FileOutputStream(userFile))); 
					oosFil.writeObject(registUsuari);
					oosFil.close();
					System.out.println(nomUsuari +" estas registrar correctament");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else {
				System.out.println("t'esperem en una altre occasio adeuuuuuu");

			}
		}
	}

}

class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String password;
	
	public User(String nom, String passwrd) {
		this.nom = nom;
		this.password=passwrd;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean comprovarPasswrd(String Passwrd) {
		return this.password.equals(Passwrd);
	}
	
	
	
	
	
}