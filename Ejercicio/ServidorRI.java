import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorRI extends Remote {
    void registerClient(String clientId) throws RemoteException;
    void depositDonation(String clientId, double amount) throws RemoteException;
    double getTotalDonated() throws RemoteException;
    String getDonorsList() throws RemoteException;
}
