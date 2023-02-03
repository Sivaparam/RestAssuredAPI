import static io.restassured.RestAssured.*;

import com.beust.ah.A;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
public class dataUsaAPITest {
    @Test
    public void getPopYr(){
        baseURI = "https://datausa.io/api";
//        given().
//                get("data?drilldowns=Nation&measures=Population").
//                then().
//                statusCode(200);


      Response response = given().get("data?drilldowns=Nation&measures=Population");
//      response.prettyPrint();

      JsonPath extractor = response.jsonPath();
//
//        Object obj = extractor.get("data.Year,Population");
//        System.out.println(obj.toString());

        System.out.println(extractor.getList("data"));
      ArrayList<String> year = new ArrayList();
        ArrayList<Integer> population = new ArrayList();
    year = extractor.get("data.Year");
    population = extractor.get("data.Population");
        System.out.println(year);
        System.out.println(population);


        for(String yearCheck : year){
            if(yearCheck.equals("2020") ){
                System.out.println("Year is present");
            }
        }

        for(int popCheck : population){
            if(popCheck == 322903030){
                System.out.println("Pop is present");
            }
        }

        JSONObject obj1 = new JSONObject();
//     obj1 = extractor.getJsonObject("data");

        Assert.assertEquals(year.get(0), "2020");
//    Response res1 = extractor.get("data[].Year");

//        System.out.println(res1);

    }
}
