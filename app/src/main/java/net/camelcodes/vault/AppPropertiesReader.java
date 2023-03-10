package net.camelcodes.vault;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppPropertiesReader {

  static final Properties appProperties = readPropertiesFile();

  private AppPropertiesReader() {
  }

  private static Properties readPropertiesFile() {
    Properties prop = new Properties();
    try (InputStream stream = ClassLoader.getSystemClassLoader()
        .getResourceAsStream("application.properties")) {
      prop.load(stream);
      return prop;
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load properties file", e);
    }
  }
}
