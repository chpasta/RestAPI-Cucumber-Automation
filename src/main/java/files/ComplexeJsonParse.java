package files;

import io.restassured.path.json.JsonPath;

public class ComplexeJsonParse {

    public static void main(String[] args) {
        System.out.println("================1================");

        // //1. Print No of courses returned by API
        JsonPath jsonPath = new JsonPath(Payload.coursePrice());
        int count = jsonPath.getInt("courses.size()");
        System.out.println("Number of elements in the array: " + count);
        System.out.println("================2================");

        //2.Print Purchase Amount
        int totalAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase amount: $" + totalAmount);
        System.out.println("================3================");

        //3. Print Title of the first course
        String title = jsonPath.getString("courses[0].title");
        System.out.println("Titles: " + title);
        System.out.println("================4================");

        //4. Print All course titles and their respective Prices
        for (int i = 0; i < jsonPath.getInt("courses.size()"); i++){

            String title1 = jsonPath.getString("courses["+i+"].title");
            int price1 = jsonPath.getInt("courses["+i+"].price");
            System.out.println("Title: "  + title1 + " Price: $" + price1);
        }
        System.out.println("================5================");

        //5. Print no of copies sold by RPA Course
        String RPACopies = jsonPath.getString("courses[2].copies");
        System.out.println("RPACopies: " + RPACopies);

        for (int j = 0; j < jsonPath.getInt("courses.size()"); j++) {

            if (jsonPath.getString("courses["+j+"].title").equalsIgnoreCase("RPA")) {

                System.out.println("Title " + jsonPath.getString("courses["+j+"].title") + " is in position: " + j);
                System.out.println("Copies = " + jsonPath.getString("courses["+j+"].copies"));
                break;
            }
        }

    }
}
