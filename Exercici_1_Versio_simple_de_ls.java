package damM0486_2526_UF1;

import java.io.File;

public class Exercici_1_Versio_simple_de_ls {

	public static void main(String[] args) {

		System.out.println("Tinc un parametre: ");
		
		//String ruta = "C:\\Users\\Bilal Namir\\Desktop\\CFGS_DAM1\\ESCRIPTORII\\javadoc_dissenyModular_BilalNamir";
		String ruta = args[0];
		File path = new File(ruta);
		
		File[] files = path.listFiles();
		
		for (File file : files) {
			String fileInfo="";
			if(file.canRead() && file.isDirectory()) {
				
				fileInfo += "dr";
				
				if (file.canWrite()) {
					fileInfo += "w";
				} else {
					fileInfo += "-";
				}
				
				if (file.canExecute()) {
					fileInfo += "x";
				} else {
					fileInfo += "-";
				}
				
			}else if(file.canRead() && file.isFile()) {
				
				fileInfo += "-r";
				
				if (file.canWrite()) {
					fileInfo += "w";
				} else {
					fileInfo += "-";
				}
				
				if (file.canExecute()) {
					fileInfo += "x";
				} else {
					fileInfo += "-";
				}
				
			}
			
			System.out.println(fileInfo +"  "+ file.getName());
			
		}
		
	}

}
