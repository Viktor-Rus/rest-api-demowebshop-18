import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigProvider;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
public class TestBase {

        @BeforeAll
        static void configure() {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);

            Configuration.browserCapabilities = capabilities;
            Configuration.baseUrl = "http://demowebshop.tricentis.com";
            RestAssured.baseURI = "http://demowebshop.tricentis.com";

            Configuration.browserSize = "1920x1080";
            Configuration.browser = "opera";
            Configuration.browserVersion = "88";
//            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";


            ConfigProvider config = new ConfigProvider();

            // remote run
            config.setConfiguration("remote");
            // local run
//            config.setConfiguration("local");

//            Configuration.browserSize = System.getProperty("browser_size");
//            Configuration.browser = System.getProperty("browser_name");
//            Configuration.browserVersion = System.getProperty("browser_version");
//            Configuration.remote = System.getProperty("remote_selenide");
        }

        @AfterEach
        void addAttachments() {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }
}
