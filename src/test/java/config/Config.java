package config;

@org.aeonbits.owner.Config.Sources({
        "classpath:${environment}.properties"
})
public interface Config extends org.aeonbits.owner.Config {

    @Key("browserName")
    String getBrowserName();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("browserPosition")
    String getBrowserPosition();

    @Key("baseUrl")
    String getBaseUrl();

    @Key("baseURI")
    String getBaseURI();

    @Key("remoteUrl")
    String getRemoteUrl();
}