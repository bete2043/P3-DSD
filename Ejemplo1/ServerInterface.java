import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    void registerClient(String clientId) throws RemoteException;
    void depositDonation(String clientId, double amount) throws RemoteException;
    double getTotalDonated(String clientId) throws RemoteException;
    String getDonorsList(String clientId) throws RemoteException;
}
