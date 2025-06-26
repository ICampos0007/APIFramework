package org.IrvinCampos.resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Utils {
    RequestSpecification requestSpecification;
    public RequestSpecification requestSpecificationUtil() throws FileNotFoundException {
        PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt"));
        requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key","qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                .setContentType(ContentType.JSON).build();
        return requestSpecification;
    }
}
