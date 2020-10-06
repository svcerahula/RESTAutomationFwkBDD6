package steps;

import Utils.*;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import payLoads.createDataSourcePayload;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetPostStepDefs {
    PropertyManager propMgr = new PropertyManager();
    String bearerToken;
    String createPayload;
    public Response createDataresourceResp;
    public Response getDataSourcesResponse;
    public String basePath;
    public String googleAPIBaseURI;
    public Map<String,String> commonHeaders;
    public TestContext context;
    @Before
    public void initSetup() throws IOException {
        // load properties
        propMgr.loadProperties();
        googleAPIBaseURI = propMgr.getGoogleFitnessApi();
        bearerToken = propMgr.getBearerToken();
        commonHeaders= getHeaders();
        context = new TestContext();
    }

    @Given("^payload for creating a \"([^\"]*)\" data source$")
    public void payload_for_creating_a_something_data_source(String dataSourceName) throws Throwable {
        createPayload = createDataSourcePayload.getPayLoad(dataSourceName);
    }

    @When("^http \"([^\"]*)\" action is done to the \"([^\"]*)\" API to create the dataSource$")
    public void http_something_action_is_done_to_the_something_api_to_create_a_new_data_source_name
            (String httpAction, String apiName) throws Throwable {
        commonHeaders = getHeaders();
        createDataresourceResp = RestAssuredUtils.httpRestRequest(googleAPIBaseURI,GoogleAPIs.valueOf(apiName).getSourceURL()
                ,httpAction,commonHeaders,createPayload,null);
        context.setContext("currentRestCallResponseStatusCode",createDataresourceResp.getStatusCode());
    }

    @Then("^response with \"([^\"]*)\" status is received$")
    public void response_with_something_status_is_received(String respStatusCode) throws Throwable {
        System.out.println("Return Status code : "+ context.getContextValue("currentRestCallResponseStatusCode"));
        Assert.assertEquals(Integer.parseInt(respStatusCode)
                ,context.getContextValue("currentRestCallResponseStatusCode")
                ,"Expected Status code :"+respStatusCode);

    }

    @And("^response has \"([^\"]*)\" equal to \"([^\"]*)\"$")
    public void response_has_something_equal_to_something(String attr, String attrValue) throws Throwable {
        JsonPath js = createDataresourceResp.jsonPath();
        Assert.assertEquals(js.getString(attr),attrValue);
        System.out.println("Successfully validated the value of "+attr
                +" attribute in the response Json. Its value is = "+attrValue);
    }

    @Given("^The \"([^\"]*)\" \"([^\"]*)\" dataSource is available in the \"([^\"]*)\" list then find its \"([^\"]*)\"$")
    public void the_something_something_is_available_in_the_something_list_find_its_something
            (String attr, String value, String arrayListAttr, String fetchAttrVal) throws Throwable {
        // do a GET response and find if the resource is available or not
        System.out.println("googleAPIBaseURI in get call is : "+ googleAPIBaseURI);
        getDataSourcesResponse = RestAssuredUtils.httpRestRequest(googleAPIBaseURI
                ,GoogleAPIs.valueOf("fitnessDataSources").getSourceURL()
                ,"GET",commonHeaders,null,null);
        Assert.assertEquals(getDataSourcesResponse.getStatusCode(),200);
        System.out.println("Output of GET operation : "+ getDataSourcesResponse.prettyPrint());
        String dataStreamId = JsonResponseSearchUtils.
                findValueInArrayListOfJsonResponse(getDataSourcesResponse,arrayListAttr,attr,value,fetchAttrVal);
        context.setContext("dataStreamId",dataStreamId); // passing the dataStreamID value to the @When step
    }

    @When("^http \"([^\"]*)\" action is done to the \"([^\"]*)\" API to delete the dataSource$")
    public void http_something_action_is_done_to_the_something_api_to_delete_something
            (String httpAction, String apiName) throws Throwable {
        System.out.println("Get dataStreamID of the Source to delete from testContext : "
                + context.getContextValue("dataStreamId"));
        Response delResp = RestAssuredUtils.httpRestRequest(googleAPIBaseURI,
                GoogleAPIs.valueOf("fitnessDataSources").getSourceURL()
                        +"/"+context.getContextValue("dataStreamId")
                ,httpAction,commonHeaders,null,null);
        context.setContext("currentRestCallResponseStatusCode",delResp.getStatusCode());
        System.out.println("Response JSON for deletion : "+ delResp.prettyPrint());
    }

    public Map<String,String> getHeaders() {
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization",bearerToken);
        return headers;
    }
}
