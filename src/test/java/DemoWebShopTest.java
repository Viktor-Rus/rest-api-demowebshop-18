import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class DemoWebShopTest {
    String authCookieName = "NOPCOMMERCE.AUTH";
    String tokenName = "__RequestVerificationToken";
    String tokenValueBody = "64_IgAxRLwWumrArl-Cc8ku132wQn9rQg1rnpFPOwxs6OVOKWCcf913Z-0ELTTlSfVqijjZcPVy2Kd4zX-SqEWY0pQO1a9xKA5NKE7XXY1I1";
    String tokenValueHeader = "UCkJckuoURfIyiS7HpzrRzActK9NOYSseBV1nzQ92p9rthjgun6-8ENw7AhYzH_FBY5kQBDQOGwXI_Fq_qURJY0eegsvDEwiU3cMa1Dco6I1";
    String email;
    String pass;
    String firstName;
    String lastName;


    @BeforeEach
    void createDataUser() {
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        pass = faker.numerify("11111111");
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
    }



    @BeforeAll
    static void setUp() {

        Configuration.baseUrl = "http://demowebshop.tricentis.com";
        RestAssured.baseURI = "http://demowebshop.tricentis.com";

    }
    @Test
    void registrationUser() {
        String authCookieValue=
        given()
                .when()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam(tokenName, tokenValueBody)
                .formParam("Gender", "F")
                .formParam("FirstName", firstName)
                .formParam("LastName", lastName)
                .formParam("Email", email)
                .formParam("Password", pass)
                .formParam("ConfirmPassword", pass)
                .cookie(tokenName, tokenValueHeader)
                .post("/register")
                .then()
                .log().all()
                .extract().cookie(authCookieName);



            open("/Themes/DefaultClean/Content/images/logo.png");
            Cookie authCookie = new Cookie(authCookieName, authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);


            open("/registerresult/1");
            $("div.header a.account").shouldHave(Condition.text(email));

    }

    @Test
    void changeGenderProfile() {
        String changeLastName = "changeLastName";

        String authCookieValue=
                given()
                        .when()
                        .contentType("application/x-www-form-urlencoded; charset=utf-8")
                        .formParam(tokenName, tokenValueBody)
                        .formParam("FirstName", firstName)
                        .formParam("Gender", "F")
                        .formParam("LastName", lastName)
                        .formParam("Email", email)
                        .formParam("Password", pass)
                        .formParam("ConfirmPassword", pass)
                        .cookie(tokenName, tokenValueHeader)
                        .post("/register")
                        .then()
                        .extract().cookie(authCookieName);


        open("/Themes/DefaultClean/Content/images/logo.png");
        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);

        open("/customer/info");
        $("input[id='LastName']").setValue(changeLastName);
        $("input[value='Save']").click();
        open("/customer/info");
        $("input[id='LastName']").getValue().equals(changeLastName);
    }
}
