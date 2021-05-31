package activities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity2 {
	final static String ROOT_URL = "https://petstore.swagger.io/v2/user";
	  
	  @Test(priority=1)
	  public void addnewuserfromfile() throws IOException  {
		  
		  FileInputStream inputJSON = new FileInputStream("src/test/java/activities/userinfo.json");
		  
		  String reqBody = new String(inputJSON.readAllBytes());
		  
		  Response response = given().contentType(ContentType.JSON)
				  .body(reqBody).when().post(ROOT_URL);
		  
		  inputJSON.close();
		  response.then().body("code", equalTo(200));
		  response.then().body("message", equalTo("59329"));
		  
	  }
	  
	  @Test(priority=2)
	  public void getuserinfo() {
		  
		  
		  File outputJSON = new File("src/test/java/activities/userGETResponse.json");
		  
		  Response response = given().contentType(ContentType.JSON).pathParam("username", "mytestuser").when()
				  .get(ROOT_URL + "/{username}");
		  
		  String resBody = response.getBody().prettyPrint();
		  
		  try {
			  
			  outputJSON.createNewFile();
			  FileWriter writer = new FileWriter(outputJSON.getPath());
			  writer.write(resBody);
			  writer.close();		  
		  }catch(IOException excp) {		  
			  excp.printStackTrace();
		  }
		  
		  response.then().body("id", equalTo(59329));
	      response.then().body("username", equalTo("mytestuser"));  	
	      response.then().body("firstName", equalTo("Justin"));	
	      response.then().body("lastName", equalTo("Case"));	
	      response.then().body("email", equalTo("justincase@mail.com"));	
	      response.then().body("password", equalTo("password123"));	
	      response.then().body("phone", equalTo("9812763450"));
		  
	  }
	  
	  @Test(priority=3)
	  public void deleteuser() throws IOException {
		  
		  Response response = given().contentType(ContentType.JSON)
				  .pathParam("username", "mytestuser")
				  .when().delete(ROOT_URL + "/{username}");
		  
		  response.then().body("code", equalTo(200));
		  response.then().body("message", equalTo("mytestuser"));
		  
		  
	  }
	  
	  
}
