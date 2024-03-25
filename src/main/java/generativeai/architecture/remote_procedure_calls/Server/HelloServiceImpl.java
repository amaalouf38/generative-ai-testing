package generativeai.architecture.remote_procedure_calls.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import generativeai.architecture.remote_procedure_calls.HelloService;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    protected HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name + "!";
    }
}
