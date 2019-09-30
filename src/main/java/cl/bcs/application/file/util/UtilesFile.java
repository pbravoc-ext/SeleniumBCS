package cl.bcs.application.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author Narveider
 *
 */
public class UtilesFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private transient String nomFile = null;
	private transient FileOutputStream out = null;
	private transient FileReader stream = null;
	private transient BufferedReader reader = null;
	private transient Properties prop = null;
	private static Logger logger = Logger.getLogger("");

	/**
	 * 
	 * 
	 * @param nomFile
	 */

	public UtilesFile(String nomFile) {
		this.nomFile = nomFile;
	}

	private void close() throws IOException {

		if (out != null) {
			out.close();
			out = null;
		}

		if (stream != null) {
			stream.close();
			stream = null;
		}

		if (reader != null) {
			reader.close();
			reader = null;
		}
	}

	/**
	 * Metodo que abre archivo
	 */
	public void openToWrite() {
		File outputfile = new File(this.nomFile);
		try {
			if (!outputfile.exists() || !outputfile.canRead())
				outputfile.createNewFile();

			out = new FileOutputStream(outputfile);
		} catch (FileNotFoundException ex) {
			logger.error(ex);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	private void openToRead() {
		try {
			stream = new FileReader(this.getAbsolutePath());
			reader = new BufferedReader(stream);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 
	 * @param str
	 */
	public void println(String str) {
		try {
			if (this.out == null)
				return;
			StringBuilder linea = new StringBuilder();
			linea.append(str + "\n");

			this.out.write(linea.toString().getBytes());
		} catch (IOException e) {
			logger.error(e);
		}
	}

	/**
	 * 
	 * @return
	 */
	public String readLine() {
		String readLine = null;
		try {
			if (reader == null)
				openToRead();
			readLine = reader.readLine();
		} catch (IOException e) {
			logger.error(e);
		}
		return readLine;
	}

	/**
	 * Close file
	 */
	public void closeFile() {
		try {
			close();
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public String getAbsolutePath() {
		File file = new File(this.nomFile);

		if (file.exists() || file.canRead()) {
			return file.getAbsolutePath();
		} else {
			logger.debug("fichero no existe o no puede ser leido");
			return null;
		}
	}

	/**
	 * destruye archivo
	 */
	public void destroy() {
		try {
			File f = new File(this.nomFile);
			if (f.exists()) {
				this.out = null;
				this.stream = null;
				this.reader = null;
				boolean delete = f.delete();

				if (delete) {
					logger.debug("Archivo eliminado");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * 
	 * @param objecto
	 * @return
	 */
	public String getProperty(String objecto, String environment) {
		String key = "";
		if (prop == null)
			loadProperty();
		try {
			if (environment.isEmpty()) {
				key = prop.getProperty(objecto);
			} else {
				key = prop.getProperty(objecto + "." + environment);
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		return key;
	}

	/**
	 * 
	 * @param objecto
	 * @return
	 */
	public String getProperty(String objecto) {
		String key = "";
		if (prop == null)
			loadProperty();
		try {
			key = prop.getProperty(objecto);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return key;
	}

	/**
	 * 
	 */
	private void loadProperty() {
		FileInputStream streams = null;
		try {
			File f = new File(nomFile);
			if (!f.exists() || !f.canRead()) {
				logger.error("archivo no existe : " + nomFile);
				return;
			}
			prop = new Properties();
			streams = new FileInputStream(nomFile);
			prop.load(streams);
			prop.put("CARGA-CONFIG", "OK");
			streams.close();
		} catch (Exception e) {
			prop.put("CARGA-CONFIG", "NOOK");
			logger.error("error en carga : " + e);
		} finally {
			try {
				// cerramos stream
				if (streams != null)
					streams.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}

	}

}
