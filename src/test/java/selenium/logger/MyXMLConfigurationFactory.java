package selenium.logger;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.xml.sax.InputSource;

/**
 * Created by alexander.
 */
@Plugin(name = "MyXMLConfigurationFactory", category = "ConfigurationFactory")
@Order(10)
public class MyXMLConfigurationFactory extends ConfigurationFactory {

    /**
     * Valid file extensions for XML files.
     */
    public static final String[] SUFFIXES = new String[]{".xml", "*"};

    /**
     * Return the Configuration.
     *
     * @param source The InputSource.
     * @return The Configuration.
     */
    public Configuration getConfiguration(InputSource source) {
        return new MyXMLConfiguration(source, configFile);
    }

    /**
     * Returns the file suffixes for XML files.
     *
     * @return An array of File extensions.
     */
    public String[] getSupportedTypes() {
        return SUFFIXES;
    }

}
