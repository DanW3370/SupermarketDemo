package com.qikserve.codingchallenge;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class TestProductResource {


    public static final String SERVER_API_URI = "http://localhost:8080/api/";

    @Test
    public void getAllProduct() {
        given().when()
                .get(SERVER_API_URI + "products")
                .then()
                .statusCode(200)
                    .contentType(ContentType.JSON)
                .body("list.size()",is(5));
    }
}
