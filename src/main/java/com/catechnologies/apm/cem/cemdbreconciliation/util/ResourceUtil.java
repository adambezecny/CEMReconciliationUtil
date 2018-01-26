package com.catechnologies.apm.cem.cemdbreconciliation.util;

import java.io.IOException; 
import java.io.InputStream; 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class for working with application.properties configuration file
 * @author bezad01
 *
 */
public class ResourceUtil {

	static final Logger log = LogManager.getLogger(ResourceUtil.class.getName());
	
	public static final String JDBC_LOGGING_LEVEL =	"eclipselink.logging.level";
	public static final String JDBC_DRIVER		  =	"javax.persistence.jdbc.driver";
	public static final String JDBC_URL			  =	"javax.persistence.jdbc.url";
	public static final String JDBC_PASSWORD	  =	"javax.persistence.jdbc.password";
	public static final String JDBC_USER		  =	"javax.persistence.jdbc.user";
	
	
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}	
	
	/**
	 * returns file as a String object
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String path) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, "UTF-8");
			}
	
	/**
	 * 
	 * @return
	 */
	public static Properties getApplicationProperties(){
		
		InputStream in = ResourceUtil.class.getResourceAsStream("/application.properties");
		
		Properties config = new Properties(); 
		try { 
			config.load(in); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}

		return config;		
	}
	
	public static String getApplicationProperty(String propertyName){
		return getApplicationProperties().getProperty(propertyName);
	}
	
	/**
	 * file must be put in src/resources folder
	 * @param fileName - name of the file to obtain, must start with / !!, e.g. /sourceBA1.xml
	 * @return
	 */
	public static String getFileResource(String fileName){
		return getStringFromInputStream(ResourceUtil.class.getResourceAsStream(fileName));
	}
	
	/**
	 * returns database configuration in HashMap. Used by JPAFactory
	 * @param dbType - literal 'source' or 'target'
	 * @return
	 */
	public static Map<String, String> getDBProperties(String dbType){
		if(!dbType.equals("sourcedb") && !dbType.equals("targetdb")) throw new RuntimeException ("unsupported dbType!");
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		Properties props = getApplicationProperties();

		map.put(JDBC_LOGGING_LEVEL, props.getProperty(dbType+"."+JDBC_LOGGING_LEVEL));
		map.put(JDBC_DRIVER, props.getProperty(dbType+"."+JDBC_DRIVER));
		map.put(JDBC_URL, props.getProperty(dbType+"."+JDBC_URL));
		map.put(JDBC_USER, props.getProperty(dbType+"."+JDBC_USER));
		map.put(JDBC_PASSWORD, props.getProperty(dbType+"."+JDBC_PASSWORD));			

		return map;
		
	}
	
}
