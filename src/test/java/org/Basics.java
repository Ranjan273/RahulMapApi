package org;

import files.Payloads;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) {

        //Base URI
        RestAssured.baseURI="https://rahulshettyacademy.com";

        //Post Call
        String response=
                given()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(Payloads.addPlace())
                .log().all()
        .when()
                .post("/maps/api/place/add/json")
        .then()
                .assertThat()
                .statusCode(200).log().all()
                .assertThat().body("scope",equalTo("APP"))
                .assertThat().header("Server","Apache/2.4.41 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);

        //Converting String to Json and extract particular value using jsonpath class
        JsonPath js=new JsonPath(response);
        String placeID=js.getString("place_id");
        System.out.println("Place id of the current location is "+placeID);

        //Put call -for update the address
        String newAddress="70 Summer walk, USA";
        given()
                .log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
        .when()
                .put("/maps/api/place/update/json")
        .then()
                .assertThat().log().all()
                .statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        //Get Call

        String getResponse=given()
                .log().all()
                .queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
        .when()
                .get("/maps/api/place/get/json")
        .then()
                .assertThat().log().all()
                .statusCode(200)
                .extract()
                .response().asString();

        JsonPath getjs=ReUsableMethods.rawToJson(getResponse);
        String actualAddress=getjs.getString("address");
        System.out.println("Actual address is "+actualAddress);

        Assert.assertEquals(actualAddress,newAddress);



    }
}
