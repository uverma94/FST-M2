package projectclass;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RESTAssuredGitHubProject {

  RequestSpecification requestSpec;
  
  ResponseSpecification responseSpec;
  
  String SSH_key ; 
  int sshKeyId;
  
  @BeforeClass
  public void setup() 
  {
	  requestSpec = new RequestSpecBuilder()
			  .setContentType(ContentType.JSON)
			  .addHeader("Authorization", "token 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")
			  .setBaseUri("https://api.github.com")
			  .build();
	  SSH_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCryS4o6gAYf36pkKF+WE4yHZI3EPGVUFGXa+H9zeXezo0sH1D/6sHg5I4yP3lOCEtLxpjHcsWYoG375Kcyost1d4uKkyy4Wa607POcAzlXdvduBRAM3IaKU65T4OdSJHxsmprLRAabWGwiKbYS1we6SAJo4YTIsXC9gcXTHrjCD6I0pHQ1iA1Q9ZJ3P9eXzq/lzb0meaYXeUPANWNwJnGQq39XOIJRI02dNfgQqpWF1K4J4xpnBe2fsCjON+dQM+9DdECj3JJbdylL73MKycwGMepRVv+6HotKlNUjjxXgme+1LgBPjsNT5upXKvtRvTOw/wmN+hltw/oRQTOq4uSf";
  }  
    
  @Test(priority=1)
  public void addsshkey() {
	  
	  String reqBody = "{\"title\": \"TestAPIKey\", \"key\": \""+SSH_key+"\"}"; 
      Response response = given().spec(requestSpec)  	
              .body(reqBody)  	
              .when().post("/user/keys");
      String resBody = response.getBody().asPrettyString();
      System.out.println(resBody);
      sshKeyId = response.then().extract().path("id");
      response.then().statusCode(201);
  }
  
  @Test(priority=2)
  public void getallsshkey() {
	  
	  Response response = given().spec(requestSpec)
			  .when().get("/user/keys");
	  
	  String resBody = response.getBody().asPrettyString();
	  System.out.println(resBody);
	  response.then().statusCode(200);		
	  
  }
  
  @Test(priority=3)
  public void deletesshkey() {
	  
	  Response response = given().spec(requestSpec)
			  .pathParam("keyId", sshKeyId).when().delete("/user/keys/{keyId}");
	  
	  String resBody = response.getBody().asPrettyString();
	  System.out.println(resBody);
	  
	  response.then().statusCode(204);
  }
}
