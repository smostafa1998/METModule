package testBase;

import baseAPI.BasePage;
import baseAPI.MetMuseumResources;
import baseAPI.RestAssuredClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class TestMetMuseumResources extends RestAssuredClient {
    Response response;
    ValidatableResponse validatableResponse;

    @Test(enabled = false)
    public void testGetObjects() {
        String endpoint = MetMuseumResources.OBJECTS;
        String requestURI = BasePage.API_URL + endpoint;
        get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test(enabled = false)
    public void testGetObjectID() {
        String endpoint = MetMuseumResources.OBJECTSID;
        String requestURI = BasePage.API_URL + endpoint;
        get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test(enabled = false)
    public void testGetDepartments() {
        String endpoint = MetMuseumResources.DEPARTMENTS;
        String requestURI = BasePage.API_URL + endpoint;
        get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    /**
     * For SEARCH resource with Params
     */

    @Test(enabled = false)
    public void testGetSearchwithQuery() {
        String endpoint = MetMuseumResources.SEARCH;
        String query = "?q=sunflowers";
        String requestURI = BasePage.API_URL + endpoint + query;
        get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test(enabled = false)
    public void testGetSearchwithHighlight() {
        String endpoint = MetMuseumResources.SEARCH;
        String highlight = "?isHighlight=true&q=sunflowers";
        String requestURI = BasePage.API_URL + endpoint + highlight;
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
        String testing = get(requestURI).body().prettyPrint();
        printObjects(testing);
    }

    @Test(enabled = false)
    public void testGetSearchwithDepartmentID() {
        String endpoint = MetMuseumResources.SEARCH;
        String highlight = "?departmentId=6&q=cat";
        String requestURI = BasePage.API_URL + endpoint + highlight;
        get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test(enabled = false)
    public void testGetSearchwithIsOnView() {
        String endpoint = MetMuseumResources.SEARCH;
        String highlight = "?isOnView=true&q=sunflowers";
        String requestURI = BasePage.API_URL + endpoint + highlight;
        get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test(enabled = false)
    public void testGetSearchwithArtistOrCultureRequest() {
        String endpoint = MetMuseumResources.SEARCH;
        String highlight = "?artistOrCulture=true&q=french";
        String requestURI = BasePage.API_URL + endpoint + highlight;
        get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }
    @Test(enabled = false)
    public void testGetSearchwithMediumRequest() {
        String endpoint = MetMuseumResources.SEARCH;
        String highlight = "?medium=Quilts|Silk|Bedcovers&q=quilt";
        String requestURI = BasePage.API_URL + endpoint + highlight;
        //get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
        String testing = get(requestURI).body().prettyPrint();
        printObjects(testing);
    }

    @Test(enabled = false)
    public void testGetSearchwithHasImagesRequest() {
        String endpoint = MetMuseumResources.SEARCH;
        String highlight = "?hasImages=true&q=Auguste Renoir";
        String requestURI = BasePage.API_URL + endpoint + highlight;
        get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test(enabled = false)
    public void testGetSearchwitGeolocationRequest() {
        String endpoint = MetMuseumResources.SEARCH;
        String highlight = "?geoLocation=France&q=flowers";
        String requestURI = BasePage.API_URL + endpoint + highlight;
        get(requestURI).body().prettyPeek();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test(enabled = false)
    public void testGetSearchwithDataRangeRequest() {
        String endpoint = MetMuseumResources.SEARCH;
        String highlight = "?dateBegin=1700&dateEnd=1800&q=African";
        String requestURI = BasePage.API_URL + endpoint + highlight;
       // get(requestURI).body().prettyPeek();
        get(requestURI).body().prettyPrint();
        response = get(requestURI);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

}
