package kz.app.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Дамир
 */
public class PropUtil {
    private static final Properties props;
    
    static {
        props = new Properties();
        try {
            props.load(PropUtil.class.getResourceAsStream("/app.properties"));
        } catch (IOException ex) {
            Logger.getLogger(PropUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized static Properties getProps() {
        return props;
    }
}
