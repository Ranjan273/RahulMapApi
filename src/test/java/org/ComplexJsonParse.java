package org;

import files.Payloads;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath jp=new JsonPath(Payloads.CoursePrice());

        //Print No of courses returned by API
        int NumberOfCourses=jp.getInt("courses.size()");
        System.out.println("Number of courses available : "+NumberOfCourses);

        //Print Purchase Amount
        int PurchaseAmount=jp.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase amount of the complete courses is : "+PurchaseAmount);

        //Print Title of the first course
        String TitleOfFirstCourse=jp.get("courses[0].title");
        System.out.println("Title of the First course is : "+TitleOfFirstCourse);

        //Print All course titles and their respective Prices
        for(int i=0;i<NumberOfCourses;i++){
            String coursetitles=jp.get("courses["+i+"].title");
            int Price=jp.get("courses["+i+"].price");
            System.out.println(jp.get("courses["+i+"].price").toString());
            System.out.println("course of "+i+" is " +coursetitles+ " and price of the course is "+Price);
        }

        //Print no of copies sold by RPA Course
        for(int i=0;i<NumberOfCourses;i++){
            String CourseTitle=jp.get("courses["+i+"].title");
            if(CourseTitle.equalsIgnoreCase("RPA")){
                System.out.println("No of copies for RPA course is "+jp.get("courses["+i+"].copies").toString());
                break;
            }
        }
    }
}
