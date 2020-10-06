package Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredUtils {
    public static Response resp;
    public static Response httpRestRequest(String uri , String basePath,
                                           String httpAction, Map<String,String> headers,
                                           String payLoad, String contentType) {
        RestAssured.reset();
        RestAssured.baseURI = uri;
        RequestSpecification reqSpec = RestAssured.given();
        if(payLoad!=null)
            reqSpec.body(payLoad);

        System.out.println("Headers Passed are");
        for(String headerKey: headers.keySet()) {
            System.out.println(headerKey+"="+headers.get(headerKey));
            reqSpec.header(headerKey,headers.get(headerKey));
        }

        if(contentType!=null) {
            reqSpec.contentType(contentType);
        } else {
            reqSpec.contentType(ContentType.JSON);
        }
        if(httpAction.equals("POST")) {
            resp = reqSpec.post(basePath);
        } else if (httpAction.equals("GET")) {
            resp = reqSpec.get(basePath);
        } else if(httpAction.equals("DELETE")) {
            resp = reqSpec.delete(basePath);
        }
        return resp;
    }
}
