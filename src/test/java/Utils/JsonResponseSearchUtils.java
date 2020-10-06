package Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonResponseSearchUtils {
    public static String findValueInArrayListOfJsonResponse(Response resp, String arrayListAttr, String attr
            , String val, String  fetchAttrVal) {
        JsonPath json = resp.jsonPath();
        String returnVal=null;
        int attrSize =  json.getInt(arrayListAttr+".size()");
        for(int i=0;i<attrSize;i++) {
            if(json.getString(arrayListAttr+"["+i+"]."+attr).equals(val)) {
                returnVal = json.getString(arrayListAttr+"["+i+"]."+fetchAttrVal);
                break;
            }
        }
        return returnVal;
    }
}
