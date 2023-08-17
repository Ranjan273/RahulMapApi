package eComApi;

import eComApiPojo.CreateOrderDetails;
import eComApiPojo.CreateOrders;
import eComApiPojo.LoginRequest;
import eComApiPojo.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class EComAPITest {


    public static void main(String[] args) {

        //Login user and get the token and userId

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        LoginRequest lr = new LoginRequest();
        lr.setUserEmail("ranjanjyoti273@gmail.com");
        lr.setUserPassword("Ranjan@001");


        RequestSpecification reqLogin = given().log().all().spec(req).body(lr);

        LoginResponse loginresponse = reqLogin.when().post("/api/ecom/auth/login")
                .then().log().all().extract().response().as(LoginResponse.class);
        String token = loginresponse.getToken();
        String userId = loginresponse.getUserId();
        System.out.println("Login Token is :" + loginresponse.getToken());
        System.out.println("Login user id is :" + loginresponse.getUserId());

        //Add Product

        RequestSpecification addProductreqbase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token).build();

        RequestSpecification reqAddProduct=given().log().all()
                .spec(addProductreqbase)
                .param("productName","CanvasArt")
                .param("productAddedBy",userId)
                .param("productCategory","Art")
                .param("productSubCategory","Canvas")
                .param("productPrice",10000)
                .param("productDescription","ArtBySupi")
                .param("productFor","Unisex")
                .multiPart("productImage",new File("C:\\Users\\supi\\Desktop\\Untitled.png"));

        String addProductResponse=reqAddProduct.when().post("/api/ecom/product/add-product")
                .then().log().all()
                .extract().response().asString();

        JsonPath jp=new JsonPath(addProductResponse);
        String productID=jp.get("productId");
        System.out.println(productID);

        //Create Order

        RequestSpecification CreateOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();
        CreateOrderDetails orderDetails=new CreateOrderDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderedId(productID);

        List<CreateOrderDetails> orderDetailsList=new ArrayList<CreateOrderDetails>();
        orderDetailsList.add(orderDetails);

        CreateOrders orders=new CreateOrders();
        orders.setOrders(orderDetailsList);


        RequestSpecification createOrderReq=given().log().all().spec(CreateOrderBaseReq)
                .body(orders);

        String createOrderResponse=createOrderReq.when()
                .post("/api/ecom/order/create-order")
                .then().log().all()
                .extract().response().asString();

        System.out.println(createOrderResponse);

        //Delete Product

        RequestSpecification DeleteProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();

        RequestSpecification deleteProductReq=given().log().all()
                .spec(DeleteProductBaseReq)
                .pathParam("productId",productID);

        String deleteProductResponse=deleteProductReq.when().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all()
                .extract().response().asString();

        JsonPath jp1=new JsonPath(deleteProductResponse);
        String deleteMessage=jp1.get("message");
        System.out.println(deleteMessage);
        Assert.assertEquals("Product Deleted Successfully",jp1.get("message"));



    }


}
