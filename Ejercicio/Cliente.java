import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {
    @SuppressWarnings("null")
    public static void main(String[] args) {
        try {
            try (Scanner scanner = new Scanner(System.in)) {
                ServidorRI replica = (ServidorRI) Naming.lookup("rmi://localhost/Replica1");
                ServidorRI replica1 = (ServidorRI) Naming.lookup("rmi://localhost/Replica2");
                ServidorRI Seleccionado=null;
                while (true) {
                    System.out.println("Seleccione una opción:");
                    System.out.println("1. Registrar cliente");
                    System.out.println("2. Donar");
                    System.out.println("3. Obtener total donado");
                    System.out.println("4. Obtener lista de donantes");
                    System.out.println("5. Obtener las donaciones de un usuario");
                    System.out.println("6. Ver si el cliente pertenece al servidor");
                    System.out.println("7. Subtotal del servidor");
                    System.out.println("8. Salir");
                    System.out.print("Opción: ");
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    switch (opcion) {
                        case 1:
                            System.out.println("Seleccione el servidor donde registrarse");
                            System.out.println("1. Servidor Replica1");
                            System.out.println("2. Servidor Replica2");
                            System.out.print("Opción: ");
                            int servidor = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Ingrese el ID del cliente: ");
                            String clientId1 = scanner.nextLine();
                        
                            if (servidor == 1 && !replica1.EstaRegistrado(clientId1)) {
                                replica.Registrar(clientId1);
                            } else if (servidor == 2 && !replica.EstaRegistrado(clientId1)){
                                replica1.Registrar(clientId1);
                            } else {
                                System.out.println("Opción de servidor inválida o el cliente ya esta registrado en otro sevidor");
                                break;
                            }
                        break;
                        case 2:
                            System.out.print("Ingrese el ID del cliente: ");
                            String clientId2 = scanner.nextLine();
                            System.out.print("Ingrese la cantidad a donar: ");
                            double cantidad = scanner.nextDouble();


                            if (replica.EstaRegistrado(clientId2)) {
                                replica.Donar(clientId2, cantidad);
                            } else if (replica1.EstaRegistrado(clientId2)){
                                replica1.Donar(clientId2, cantidad);
                            }
                            break;
                        case 3:
                            System.out.print("Ingrese el ID del cliente: ");
                            String clientId3 = scanner.nextLine();
                            double totalDonado = 0.0;
                            if ((replica.EstaRegistrado(clientId3) && replica.getTotalDonado() > 0) ||
                                (replica1.EstaRegistrado(clientId3) && replica1.getTotalDonado() > 0)) {
                                totalDonado = replica.getTotalDonado() + replica1.getTotalDonado();
                                System.out.println("Total donado: " + totalDonado);
                            } else {
                                System.out.println("El cliente no está registrado en al menos una réplica o no ha donado en ninguna réplica.");
                            }
                            break;
                        
                        case 4:
                            System.out.print("Ingrese el ID del cliente: ");
                            String clientId4 = scanner.nextLine();
                            String donantes = "";
                            
                            if ((replica.EstaRegistrado(clientId4) && replica.getTotalDonado() > 0) ||
                                (replica1.EstaRegistrado(clientId4) && replica1.getTotalDonado() > 0)) {
                                donantes = replica.getDonantes() + replica1.getDonantes();
                                System.out.println("Lista de donantes:\n" + donantes);
                            } else {
                                System.out.println("El cliente no está registrado en al menos una réplica o no ha donado en ninguna réplica.");
                            }
                            break;
                        
                        case 5:
                            System.out.print("Ingrese el ID del cliente: ");
                            String cliente = scanner.nextLine();
                            String donadoCliente;
                            if (replica.EstaRegistrado(cliente)) {
                                donadoCliente = replica.getDonacionesCliente(cliente);
                            } else if (replica1.EstaRegistrado(cliente)) {
                                donadoCliente = replica1.getDonacionesCliente(cliente);
                            } else {
                                donadoCliente = "Error: Servidor desconocida.";
                            }
                            System.out.println(donadoCliente);
                            break;
                        case 6:
                            System.out.print("Ingrese el ID del cliente: ");
                            String cliente6 = scanner.nextLine();
                            System.out.print("Ingrese el servidor: ");
                            String serv= scanner.nextLine();
                            String mensaje;
                            if(serv.equals("Replica1")){
                                if (replica.EstaRegistrado(cliente6)) {
                                    mensaje = "El cliente está registrado en la Replica1.";
                                } else {
                                    mensaje = "El cliente no está registrado en Replica1.";
                                }
                            }
                            else if(serv.equals("Replica2")){
                                if (replica1.EstaRegistrado(cliente6)) {
                                    mensaje = "El cliente está registrado en la Replica2.";
                                } else {
                                    mensaje = "El cliente no está registrado en replica2.";
                                }
                            }
                            else{
                                mensaje = "No existe el servidor";
                            }
                            System.out.println(mensaje);
                            break;
                        case 7:
                            System.out.print("Ingrese el ID del cliente: ");
                            String client = scanner.nextLine();
                            System.out.print("Ingrese el servidor: ");
                            String serv1= scanner.nextLine();
                            double sub = 0.0;
                            if ((replica.EstaRegistrado(client) && replica.getTotalDonado() > 0) ||
                                (replica1.EstaRegistrado(client) && replica1.getTotalDonado() > 0)) {
                                    if(serv1.equals("Replica1"))
                                        sub = replica.getTotalDonado();
                                    else 
                                    sub = replica1.getTotalDonado();
                                System.out.println("Total donado: " + sub);
                            } else {
                                System.out.println("El cliente no está registrado en al menos una réplica o no ha donado en ninguna réplica.");
                            }
                            break;

                        case 8:
                            System.out.println("Saliendo...");
                            System.exit(0);
                        default:
                            System.out.println("Opción inválida.");
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
