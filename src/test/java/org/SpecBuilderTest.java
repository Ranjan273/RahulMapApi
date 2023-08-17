package org;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import serializeTest.PoJoOne;
import serializeTest.PoJoTwoLocation;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {

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
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();
        ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        RequestSpecification myrequest=given()
                .spec(req).body(pj);
        Response myResponse=myrequest.when()
                .post("/maps/api/place/add/json")
        .then()
                .spec(res)
                .extract().response();

        String responseString=myResponse.asString();
        System.out.println(responseString);

    }
}
