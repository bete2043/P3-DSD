import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerReplica extends UnicastRemoteObject implements ServidorRI {
    private Map<String, Double> donaciones = new HashMap<>();
    private Set<String> clientesRegistrados = new HashSet<>();

    public ServerReplica() throws RemoteException {
        super();
    }

    @Override
    public synchronized void Registrar(String clientId) throws RemoteException{
        if (!clientesRegistrados.contains(clientId)) {
            clientesRegistrados.add(clientId);
            System.out.println("Cliente " + clientId + " registrado en el servidor.");
        } else {
            System.out.println("El cliente " + clientId + " ya está registrado en el servidor.");
        }
    }

    @Override
    public synchronized void Donar(String clientId, double cantidad) throws RemoteException{
        if (clientesRegistrados.contains(clientId) && cantidad>0) {
            donaciones.put(clientId, donaciones.getOrDefault(clientId, 0.0) + cantidad);
            System.out.println("Depósito de " + cantidad + " realizado por el cliente " + clientId + ".");
        } else {
            System.out.println("El cliente " + clientId + " no está registrado en el servidor.");
        }
    }

    @Override
    public synchronized double getTotalDonado() throws RemoteException{
        double Donado = 0.0;
        for (double cantidad : donaciones.values()) {
            Donado += cantidad;
        }
        return Donado;
    }

    @Override
    public synchronized String getDonantes() throws RemoteException{
        StringBuilder donantes = new StringBuilder("Lista de donantes en este servidor:\n");
        for (String clientId : donaciones.keySet()) {
            donantes.append(clientId).append("\n");
        }
        return donantes.toString();
    }

    @Override
    public synchronized String getDonacionesCliente(String clientId) throws RemoteException{

        StringBuilder donacionesCliente = new StringBuilder("Donaciones del cliente " + clientId + ":\n");
        if (donaciones.containsKey(clientId)) {
            donacionesCliente.append(donaciones.get(clientId)).append("\n");
        } else {
            donacionesCliente.append("Este cliente no tiene donaciones registradas.\n");
        }
        return donacionesCliente.toString();

    }

    @Override
    public synchronized boolean EstaRegistrado(String clientId) throws RemoteException {
        return clientesRegistrados.contains(clientId);
    }

}
