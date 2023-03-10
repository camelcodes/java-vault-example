package net.camelcodes.vault;


import static net.camelcodes.vault.AppPropertiesReader.appProperties;

import java.util.Optional;
import java.util.logging.Logger;

public class App {

  Logger log = Logger.getLogger(App.class.getName());

  private static final String API_KEY_VAULT_PATH_PROPERTY = "externalapi.api-key-vault-path";
  private static final String API_KEY_VAULT_PROPERTY = "externalapi.api-key-vault-property";


  public App() {

    callExternalApi();

  }

  /**
   * This method is simulating a web service call, in our case will just log a message.
   * <p>
   * The idea is to fetch the API_KEY from the Vault and log it in the console
   */
  private void callExternalApi() {

    VaultStaticSecretLoader vault = new VaultStaticSecretLoader();

    Optional<String> apiKey = vault.getStringKeyEntry(
        appProperties.getProperty(API_KEY_VAULT_PATH_PROPERTY),
        appProperties.getProperty(API_KEY_VAULT_PROPERTY));

    if (apiKey.isEmpty()) {
      throw new IllegalStateException("Couldn't retrieve api key from Vault");
    }

    log.info(String.format("[Mock Call] External api called with API_KEY: %s",
        apiKey.get()));
  }

  public static void main(String[] args) {
    new App();
  }


}