import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Klijent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			Socket server = new Socket("localhost", 1234);
			out = new PrintWriter(new OutputStreamWriter(server.getOutputStream()), true);
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));

			BufferedReader tastatura = new BufferedReader(new InputStreamReader(System.in));
			boolean kraj = false;

			while (!kraj) {
				String prvaPoruka = in.readLine();
				String drugaPoruka = in.readLine();

				if (drugaPoruka.equalsIgnoreCase("kraj")) {
					System.out.println(prvaPoruka);
					kraj = true;
				} else {
					System.out.println("Rec: " + prvaPoruka);
					System.out.println("Broj zivota: " + drugaPoruka);
					String mojaPoruka = tastatura.readLine();
					out.println(mojaPoruka);
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e2) {
				// TODO: handle exception
			}
		}
	}

}
