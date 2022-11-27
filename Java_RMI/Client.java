
import java.io.Console;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Client extends UnicastRemoteObject implements ChatRoom_interface{
    private ChatRoom room;

    public Client(ChatRoom Room) throws RemoteException
    {
        super();
        room = Room;
    }

    public void register(){
        try{
            room.register(this);
            System.out.println("REG");
        }
        catch(Exception e) {System.out.println(e);};
    }

    public void call()
    {
        System.out.println("NEW POSTS");
        try
        {
            String response = room.get();
            System.out.println("messages: " + response);
            }
        catch (Exception e) {};
    }
    public void post(String Text)
    {
        try{
            room.post(Text);
        }
        catch(Exception e) {};
    }
    public synchronized void waiting()
    {
        try{
            wait();
            }
        catch(Exception e) {};
    }
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        Console console = System.console();
        try {

            boolean loop = true;
            String message = "";

            Registry registry = LocateRegistry.getRegistry(host);
            Client client = new Client(((ChatRoom) registry.lookup("Hello")) );
            client.register();
            client.call();


            while (loop) {
                System.out.println("Press 1 to exit");
                message = console.readLine();
                if (message.equals("1")){
                    loop=false;
                }
                client.post(message);
                client.call();
            }

            


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            }
        }
}

