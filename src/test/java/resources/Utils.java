package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {

        if (req == null) {

            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

            req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalProperties("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
        }
        return req;
    }

    public static String getGlobalProperties(String key) throws IOException {

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/Users/carloscavalcante/IdeaProjects/Fixing2/src/test/java/resources/global.properties");

        properties.load(fileInputStream);

        return properties.getProperty(key);

    }

    public String getJsonPath(Response response, String key){

        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.get(key).toString();
    }

}
