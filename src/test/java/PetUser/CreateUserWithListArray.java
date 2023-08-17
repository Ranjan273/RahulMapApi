package PetUser;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class CreateUserWithListArray{



    @Test
    public void CreateUser() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .header("Content-Type", "application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
                        "    \"username\": \"string\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]").log().all()
                .when()
                .post("/user/createWithArray")
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract()
                .response()
                .asString();

    }

    @Test
    public void GetUser(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .pathParam("username","string")
                .header("accept","application/json")
                .log().all()
        .when()
                .get("/user/{username}")
        .then()
        .assertThat().statusCode(200)
        .log().all()
        .extract().response().asString();

    }
    @Test
    public void UpdatedUser(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .pathParam("username","string")
                .header("accept","application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"string\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .log().all()
        .when()
                .put("/user/{username}")
        .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract()
                .response()
                .asString();
    }
    @Test
    public void CreateUserWithList(){

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .header("Content-Type", "application/json")
                .header("accept","application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
                        "    \"username\": \"string\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .log().all()
        .when()
                .post("/user/createWithList")
        .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response().asString();
    }

    @Test
    public void DeleteUser(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .pathParam("username","string")
                .log().all()
        .when()
                .delete("/user/{username}")
        .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response().asString();
    }
    @Test
    public void GetLoginUserIntoSystem(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .queryParam("username","string")
                .queryParam("password","string")
                .header("Content-Type","application/json")
                .log().all()
        .when()
                .get("/user/login")
        .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response().asString();

    }

    @Test
    public void GetLogOutFromCurrentLoginUser(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .header("Content-Type","application/json")
                .log().all()
        .when()
                .get("/user/logout")
        .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response().asString();
    }

    @Test
    public void AgainCreateUser(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"string\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .log().all()
        .when()
                .post("/user")
        .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response().asString();
    }

}
