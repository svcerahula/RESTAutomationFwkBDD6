package payLoads;

public class createDataSourcePayload {
    public static String getPayLoad(String resourceName) {
        return "{\n" +
                "  \"dataStreamName\": \""+resourceName+"\",\n" +
                "  \"type\": \"derived\",\n" +
                "  \"application\": {\n" +
                "    \"detailsUrl\": \"http://example.com\",\n" +
                "    \"name\": \"Foo Example App\",\n" +
                "    \"version\": \"1\"\n" +
                "  },\n" +
                "  \"dataType\": {\n" +
                "    \"field\": [\n" +
                "      {\n" +
                "        \"name\": \"steps\",\n" +
                "        \"format\": \"integer\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"name\": \"com.google.step_count.delta\"\n" +
                "  },\n" +
                "  \"device\": {\n" +
                "    \"manufacturer\": \"Example Manufacturer\",\n" +
                "    \"model\": \"ExampleTablet\",\n" +
                "    \"type\": \"tablet\",\n" +
                "    \"uid\": \"1000001\",\n" +
                "    \"version\": \"1.0\"\n" +
                "  }\n" +
                "}";
    }
}
