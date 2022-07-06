import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote{
	public String komunikacija(String poruka) throws RemoteException;
}
