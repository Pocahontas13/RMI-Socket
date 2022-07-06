import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		try {
			ServerSocket server_socket = new ServerSocket(1234);
			while (!Thread.interrupted()) {
				Socket klijent = server_socket.accept();
				Logika logika = new Logika(klijent);
				logika.start();
 			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
