package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    public static Properties configProp =  new Properties();
    private static String bearerToken;
    private static String dataSourcesPath;
    private static String googleFitnessApi;

    public static Properties loadProperties() throws IOException, IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                +"\\src\\test\\java\\resources\\config.properties");
        configProp.load(fis);
        bearerToken = configProp.getProperty("bearerToken");
        dataSourcesPath = configProp.getProperty("dataSourcesPath");
        googleFitnessApi = configProp.getProperty("googleFitnessApi");
        return configProp;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public String getDataSourcesPath() {
        return dataSourcesPath;
    }

    public String getGoogleFitnessApi() {
        return googleFitnessApi;
    }
}
