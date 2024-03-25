package generativeai.architecture.remote_procedure_calls.Client;

import java.rmi.Naming;
import generativeai.architecture.remote_procedure_calls.HelloService;

public class Client {
    public static void main(String[] args) {
        try {
            HelloService service = (HelloService) Naming.lookup("rmi://localhost/HelloService");
            String response = service.sayHello("World");
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.out.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}