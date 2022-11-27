

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;

public interface ChatRoom extends Remote {
    //get chatroom
    String get() throws RemoteException;

    //post to a chatroom
    void post(String Post) throws RemoteException;
    
    // Registers client
    void register(ChatRoom_interface Client) throws RemoteException;
}