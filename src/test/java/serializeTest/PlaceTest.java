package serializeTest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PlaceTest {

    @Test
    public void AddPlace(){

        RestAssured.baseURI="https://rahulshettyacademy.com";
        PoJoOne pj=new PoJoOne();
        pj.setAccuracy(50);
        pj.setAddress("29, side layout, cohen 09");
        pj.setLanguage("German-IN");
        pj.setName("Ravi Nilayam");
        pj.setPhone_number("(+91) 8099163065");
        pj.setWebsite("http://google.com");

        List<String> ml=new ArrayList<>();
        ml.add("shoe park");
        ml.add("shop");
        pj.setTypes(ml);

        PoJoTwoLocation pl=new PoJoTwoLocation();
        pl.setLat(-38.383494);
        pl.setLng(33.427362);

        pj.setLocation(pl);

        Response res=given().log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(pj)
        .when()
                .post("/maps/api/place/add/json")
        .then()
                .assertThat().statusCode(200)
                .extract().response();

        String responseString=res.asString();
        System.out.println(responseString);

    }
}
