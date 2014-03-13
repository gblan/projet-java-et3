package et3.sauvegarde;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

	/**
	 * Charge la liste des propriétés contenu dans le fichier spécifié
	 * 
	 * @param filename
	 *            le fichier contenant les propriétés
	 * @return un objet Properties contenant les propriétés du fichier
	 */
	public static Properties load(String filename) throws IOException,
			FileNotFoundException {
		Properties properties = new Properties();
		FileInputStream input = new FileInputStream(filename);

		try {
			properties.load(input);
			return properties;
		} finally {
			input.close();
		}
	}
}
