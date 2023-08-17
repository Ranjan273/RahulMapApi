package oAuthTest;
import Pojo.Api;
import Pojo.GetCourse;
import Pojo.WebAtomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
public class OAuth2Test {

    public static void main(String[] args) throws InterruptedException {

        /*System.setProperty("webdriver.chrome.driver","D://RahulShetty-RestAsuuredTutorial//chromedriver_win32");
        WebDriver driver=new ChromeDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("ranjanjyoti273@gmail.com");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("nilu@007");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        String currentURL=driver.getCurrentUrl();*/

        String currentURL="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AbUR2VPAagwzUmHc9OiQTBsXrvv-nKjTT1lYz6UhRqJoHYPeIBHraa87H2dGJrpRWcCJ6A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
        String partialCode=currentURL.split("code=")[1];
        String actualCode=partialCode.split("&scope")[0];
        System.out.println(actualCode);
        String[] coursesWeb={"Selenium Webdriver Java","Cypress","Protractor"};



        String accessTokenResponse=given()
                .urlEncodingEnabled(false)
                .queryParam("code",actualCode)
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code")
        .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        System.out.println(accessTokenResponse);

        JsonPath js=new JsonPath(accessTokenResponse);
        String accessToken=js.getString("access_token");


        String response=given()
                .queryParam("access_token",accessToken)
        .when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .asString();

        System.out.println(response);

        GetCourse gc=given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
        System.out.println(gc.getLinkedin());
        System.out.println(gc.getInstructor());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
        List<Api> apiCourses=gc.getCourses().getApi();
        System.out.println(gc.getCourses());
        for(int i=0;i<apiCourses.size();i++){

            System.out.println(gc.getCourses().getApi().get(i).getCourseTitle());
            System.out.println(gc.getCourses().getApi().get(i).getPrice());

        }
        ArrayList<String> actualWebCourses=new ArrayList<String>();
        List<WebAtomation> webcourses=gc.getCourses().getWebAutomation();
        for(int i=0;i<webcourses.size();i++){

            actualWebCourses.add(webcourses.get(i).getCourseTitle());

            if(webcourses.get(i).getCourseTitle().equalsIgnoreCase("Cypress")){
                System.out.println(webcourses.get(i).getPrice());
            }
        }
        List<String> expectWebCourses=Arrays.asList(coursesWeb);
        Assert.assertEquals(actualWebCourses,expectWebCourses);
        Assert.assertTrue(actualWebCourses.equals(expectWebCourses));
    }
}
