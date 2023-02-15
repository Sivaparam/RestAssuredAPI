import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;



public class dataUsaAPITest {

    @DataProvider
    public Object[][] data(){
        return new Object[][] {{"2020",322903030}};
    }
    private final static Logger logger = LogManager.getLogger(dataUsaAPITest.class);


    @Test(dataProvider = "data")
    public void getPopYr(String s1, int p1)  {
        baseURI = "https://datausa.io/api";
        Response response = given().get("data?drilldowns=Nation&measures=Population");

        JsonPath extractor = response.jsonPath();

        List<Object> responseData = extractor.getList("data");

       logger.info("Response Data is" + responseData);

        ArrayList<String> year = new ArrayList();
        ArrayList<Integer> population = new ArrayList();

        year = extractor.get("data.Year");
        population = extractor.get("data.Population");

       logger.info("Year details" + year);
       logger.info("Population details" + population);

        for (String yearCheck : year) {
            if (yearCheck.equals(s1)) {
               logger.info("Year is present");

            }
        }

        for (int popCheck : population) {
            if (popCheck == p1) {
                logger.info("Population is present");

            }
        }
    }
}
