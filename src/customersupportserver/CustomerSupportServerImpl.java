package customersupportserver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.io.File;               
import java.io.FileNotFoundException; 
import java.util.Collections;
import java.util.Scanner;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author Tay Fen Yee A0138708X
 */

public class CustomerSupportServerImpl extends PortableRemoteObject implements CustomerSupportServer{
    
    public CustomerSupportServerImpl() throws RemoteException{
        super();
    }
    
    @Override
    public int getCustomerID(String name) throws RemoteException, FileNotFoundException {
        int customerID = 0;
        String fileName = "customersupportserver\\customer.txt";
        
        try {
            File file = new File(fileName);
            Scanner inputFile = new Scanner(file); 
            String line;
            String[] arraySplitLine;
            
            while (inputFile.hasNextLine()) {
                line = inputFile.nextLine();
                arraySplitLine = line.split("\\,", -1);
                String compareName = arraySplitLine[1].trim();
                
                if (name.equalsIgnoreCase(compareName)) {
                    customerID = Integer.parseInt(arraySplitLine[0]);
                    break;
                } else {
                    customerID = 0;
                }
            }
            
            inputFile.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + fileName);
            System.exit(0);
        }
        
        if (customerID == 0) {
            System.out.println("getCustomerID(): Name:" + name.toLowerCase() + " ==> Name not found \n");
        } else {
            System.out.println("getCustomerID(): Name:" + name.toLowerCase() + " ==> " + customerID);
        }
        return customerID;
    }

    @Override
    public ArrayList<Integer> getRequestIDs(int customerID) throws RemoteException, FileNotFoundException {
        ArrayList<Integer> requestID = new ArrayList<Integer>();
        String custID = Integer.toString(customerID);
        String fileName = "customersupportserver\\requests.txt";
        
        try {
            File file = new File(fileName);
            Scanner inputFile = new Scanner(file); 
            String line;
            String[] arraySplitLine;
            
            while (inputFile.hasNextLine()) {
                line = inputFile.nextLine();
                arraySplitLine = line.split("\\,", -1);
                String compareID = arraySplitLine[1].trim();
                
                if (custID.equalsIgnoreCase(compareID) || custID.equalsIgnoreCase("0")) {
                    int reqID = Integer.parseInt(arraySplitLine[0]);
                    requestID.add(reqID);
                } 
            }
            
            Collections.sort(requestID);
            inputFile.close();
            if (!requestID.isEmpty()) {
                System.out.println("getRequestIDs(): request list returned \n");
            } else {
                System.out.println("getRequestIDs(): request list returned \n");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + fileName);
            System.exit(0);
        }
        return requestID;
    }

    @Override
    public String getRequestDesc(int requestID) throws RemoteException {
        String requestDesc = "";
        String fileName = "customersupportserver\\requests.txt";
        
        try {
            File file = new File(fileName);
            Scanner inputFile = new Scanner(file); 
            String reqID = Integer.toString(requestID);
            String line;
            String[] arraySplitLine;
            
            while (inputFile.hasNextLine()) {
                line = inputFile.nextLine();
                arraySplitLine = line.split("\\,", -1);
                String compareID = arraySplitLine[0].trim();
                
                if (reqID.equalsIgnoreCase(compareID)) {
                    requestDesc = arraySplitLine[2];
                    System.out.println("getRequestDesc(): ID:" + reqID + " ==> " + requestDesc + "\n");
                    break;
                } 
            }
            
            inputFile.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + fileName);
            System.exit(0);
        }
        return requestDesc;
    }
}
