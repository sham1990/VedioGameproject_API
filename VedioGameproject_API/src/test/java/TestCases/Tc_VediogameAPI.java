package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class Tc_VediogameAPI {
	
	@Test
	public void test_GetallVedioGames()
	{
		given()
		
		.when()
		   .get("http://localhost:8081/app/videogames")
		 
		 .then()
		    .statusCode(200);
		   
	}

	@Test(priority=2)
	public void test_AddnewVedioGame()
	{
		HashMap data = new HashMap();
		
		data.put("id", "200");
		data.put("name", "Spider-Man");
		data.put("releaseDate", "2021-05-11T01:36:28.509Z");
		data.put("reviewScore", "5");
		data.put("category", "Adventure");
		data.put("rating", "Universal");
		
		Response resp=
		given()
		 .contentType("application/json")
		 .body(data)
		
		.when()
		   .post("http://localhost:8081/app/videogames")
		   
		 .then()
		    .statusCode(200)
		    .log().body()
		    .extract().response();
		
		String jsonstring = resp.asString();
		
		Assert.assertEquals(jsonstring.contains("Record Added Successfully"), true);
		
					
	}
	
	@Test(priority=3)
	public void test_GetViedioGame()
	{
		  given()
		  
		  .when()
		    .get("http://localhost:8081/app/videogames/200")
		.then()
		    .statusCode(200)
		    .log().body()
		    .body("videoGame.id", equalTo("200"))
		    .body("videoGame.name", equalTo("Spider-Man"));  
		     
	}
	
	@Test(priority=4)
	public void test_updateVedioGame()
	{

		HashMap data = new HashMap();
		
		data.put("id", "200");
		data.put("name", "Bat-Man");
		data.put("releaseDate", "2021-05-11T01:36:28.509Z");
		data.put("reviewScore", "4");
		data.put("category", "Adventure");
		data.put("rating", "Universal");
		
		
		given()
		 .contentType("application/json")
		 .body(data)
	   .when()
	     .put("http://localhost:8081/app/videogames/200")
	   .then()
		    .statusCode(200)
		    .log().body()
		    .body("videoGame.id", equalTo("200"))
		    .body("videoGame.name", equalTo("Bat-Man"));    
		
	}
	
	@Test(priority=5)
	public void test_deleteVedioGame()
	{    
		Response resp=
		given()
		.when()
		  .delete("http://localhost:8081/app/videogames/200")
		 .then()
		   .statusCode(200)
		   .log().body()
		   .extract().response();
		String jsonstring = resp.asString();
		
		Assert.assertEquals(jsonstring.contains("Record Deleted Successfully"), true);
		
		
	}
}
