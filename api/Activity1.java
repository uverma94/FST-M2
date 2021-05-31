package activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity1 {
	final static String ROOT_URL = "https://petstore.swagger.io/v2/pet";
  	
	  @Test(priority = 1)
	  public void Addnewpet() {
		  
		  String reqBody = "{\"id\": 58327, \"name\": \"MyPet\", \"status\": \"alive\"}";
		  
		  Response response = given().contentType(ContentType.JSON).body(reqBody).when().post(ROOT_URL);
		  
		  response.then().body("id", equalTo(58327));
		  response.then().body("name", equalTo("MyPet"));
		  response.then().body("status", equalTo("alive"));
		  
	  }
	  
	  @Test(priority = 2)
	  public void getpetInfo() {
		  
		  Response response = given().contentType(ContentType.JSON).when().pathParam("petId", "58327").get(ROOT_URL +"/{petId}");
		  
		  response.then().body("id", equalTo(58327));
		  response.then().body("name", equalTo("MyPet"));
		  response.then().body("status", equalTo("alive"));
		  
	  }
	  
	  @Test(priority=3)
	  public void deletpet(){
		  Response response = given().contentType(ContentType.JSON).when().pathParam("petId", "58327").delete(ROOT_URL+"/{petId}");
		  
		  response.then().body("code", equalTo(200));
		  response.then().body("message", equalTo("58327"));
		  
		  
	  }
	  
}
