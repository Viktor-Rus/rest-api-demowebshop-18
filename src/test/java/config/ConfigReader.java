package config;

import org.aeonbits.owner.ConfigFactory;

public enum ConfigReader {
    Instance;

    private static final Config WEB_CONFIG =
            ConfigFactory.create(
                    Config.class,
                    System.getProperties()
            );

    public Config read() {
        return WEB_CONFIG;
    }
}
