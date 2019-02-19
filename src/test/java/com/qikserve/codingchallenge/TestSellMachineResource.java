package com.qikserve.codingchallenge;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import io.restassured.http.ContentType;

public class TestSellMachineResource {
	public static final String SERVER_MACHINE_API_URI = "http://localhost:8080/api/machine/";
	
	@Test
    public void testAddProduct() {      
        given().when().get(SERVER_MACHINE_API_URI + "addProduct/1")
        	.then()
        	.statusCode(200)
        	 .contentType(ContentType.JSON)
             .body("list.size()",is(1));
        
        given().when().get("/addProduct/1")
    	.then()
    	.statusCode(200)
    	 .contentType(ContentType.JSON)
         .body("list.size()",is(1))
         .body("list.get(0).quantity", is(2));
        
        given().when().get("/addProduct/2")
    	.then()
    	.statusCode(200)
    	 .contentType(ContentType.JSON)
         .body("list.size()",is(2))
         .body("list.get(0).quantity", is(2));
    }
	
	@Test
    public void testCheckout() {      
        given().when().get(SERVER_MACHINE_API_URI + "checkout")
        	.then()
        	.statusCode(200);
//        	 .contentType(ContentType.JSON)
//             .body("order.tota",is(1));
    }
	
	
	
}
