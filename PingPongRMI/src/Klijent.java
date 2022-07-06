import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Klijent {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1234);
			Server server = (Server) registry.lookup("pong");
			
			BufferedReader tastatura = new BufferedReader(new InputStreamReader(System.in));
			
			boolean kraj = false;
			while(!kraj) {
				System.out.println("Unesite poruku: ");
				String poruka = tastatura.readLine();
				String odgovor = server.komunikacija(poruka);
				System.out.println("Server odgovorio: " + odgovor);
				if(poruka.equalsIgnoreCase("kraj"))
					kraj = true;
			}
		} catch (IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
