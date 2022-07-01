package de.tudresden.agentontology.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	public static Properties properties = null;

	public static synchronized Properties getInstance() {
		if (properties == null) {
			try (InputStream resourceStream = new FileInputStream("resources/ganache.properties")) {
				properties = new Properties();
				if (resourceStream != null) {
					properties.load(resourceStream);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return properties;
	}
}
