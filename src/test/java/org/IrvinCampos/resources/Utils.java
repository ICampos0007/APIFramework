package org.IrvinCampos.resources;

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
    public  static RequestSpecification requestSpecification;
    public RequestSpecification requestSpecificationUtil() throws IOException {
        if (requestSpecification == null) {
            PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .setContentType(ContentType.JSON).build();
            return requestSpecification;
        }
        return requestSpecification;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/org/IrvinCampos/resources/global.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);

    }

    public String getJSonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.get(key);
    }
}
