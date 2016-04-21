package com.adoa.azportal.util;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 *
 * @author Lance Stine
 */
public class JndiLookups {
    private static JndiLookups jndi;
    private static Properties p;
    private static javax.naming.Context c;
    
    public static JndiLookups getInstance() throws NamingException {
        if (jndi == null) {
            jndi = new JndiLookups();
        }
        return jndi;
    }
    
    private JndiLookups() throws NamingException {
        p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        p.put(Context.URL_PKG_PREFIXES, "jboss.naming:org.jnp.interfaces");
        c = new javax.naming.InitialContext(p);
    }
    
    /**
     * Look up a String value by specifying the jndi name as an argument
     */
    public String lookupStringFromJndi(String jndiName) throws NamingException {    
        String con = (String) c.lookup(jndiName);
        return con;        
    }
    
    /**
     * Look up a Object value by specifying the jndi name as an argument
     */
    public Object lookupObjectFromJndi(String jndiName) throws NamingException {    
        return c.lookup(jndiName);
    }
    
    /**
     * Get an EJB by specifying the interface name.
     * @param name this will add the argument to the JNDI name. i.e. own/" + name + "/local
     * @return
     * @throws javax.naming.NamingException
     */
    public Object lookupLocalInterface(String name) throws NamingException {
        return c.lookup("dhs/" + name + "/local");
    }
}
