package org;

import files.Payloads;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {


    @Test
    public void sumOfCourses(){
        JsonPath jp=new JsonPath(Payloads.CoursePrice());
        int NumberOfCourses=jp.getInt("courses.size()");
        int sum=0;

        for (int i=0;i< NumberOfCourses;i++){
            int price=jp.getInt("courses["+i+"].price");
            int copy=jp.getInt("courses["+i+"].copies");
            int amount=price*copy;
            System.out.println(amount);
            sum=sum+amount;


        }
        System.out.println("Actual Amount is : "+sum);
        int purchaseAmount=jp.get("dashboard.purchaseAmount");
        System.out.println("Purchase amount is : "+purchaseAmount);
        Assert.assertEquals(sum,purchaseAmount);
    }
}
