package damM0486_2526_UF1;

import java.io.*;
import java.util.*;

//Classe usuari 
class Usuari implements Serializable {
    String nom;
    String pass;
    int punts;
    boolean admin;

    Usuari(String n, String p, boolean a ) {
        nom = n;
        pass = p;
        punts = 0;
        admin = a;
        
    }
}

public class JocPenjatF1 {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Usuari> llista = new ArrayList<>();
    static File fitxerUsuaris = new File("usuaris.dat");
    static File fitxerParaules = new File("paraules.txt");

    public static void main(String[] args) {
        carregarUsuaris();
        crearFitxerParaulesSiNoExisteix();

        while (true) {
            System.out.println("\n=== JOC PENJAT ===");
            System.out.println("1 - Login");
            System.out.println("2 - Registrar");
            System.out.println("0 - Sortir");
            System.out.print("Opcio: ");
            String op = sc.nextLine();

            if (op.equals("1")) login();
            else if (op.equals("2")) registrar();
            else if (op.equals("0")) {
                System.out.println("Adeu!");
                break;
            } else System.out.println("Opcio no valida");
        }
    }

    // Carregar usuaris del fitxer
    static void carregarUsuaris() {
        if (!fitxerUsuaris.exists()) {
            // crear admin per defecte
            llista.add(new Usuari("admin", "admin", true));
            guardarUsuaris();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fitxerUsuaris))) {
            llista = (ArrayList<Usuari>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No puc llegir usuaris. Creo admin.");
            llista = new ArrayList<>();
            llista.add(new Usuari("admin", "admin", true));
            guardarUsuaris();
        }
    }

    //Guardar usuaris al fitxer
    static void guardarUsuaris() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fitxerUsuaris))) {
            oos.writeObject(llista);
        } catch (Exception e) {
            System.out.println("No puc guardar usuaris.");
        }
    }

    //crear fitxer paraules si no existeix
    static void crearFitxerParaulesSiNoExisteix() {
        try {
            if (!fitxerParaules.exists()) {
                fitxerParaules.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error fitxer paraules.");
        }
    }

    // afegir paraula al fitxer 
    static void afegirParaulaFitxer(String p) {
    	
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerParaules, true))) {
            bw.write(p.toLowerCase());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("No puc afegir paraula.");
        }
        
    }

    //carrega totes les paraules 
    static ArrayList<String> carregarParaules() {
    	
        ArrayList<String> l = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fitxerParaules))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                linia = linia.trim();
                if (!linia.isEmpty()) l.add(linia.toLowerCase());
            }
        } catch (IOException e) {
      
        }
         return l;
    }

    // registrar un nou usuari
    static void registrar() {
        System.out.print("Nom usuari: ");
        String nom = sc.nextLine();
        for (Usuari u : llista) {
            if (u.nom.equals(nom)) {
                System.out.println("Ja existeix aquest nom.");
                return;
            }
        }
        System.out.print("Contrasenya: ");
        String pass = sc.nextLine();
        llista.add(new Usuari(nom, pass, true));
        guardarUsuaris();
        System.out.println("Usuari creat!");
    }

    // login
    static void login() {
        System.out.print("Nom usuari: ");
        String nom = sc.nextLine();
        System.out.print("Contrasenya: ");
        String pass = sc.nextLine();

        for (Usuari u : llista) {
            if (u.nom.equals(nom) && u.pass.equals(pass)) {
                System.out.println("Hola " + u.nom + "! Tens " + u.punts + " punts.");
                menuUsuari(u);
                return;
            }
            
        }
        
        System.out.println("Error login.");
        
        
    }

    // menu per jugar i per admin afegir paraula
    
    static void menuUsuari(Usuari u) {
        while (true) {
        	
            System.out.println("\n--- MENU ---");
            
            if (u.admin) System.out.println("1 - Afegir paraula (admin)");
            System.out.println("2 -Jugar");
            System.out.println("3 -Veure punts");
            System.out.println("0 -Sortir");
            System.out.print("Opcio: ");
            String op = sc.nextLine();
            if (op.equals("1") && u.admin) {
                menuAfegirParaula();
            } else if (op.equals("2")) {
                jugar(u);
            } else if (op.equals("3")) {
                System.out.println("Tens " + u.punts + " punts.");
            } else if (op.equals("0")) {
                guardarUsuaris();
                break;
            } else System.out.println("Opcio no valida.");
        }
    }

    // menu per afegir paraula (admin)
    
    
    static void menuAfegirParaula(){
    	
        System.out.println("\nParaules actuals:");
        ArrayList<String> l = carregarParaules();
        if (l.isEmpty()) System.out.println("Cap paraula.");
        else{
            for (String p : l) System.out.println(" - " + p);
        }
        System.out.print("Escriu nova paraula (buit per sortir): ");
        String nova = sc.nextLine().trim();
        if (!nova.isEmpty()) {
            afegirParaulaFitxer(nova);
            System.out.println("Paraula afegida: " +nova);
        } else {
            System.out.println("Torno.");
        }
    }

    //paraula aleatoria de fitxer o "ordinador" si no hi ha cap pruala guardada
    static void jugar(Usuari u) {
        ArrayList<String> l = carregarParaules();
        String paraula;
        if (l.isEmpty()) paraula = "ordinador";
        else{
            Random r = new Random();
            paraula = l.get(r.nextInt(l.size()));
        }

        char[] vista = new char[paraula.length()];
        Arrays.fill(vista, '_');
        int intents = 6;

        while (intents > 0) {
            System.out.println("\nParaula: " + String.valueOf(vista));
            System.out.println("Intents: " + intents);
            System.out.print("Lletra: ");
            String entrada = sc.nextLine();
            if (entrada.isEmpty()) {
                System.out.println("Escriu una lletra.");
                continue;
            }
            char lletra = Character.toLowerCase(entrada.charAt(0));

            boolean encert = false;
            for (int i = 0; i < paraula.length(); i++) {
                if (paraula.charAt(i) == lletra) {
                    vista[i] = lletra;
                    encert = true;
                }
            }

            if(!encert) intents--;
            if(String.valueOf(vista).equals(paraula)) {
                System.out.println("Has guanyat!");
                u.punts += 10;
                guardarUsuaris();
                return;
            }
        }

        System.out.println("Has perdut. La paraula era: "+paraula);
    }
}
