package br.com.fiap.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	
	private Properties prop = new Properties();
	private String path = "resource/";
	
	public Config(String fileConfig) {		
		try {
			InputStream properties = Config.class.getClassLoader().getResourceAsStream(this.path + fileConfig);
			this.prop.load(properties);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public Properties getProp() {
		return prop;
	}
	
	public String getProperty(String config) {
		return this.prop.getProperty(config);
	}
}
