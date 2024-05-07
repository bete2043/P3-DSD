import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerReplica extends UnicastRemoteObject implements ServidorRI {
    private Map<String, Double> donations = new HashMap<>();
    private Set<String> registeredClients = new HashSet<>();

    public ServerReplica() throws RemoteException {
        super();
    }

    @Override
    public synchronized void registerClient(String clientId) throws RemoteException {
        if (!registeredClients.contains(clientId)) {
            registeredClients.add(clientId);
            System.out.println("Cliente " + clientId + " registrado en esta réplica.");
        } else {
            System.out.println("El cliente " + clientId + " ya está registrado en esta réplica.");
        }
    }

    @Override
    public synchronized void depositDonation(String clientId, double amount) throws RemoteException {
        if (registeredClients.contains(clientId)) {
            donations.put(clientId, donations.getOrDefault(clientId, 0.0) + amount);
            System.out.println("Depósito de " + amount + " realizado por el cliente " + clientId + ".");
        } else {
            System.out.println("El cliente " + clientId + " no está registrado en esta réplica. No se puede realizar el depósito.");
        }
    }

    @Override
    public synchronized double getTotalDonated() throws RemoteException {
        double totalDonated = 0.0;
        for (double amount : donations.values()) {
            totalDonated += amount;
        }
        return totalDonated;
    }

    @Override
    public synchronized String getDonorsList() throws RemoteException {
        StringBuilder donorsList = new StringBuilder("Lista de donantes en esta réplica:\n");
        for (String clientId : donations.keySet()) {
            donorsList.append(clientId).append("\n");
        }
        return donorsList.toString();
    }
}
