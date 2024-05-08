import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorRI extends Remote {
    void Registrar(String clientId) throws RemoteException;
    void Donar(String clientId, double cantidad) throws RemoteException;
    double getTotalDonado() throws RemoteException;
    String getDonantes() throws RemoteException;
    String getDonacionesCliente(String clientId) throws RemoteException;
    boolean EstaRegistrado(String clientId) throws RemoteException;
}
