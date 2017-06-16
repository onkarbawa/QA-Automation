package com.curbside.automation.common.configuration;

import com.curbside.automation.common.utilities.Helpers;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by kumar.nipun on 6/16/2017.
 */
public class Configuration {

  private ThreadLocal<Map<String,String>> properties = new ThreadLocal<>();

  /**
   * Gets the Object of Configuration
   */
  public Configuration(){
    loadProperties("properties.yaml");
  }

  /**
   * Loads the properties file
   * @param resource resource file path
   */
  private void loadProperties(String resource) {
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
    Map<String, String> map = (Map<String, String>)new Yaml().load(inputStream);
    properties.set(map);
  }

  /**
   * Gets the property value
   * @param key property name
   * @return property value as String
   */
  public String get(String key){
    return Helpers.isPropertySpecifiedAtOsLevel(key) ? System.getProperty(key) : properties.get().get(key);
  }
}
