package org.IrvinCampos.resources;

import io.restassured.path.json.JsonPath;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    @Test
    public void OAuth2Test() throws InterruptedException {

//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&service=lso&o2v=2&flowName=GeneralOAuthFlow");
//        driver.findElement(By.id("identifierId")).sendKeys("cozycchino3124@gmail.com");
//        driver.findElement(By.cssSelector("input[type = 'email']")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//[@type = 'password']")).sendKeys("221354");
//        driver.findElement(By.xpath("//[@type = 'password']")).sendKeys(Keys.ENTER);
//        Thread.sleep(4000);
//        String url = driver.getCurrentUrl();
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AVMBsJgdI0MY_Ce7GkNJpyuLL_HM3-PyMHSl8fbvPLE47mhWRnesa_AnqvfDQm8Mmgs1BA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
        String partialCode = url.split("code=")[1];
        String actualCode = partialCode.split("&scope")[0];
        System.out.println(actualCode);

       String accessTokenResponse = given().urlEncodingEnabled(false)
                .queryParam("code",actualCode)
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath jsonPath = new JsonPath(accessTokenResponse);
        String token = jsonPath.getString("access_token");

        String response = given().queryParam("access_token",token)
                .when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println(response);
    }
}
