package et3.sauvegarde;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyAcces {

	/**
	 * 
	 * @param key
	 * @param value
	 * @return sauvegarde l avancement dans le fichier sauvegarde.properties
	 */
	public void saveProperties(String key, String value) {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("sauvegarde.properties");

			// set the properties value
			prop.setProperty(key, value);

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 
	 * @param filename
	 * @param key
	 * @return value du .properties demande
	 */
	public static String retrieveProperties(String filename, String key) {
		String result = "";
		try {
			Properties prop = PropertyLoader.load(filename);
			result = prop.getProperty(key, "vide");

		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}
}
