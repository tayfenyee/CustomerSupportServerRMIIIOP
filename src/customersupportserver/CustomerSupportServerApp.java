package customersupportserver;

import javax.naming.*;
import java.util.Properties;

/**
 *
 * @author Tay Fen Yee A0138708X
 */

public class CustomerSupportServerApp {
    
    static final String FACTORY = "java.naming.factory.initial";
    static final String FACTORY_NAME = "com.sun.jndi.cosnaming.CNCtxFactory";
    static final String PROVIDER = "java.naming.provider.url";
    static final String PROVIDER_URL = "iiop://localhost:900";
    
    public static void main(String[] args) {
        try{
            CustomerSupportServerImpl customerSupportServerImpl = new CustomerSupportServerImpl();
            
            //Create initial context
            Properties props = new Properties();
            props.put(CustomerSupportServerApp.FACTORY, CustomerSupportServerApp.FACTORY_NAME);
            props.put(CustomerSupportServerApp.PROVIDER, CustomerSupportServerApp.PROVIDER_URL);
            InitialContext ic = new InitialContext(props);
            
            //Bind the object to IIOP registry
            ic.rebind("CustomerSupportServer", customerSupportServerImpl);
            System.out.println("CustomerSupportServer Started \n");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
