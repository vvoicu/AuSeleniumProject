package ecs.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * All run configurations (e.g urls, db, credentials, certain filepaths (e.g.
 * secrets)). <br>
 * This is necessary for having complete control over how we treat these.<br>
 * If we have collections of all the endpoints, switching to environments or
 * using mixed infrastructure is done easily. <br>
 * It is also how we handle how we read data when the application is ran in a
 * container.
 * 
 * @author voicu.turcu
 *
 */
public class ConfigUtils {

	private static Properties prop = new Properties();
	private static InputStream input = null;

	// in case you want to keep track of your keys. there's also a method further down.
	public enum ConfigKeys {
		BASE_URL_GOOGLE, BASE_URL_WIKTIONARY, BASE_URL, BASE_URL_REQRES;
	}

	/**
	 * By default the Tests and the whole application will take its default configs
	 * from: <code>src/test/resources/configs/local-config.properties</code> <br>
	 * This can be altered at Runtime by adding to the maven command: <br>
	 * <code>-DconfigFile=dev</code> if your config file -> <br>
	 * <code>src/test/resources/configs/dev-config.properties</code> <br>
	 * To the method you need to provide one of the keys in the config file. It will
	 * return the value. <br>
	 * If no value will return an empty string. <br>
	 * {@link ConfigUtils}
	 * 
	 * @param propertyKey {@link String}
	 * @return propertyValue {@link String}
	 */
	public static String getProperty(String propertyKey) {
		String result = "";
		String configFile = System.getProperty("configFile") == null ? "local" : System.getProperty("configFile");
		String fullPath = Constants.CONFIG_RESOURCES_PATH + configFile + "-config.properties";
		
		try {
			input = new FileInputStream(fullPath);
			prop.load(input);
			result = prop.getProperty(propertyKey);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String getProperty(ConfigKeys propertyKey) {
		return getProperty(propertyKey.toString());
	}
}
