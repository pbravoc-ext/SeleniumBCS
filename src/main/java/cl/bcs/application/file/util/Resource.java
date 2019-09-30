package cl.bcs.application.file.util;

import java.io.*;
import java.net.URL;
import java.util.Properties;


/**
 * 
 * @author Narveider
 *
 */
public class Resource {

	private static Properties properties;

	static {
		properties = new Properties();
		URL props = Resource.class.getClassLoader().getResource(
				"config.properties");
		if (props == null) {
			props = Resource.class.getResource("/config.properties");
		}

		try {
			properties.load(props.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static String get(String key) {
		return getProperty(key);
	}

}
