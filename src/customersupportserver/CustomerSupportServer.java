package customersupportserver;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Tay Fen Yee A0138708X
 */

public interface CustomerSupportServer extends Remote{
    
    //input : name (String)     
    //output: ID of the customer
    public int getCustomerID(String name) throws RemoteException, FileNotFoundException;
    
    //input : ID of the customer     
    //output: request IDs (ArrayList<Integer>) 
    public ArrayList<Integer> getRequestIDs(int customerID) throws RemoteException, FileNotFoundException;
    
    //input : request ID (int)     
    //output: request description (String) 
    public String getRequestDesc(int requestID) throws RemoteException;
}
