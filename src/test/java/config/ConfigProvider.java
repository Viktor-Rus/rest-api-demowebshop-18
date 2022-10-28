package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {

    public ConfigProvider setConfiguration(String environment) {
        System.setProperty("environment", environment);
        Config config = ConfigFactory.create(Config.class, System.getProperties());
        Configuration.remote = config.getRemoteUrl();
        Configuration.browser = config.getBrowserName();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
        return this;
    }
}