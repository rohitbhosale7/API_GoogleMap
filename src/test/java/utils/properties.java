package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class properties {
	
	
	public String getproperty(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}

}
