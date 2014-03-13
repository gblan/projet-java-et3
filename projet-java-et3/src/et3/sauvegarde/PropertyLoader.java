package et3.sauvegarde;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

	/**
	 * Charge la liste des propri�t�s contenu dans le fichier sp�cifi�
	 * 
	 * @param filename
	 *            le fichier contenant les propri�t�s
	 * @return un objet Properties contenant les propri�t�s du fichier
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
