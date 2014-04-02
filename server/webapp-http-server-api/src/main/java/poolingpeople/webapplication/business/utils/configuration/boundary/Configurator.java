package poolingpeople.webapplication.business.utils.configuration.boundary;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Configurator {

    private Map<String, String> config;
    private final String notFoundMessage = "There is no value associated with the given key";

    /*
     * --- Will be called via PostConstruct invocation from the bean container" ---
     */
    @SuppressWarnings({"serial" })
    @PostConstruct
    private void loadConfiguration() {
        config = new HashMap<String, String>() {
            {
                put("test", "asdf");
                put("debug", "false");
                put("isLogging", "true");
                put("invalidJson", "Invalid JSON provided, thus deserializing is not possible!");
                put("doesNotExist", "The given Item does not exist");
                put("alreadyExists", "These relation already exists and must be unique");
                put("relationNotFound", "The relation between two objects does not exist");
                
            }
        };
    }

    public String getStringValue(String key) {
        containsKey(key);
        return config.get(key);
    }

    public long getLongValue(String key) {
        containsKey(key);
        return Long.parseLong(config.get(key));
    }

    public int getIntValue(String key) {
        containsKey(key);
        return Integer.parseInt(config.get(key));
    }

    public boolean getBooleanValue(String key) {
        containsKey(key);
        return Boolean.parseBoolean(config.get(key));
    }

    private void containsKey(String key) {
        if (!config.containsKey(key)) {
            throw new RuntimeException(notFoundMessage);
        }
    }
}
