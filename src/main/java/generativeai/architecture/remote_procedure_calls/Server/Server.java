package generativeai.architecture.remote_procedure_calls.Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import generativeai.architecture.remote_procedure_calls.HelloService;

public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // RMI registry default port
            HelloService service = new HelloServiceImpl();
            Naming.rebind("rmi://localhost/HelloService", service);
            System.out.println("Server is running...");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}