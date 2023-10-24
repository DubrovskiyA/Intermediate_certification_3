package Props;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyProvider {
    private static PropertyProvider instance;
    private Properties properties;

    public PropertyProvider() {
    }
    public static PropertyProvider getInstance(){
        if (instance==null){
            instance=new PropertyProvider();
            instance.loadProperties();
            }
        return instance;
    }
    private void loadProperties(){
        properties=new Properties();
        String env=System.getProperty("env","test");
        try {
            properties.load(new FileReader("src/test/resources/" + env + ".properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Properties getProps(){
        return properties;
    }
}
