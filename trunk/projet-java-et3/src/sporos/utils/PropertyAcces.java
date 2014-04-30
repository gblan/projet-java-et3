package sporos.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Properties;

public class PropertyAcces {

	public static int getCurrentLevel() {
		int level = 0;

		try {
			Properties prop = PropertyLoader.load("save.properties");

			String hash = prop.getProperty("level");

			for (int i = 1; i < 1000; i++) {
				if (hash.compareTo(hachage(i)) == 0) {
					level = i;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			level = 0;
		}
		return level;
	}

	/**
	 * 
	 * @param key
	 * @return sauvegarde l avancement dans le fichier save.properties
	 */
	public static void saveProperties(int level) {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("save.properties");

			prop.setProperty("level", hachage(level));

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

	public static String hachage(int numLevel) {
		String sha1 = "";
		String s = String.valueOf(numLevel);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-512");
			crypt.reset();
			crypt.update(s.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
