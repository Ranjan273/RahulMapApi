package org;

import files.Payloads;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import static  io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class DynamicJson {

    @Test
    public void AddBook(){
        RestAssured.baseURI="http://216.10.245.166";

        String response=given()
                .header("Content-Type","application/json")
                .body(Payloads.AddBook())
        .when()
                .post("/Library/Addbook.php")
        .then()
                .assertThat().log().all().statusCode(200)
                .extract().response().asString();

        JsonPath js=ReUsableMethods.rawToJson(response);
        String id=js.get("ID");
        System.out.println(id);
    }
}
