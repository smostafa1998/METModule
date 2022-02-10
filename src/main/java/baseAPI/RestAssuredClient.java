package baseAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RestAssuredClient extends BasePage {

    public RestAssuredClient(){

    }

    public static Response get(String URL) {
        //GET
        RestAssured.defaultParser = Parser.JSON;

        return given().when().get(URL).then().contentType(ContentType.JSON).extract().response();
    }


    public static ValidatableResponse post(String URL, HashMap jsonBody) {
        //GIVE
        RestAssured.defaultParser = Parser.JSON;

        return given().contentType(ContentType.JSON).with().body(jsonBody).when().post(URL).then();
    }

    public static ValidatableResponse patch(String URL, HashMap  jsonBody) {
        RestAssured.defaultParser = Parser.JSON;
        return given().contentType(ContentType.JSON).with().body(jsonBody).when().patch(URL).then();
    }

    public static ValidatableResponse delete(String URL, HashMap objectToDelete) {
        RestAssured.defaultParser = Parser.JSON;
        return given().contentType(ContentType.JSON).with().body(objectToDelete).delete(URL).then();
    }

    public void printObjects(String testing){
        String [] testing2 = testing.split("\"objectIDs\":");
        String [] testing3 = testing2[1].split("\n");
        ArrayList<String> testing4 = new ArrayList<>();
        for(int i=0;i<testing3.length;i++){
            testing3[i] = testing3[i].trim();
            if (testing3[i].endsWith(",")){
                testing3[i] = testing3[i].substring(0,testing3[i].length()-1);
                testing4.add(testing3[i]);
            }
        }

        for(String s:testing4){
            String requestOBJURI = BasePage.API_URL + MetMuseumResources.OBJECTS +'/'+s;
            get(requestOBJURI).body().prettyPrint();
        }
    }

}
