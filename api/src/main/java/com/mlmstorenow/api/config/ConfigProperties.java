package com.mlmstorenow.api.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {
	static private Properties prop;

	public static String getConfigProp(String propName) {
		String property = getProp().getProperty(propName);
		return property;
	}

	public static Properties getProp() {
		prop = new Properties();

		try {

			String home = System.getProperty("user.dir");
			java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "resources", "Config.properties");
			boolean directoryExists = java.nio.file.Files.exists(path);
			String pathString = path.toString();
			if (!directoryExists) {
				throw new RuntimeException();
			}
			prop.load(new FileReader(pathString));
			return prop;
		} catch (IOException e) {
		}
		return null;
	}
}
