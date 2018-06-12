package com.svhsoftware.automation.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	
	private Properties properties;
	private final String propertyFilePath= "config.properties";

	
	public ConfigManager(){
		BufferedReader reader;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(propertyFilePath).getFile());
			reader = new BufferedReader(new FileReader(file));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("config.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getHomeURL(){
		String value = properties.getProperty("home.url");
		if(value != null) return value;
		else throw new RuntimeException("home.url not specified in the config.properties file.");		
	}

}
