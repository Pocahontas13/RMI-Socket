import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Logika extends Thread {
	
	PrintWriter out = null;
	BufferedReader in = null;
	List<String> rec = new ArrayList<>();
	
	public Logika (Socket klijent) throws IOException{
		rec.add("Banana");
		rec.add("Jabuka");
		rec.add("Kruska");
		
		out = new PrintWriter(new OutputStreamWriter(klijent.getOutputStream()), true);
		in = new BufferedReader(new InputStreamReader(klijent.getInputStream()));
		
	}
	
	public void run () {
		Random pogodi = new Random();
		int brPokusaja = 10;
		boolean kraj = false;
		System.out.println("Rec za pogadjanje " );
		int zamisljenaRecInd = pogodi.nextInt(rec.size()); //izaberem random rec
		String zamisljenjaRec = rec.get(zamisljenaRecInd); //uzmem tu random rec iz liste
		int brSlova = zamisljenjaRec.length(); //duzina
		
		String sakrivenaRec = "";
		
		for (int i = 0; i < brSlova; i++) {
			//System.out.println("_");
			sakrivenaRec = sakrivenaRec.concat("_"); // string = string + "_"
		}
		//pronajdem broj slova i na osnovu toga ispisem crtice u for petlji
		//System.out.println("Imate " + brPokusaja + " pokusaja");
		out.println(sakrivenaRec);
		out.println(brPokusaja);
		
		
		String poruka;
		try {
			while (!kraj) {
				if (brPokusaja == 0) {
					kraj = true;
					break;
				}
				
				poruka = in.readLine();
			
				if(zamisljenjaRec.toLowerCase().contains(poruka.toLowerCase())) {
					//radi nesto
					for(int i = 0; i < brSlova; i++) {
						if(zamisljenjaRec.toLowerCase().charAt(i) == poruka.toLowerCase().charAt(0)) {
							sakrivenaRec = sakrivenaRec.substring(0, i) + poruka + sakrivenaRec.substring(i + 1);
						}
						
					}
					if(!sakrivenaRec.contains("_")) {
						kraj = true;
						break;
					}
				} else {
					brPokusaja--;
				}
				out.println(sakrivenaRec);
				out.println(brPokusaja);
				
				
				//System.out.println("Igra je zavrsena, nemate vise zivota. Obeseni ste :)");
			}
			
			if(brPokusaja == 0) {
				out.println("Zao mi je niste pogodili rec");
				out.println("kraj");
			} else {
				out.println("pogodili ste zamisljenu rec");
				out.println("kraj");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				out.close();
				in.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}