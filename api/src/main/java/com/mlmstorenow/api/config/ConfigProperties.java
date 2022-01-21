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
			prop.load(new FileReader("src\\main\\resources\\Config.properties"));
			return prop;
		} catch (IOException e) {
		}
		return null;
	}
}
