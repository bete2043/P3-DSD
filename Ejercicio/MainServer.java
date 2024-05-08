import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MainServer {
    public static void main(String[] args) {
        try {
            ServerReplica replica1 = new ServerReplica();
            ServerReplica replica2 = new ServerReplica();

            LocateRegistry.createRegistry(1099);

            Naming.rebind("Replica1", replica1);
            Naming.rebind("Replica2", replica2);

            System.out.println("Servidores Replicas lanzados...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
