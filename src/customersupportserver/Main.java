package customersupportserver;

import java.rmi.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author Tay Fen Yee A0138708X
 */

public class Main {
    
    static final String FACTORY = "java.naming.factory.initial";
    static final String FACTORY_NAME = "com.sun.jndi.cosnaming.CNCtxFactory";
    static final String PROVIDER = "java.naming.provider.url";
    static String providerUrl = "iiop://";
    
    public static void main(String args[]) {
        try {
            //Make iiop URL
            Main.providerUrl = Main.providerUrl + args[0];
            
            //Create initial context
            Properties props = new Properties();
            props.put(Main.FACTORY, Main.FACTORY_NAME);
            props.put(Main.PROVIDER, Main.providerUrl);
            InitialContext ic = new InitialContext(props);
            
            //Obtain a reference to the Servant Object
            CustomerSupportServer customerSupportServer = (CustomerSupportServer) PortableRemoteObject.narrow(ic.lookup("CustomerSupportServer"), CustomerSupportServer.class);
            
            Scanner sc = new Scanner(System.in);
            int custID = 0;
            String reqDesc = "";
            String custName = "";
            ArrayList<Integer> requestID;
            
            while (!custName.equalsIgnoreCase("Q")) {
                System.out.print("Enter customer name ('*' for all request details, 'Q' or 'q' to exit): ");
                custName = sc.nextLine().trim();

            // Invoke remote method and display result
            
                if (custName.equalsIgnoreCase("Q")) {
                    System.out.println("\nTerminated. Exciting...");
                    System.exit(0);
                } else if (custName.equalsIgnoreCase("*")) {
                    requestID = customerSupportServer.getRequestIDs(0);
                    for (int i = 0; i < requestID.size(); i++) {
                        reqDesc = customerSupportServer.getRequestDesc(requestID.get(i));
                        System.out.println("Request ID: " + requestID.get(i) + " *** " + reqDesc);
                    }
                } else {
                    custID = customerSupportServer.getCustomerID(custName);
                    if (custID == 0) {
                        System.out.println("\nIncorrect customer name");
                    } else {
                        requestID = customerSupportServer.getRequestIDs(custID);
                        if (!requestID.isEmpty()) {
                            for (int i = 0; i < requestID.size(); i++) {
                                reqDesc = customerSupportServer.getRequestDesc(requestID.get(i));
                                System.out.println("Request ID: " + requestID.get(i) + " *** " + reqDesc);
                            }
                        } else {
                            System.out.println("No request found");
                        }
                    }
                }
                System.out.print("\n");
            } 
            
            System.exit(0);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
