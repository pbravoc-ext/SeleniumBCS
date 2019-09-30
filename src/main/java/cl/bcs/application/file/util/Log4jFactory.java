/**
 * 
 */
package cl.bcs.application.file.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Narveider
 *
 */
public class Log4jFactory {

	/**
	 * LOG4J_CONFIG_FILE
	 */
	public static final String LOG4J_CONFIG_FILE = "log4j.properties";

	/**
	 * Indicates if log4j has been initialized
	 */
	private static boolean log4jinitialized = false;

	/**
	 * objClase
	 */
	private static Class<?> objClase;

	/**
	 * proteje de instancear la clase
	 */
	public Log4jFactory() {
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static Logger getLogger(Class<?> c) {
		objClase = c;
		if (!log4jinitialized) {
			init();
		}
		return Logger.getLogger(objClase);
	}

	/**
	 * 
	 */
	private static synchronized void init() {
		if (!log4jinitialized) {
			String path = Resource.getProperty("path.file.log4j");
			File file = new File(path);
			if (file.exists() || file.canRead()) {
				log4jinitialized = true;
				Logger log = Logger.getLogger(objClase);
				PropertyConfigurator.configureAndWatch(path);
				log.info("Logger inicializado log4j.properties=[" + path + "]");
			}
		}
	}

	/**
	 * 
	 * @param b
	 */
	public static void setLog4jinitialized(boolean b) {
		log4jinitialized = b;
	}
}
