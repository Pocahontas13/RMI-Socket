import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {

	protected ServerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1234);
			Server server = new ServerImpl();
			registry.rebind("pong", server);
			System.out.println("server je pokrenut");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String komunikacija(String poruka) throws RemoteException {
		if(poruka.equalsIgnoreCase("ping"))
			return "pong";
		else if(poruka.equalsIgnoreCase("kraj"))
			return "drago mi je sto smo pricali";
		else 
			return "nisam razumeo poruku";
	}

}
