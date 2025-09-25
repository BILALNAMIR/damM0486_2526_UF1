package damM0486_2526_UF1;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Ex23 {

	public static void main(String[] args) {
		
		try {
			String file = "textEx23.txt";
			File f = new File(file);
			FileOutputStream fos = new FileOutputStream(f);
			DataOutputStream fosdat = new DataOutputStream(fos);
			Random rnd = new Random();
			int last = 0;
			
			
			for (int i = 0; i < 10; i++) {
				String lletres = "";
				int numRandom =  (int)(Math.random() * 3) + 1;
				
				numRandom += last;
				last = numRandom;
				
				for (int j = 0; j < 3; j++) {
					lletres += (char)((int)(Math.random() * (90 - 65 + 1)) + 65);
				}
				
				System.out.println(numRandom+"  "+lletres);
				
					fosdat.writeInt(numRandom);
					fosdat.writeChars(lletres);

			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
			
			
			
		
		
	}

}
