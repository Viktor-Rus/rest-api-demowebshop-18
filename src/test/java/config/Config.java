package config;

@org.aeonbits.owner.Config.Sources({
        "classpath:${environment}.properties"
})
public interface Config extends org.aeonbits.owner.Config {

//    @Key("browserName")
//    String getBrowserName();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

//    @Key("remoteUrl")
//    String getRemoteUrl();
}