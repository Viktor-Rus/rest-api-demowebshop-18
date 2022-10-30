package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ConfigProvider {
    private final Config webConfig;

    public static final Config CONFIG = ConfigFactory.create(Config.class, System.getProperties());

    public ConfigProvider(final Config webConfig) {
        this.webConfig = webConfig;
    }

    public ConfigProvider projectConfiguration() {
        Configuration.browser = webConfig.getBrowserName();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();

        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.getRemoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
        return this;
    }


}