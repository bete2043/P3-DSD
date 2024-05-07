import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            // Buscar la réplica del servidor
            ServidorRI replica = (ServidorRI) Naming.lookup("rmi://localhost/Replica1");
            ServidorRI replica1 = (ServidorRI) Naming.lookup("rmi://localhost/Replica2");

            // Supongamos que tenemos un cliente con ID "cliente1"
            String clientId = "cliente1";
            String clientId2 = "cliente2";
            String clientId3 = "cliente3";

            // Registrar el cliente en la réplica
            replica.registerClient(clientId);
            replica1.registerClient(clientId2);
            replica1.registerClient(clientId3);
            
            // Hacer un depósito de 100 unidades por parte del cliente registrado
            replica.depositDonation(clientId, 100);
            replica1.depositDonation(clientId2,50);
            replica1.depositDonation(clientId3,70);

            // Obtener el total donado por todos los clientes registrados en esta réplica
            double totalDonated = replica.getTotalDonated() + replica1.getTotalDonated();
            System.out.println("Total donado en esta réplica: " + totalDonated);

            // Obtener la lista de donantes en esta réplica
            String donorsList = replica.getDonorsList() + replica1.getDonorsList();
            System.out.println(donorsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
